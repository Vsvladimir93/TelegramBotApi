package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.exceptions.CommandException;
import com.petproject.TelegramBotApi.repository.dto.Keyword;
import com.petproject.TelegramBotApi.repository.dto.Settings;
import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import com.petproject.TelegramBotApi.repository.mapper.SubscribedChatsMapper;
import com.petproject.TelegramBotApi.repository.mapper.SettingsMapper;
import com.petproject.TelegramBotApi.service.MemeScheduler;
import com.petproject.TelegramBotApi.service.SendMessageService;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.slf4j.Logger;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Set;
import java.util.TimeZone;

@Component("subscribe")
public class SubscribeCommand implements Command {

    private final Logger logger;
    private final SubscribedChatsMapper subscribedChatsMapper;
    private final SettingsMapper settingsMapper;
    private final KeywordMapper keywordMapper;
    private final MemeScheduler memeScheduler;
    private final SendMessageService sendMessageService;

    public SubscribeCommand(
            Logger logger,
            SubscribedChatsMapper subscribedChatsMapper,
            SettingsMapper settingsMapper,
            KeywordMapper keywordMapper, MemeScheduler memeScheduler,
            SendMessageService sendMessageService) {
        this.logger = logger;
        this.subscribedChatsMapper = subscribedChatsMapper;
        this.settingsMapper = settingsMapper;
        this.keywordMapper = keywordMapper;
        this.memeScheduler = memeScheduler;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public String getDescription() {
        return "/subscribe - initialize work for current chat";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String data) {
        Long chatId = update.getMessage().getChatId();
        Settings settings = settingsMapper.getByChatId(chatId);
        Set<Keyword> keywords = keywordMapper.getAllByChatId(chatId);

        validateInput(chatId, settings, keywords);

        subscribedChatsMapper.create(update.getMessage().getChatId());

        logger.info("Inserted chat id: {}", update.getMessage().getChatId());

        Runnable task = () -> {
            try {
                sendMessageService.send(bot, chatId, "Hello");
            } catch (TelegramApiException e) {
                logger.error("", e);
            }
        };

        try {
            subscribe(chatId, settings, task);
            logger.info("Subscribed for chat: {}", update.getMessage().getChatId());
            return () -> "Subscribed.";
        } catch (Exception e) {
            logger.error("", e);
            return () -> "Error. Can't subscribe.";
        }
    }

    private void validateInput(Long chatId, Settings settings, Set<Keyword> keywords) {
        if (chatId == null || chatId == 0) {
            logger.error("Invalid chatId: {}", chatId);
            throw new CommandException("ChatId invalid");
        }

        if (settings == null || settings.getTimeZone() == null || settings.getCronTime() == null)
            throw new CommandException("Cannot subscribe without settings.");

        TimeZone timeZone = TimeZone.getTimeZone(settings.getTimeZone());

        boolean isCorrectTimeZone = timeZone
                .getID()
                .equals(settings.getTimeZone());

        if (!isCorrectTimeZone) {
            logger.error(
                    "Timezone is not correct: Actual Timezone <{}>, Returned ID <{}>",
                    settings.getTimeZone(),
                    timeZone.getID()
            );
            throw new CommandException("Timezone is not correct. <%s>", settings.getTimeZone());
        }

        try {
            CronExpression.parse(settings.getCronTime());
        } catch (IllegalArgumentException e) {
            logger.error("", e);
            throw new CommandException("Cron expression is not valid. <%s>", settings.getCronTime());
        }

        if (keywords.size() == 0)
            throw new CommandException("Unable to subscribe - you must have at least one keyword.");
    }

    private void subscribe(Long chatId, Settings settings, Runnable task) {
        memeScheduler.addTaskToScheduler(
                chatId,
                CronExpression.parse(settings.getCronTime()),
                TimeZone.getTimeZone(settings.getTimeZone()),
                task
        );
    }

}
