package com.petproject.TelegramBotApi.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;

@Component
public class SendMessageService {

    public void send(DefaultAbsSender bot, Long chatId, String message) throws TelegramApiException {
        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(String.valueOf(chatId))
                .text(message)
                .build();

        bot.execute(sendMessage);
    }

    public void send(Long charId, Image image) {
        // TODO send images
    }

}
