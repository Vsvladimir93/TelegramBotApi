package com.petproject.TelegramBotApi.service.commands;

public interface CommandResponse {
    String getResponseMessage();

    default boolean hasMessage() {
        return getResponseMessage() != null && !getResponseMessage().isEmpty();
    }
}
