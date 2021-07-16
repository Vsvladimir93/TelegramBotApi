package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.mapper.SubscribedChatsMapper;
import com.petproject.TelegramBotApi.service.MemeScheduler;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("unsubscribe")
public class UnsubscribeCommand implements Command {

    private final SubscribedChatsMapper subscribedChatsMapper;
    private final MemeScheduler memeScheduler;

    public UnsubscribeCommand(
            SubscribedChatsMapper subscribedChatsMapper,
            MemeScheduler memeScheduler
    ) {
        this.subscribedChatsMapper = subscribedChatsMapper;
        this.memeScheduler = memeScheduler;
    }

    @Override
    public String getDescription() {
        return "/unsubscribe - stops work for current chat";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String data) {
        Long chatId = update.getMessage().getChatId();

        int deletedQuantity = subscribedChatsMapper.delete(chatId);

        if (deletedQuantity == 0) {
            return () -> "This chat is not subscribed yet.";
        }

        unsubscribe(chatId);

        return () -> "Unsubscribed successfully.";
    }

    private void unsubscribe(Long chatId) {
        memeScheduler.removeTaskFromScheduler(chatId);
    }

}
