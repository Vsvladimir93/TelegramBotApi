package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.exceptions.CommandException;
import com.petproject.TelegramBotApi.repository.dto.Settings;
import com.petproject.TelegramBotApi.repository.mapper.SettingsMapper;
import com.petproject.TelegramBotApi.service.MemeScheduler;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;

import org.slf4j.Logger;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.TimeZone;

@Component("set_time")
public class SetTimeCommand implements Command {

    private final Logger logger;
    private final SettingsMapper settingsMapper;
    private final MemeScheduler memeScheduler;

    public SetTimeCommand(Logger logger, SettingsMapper settingsMapper, MemeScheduler memeScheduler) {
        this.logger = logger;
        this.settingsMapper = settingsMapper;
        this.memeScheduler = memeScheduler;
    }

    @Override
    public String getDescription() {
        return "/set_time * * * * * * Etc/GMT - apply the meme show period";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String data) {
        PairedResult result = parse(data);

        Long chatId = update.getMessage().getChatId();

        Settings settings = new Settings();
        settings.setChatId(chatId);
        settings.setCronTime(result.expression.toString());
        settings.setTimeZone(result.timeZone.getID());

        settingsMapper.createOrUpdate(settings);

        if (memeScheduler.hasTaskForChat(chatId)) {
            memeScheduler.rescheduleTask(chatId, result.expression, result.timeZone);
            return () -> "Task rescheduled.";
        }

        return () -> "Time set correctly.";
    }

    private PairedResult parse(String data) {
        if (data == null || data.isBlank()) {
            logger.error("CronTime and TimeZone is empty: <{}>", data);
            throw new CommandException("Time is empty.");
        }

        int lastSpaceIndex = data.lastIndexOf(' ');

        if (lastSpaceIndex == -1) {
            logger.error("CronTime and TimeZone in wrong format: <{}>", data);
            throw new CommandException("Time in wrong format.");
        }

        String cronExp = data.substring(0, lastSpaceIndex);
        String timezone = data.substring(lastSpaceIndex).trim();

        CronExpression parsedCron;
        TimeZone parsedTimezone;

        try {
            parsedCron = CronExpression.parse(cronExp);
        } catch (IllegalArgumentException e) {
            logger.error("", e);
            throw new CommandException("CronExpression in wrong format.");
        }

        parsedTimezone = TimeZone.getTimeZone(timezone);

        if (!parsedTimezone.getID().equals(timezone)) {
            logger.error(
                    "Timezone in wrong format: Parsed Timezone <{}>, Given Timezone <{}>",
                    parsedTimezone.getID(),
                    timezone
            );
            throw new CommandException("Timezone in wrong format.");
        }

        return new PairedResult(parsedCron, parsedTimezone);
    }

    private static class PairedResult {
        final CronExpression expression;
        final TimeZone timeZone;

        public PairedResult(CronExpression expression, TimeZone timeZone) {
            this.expression = expression;
            this.timeZone = timeZone;
        }
    }
}
