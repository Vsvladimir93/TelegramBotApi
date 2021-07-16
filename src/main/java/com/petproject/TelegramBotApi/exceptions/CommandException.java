package com.petproject.TelegramBotApi.exceptions;

public class CommandException extends RuntimeException {

    private final String message;

    public CommandException(String formattedMessage, Object... args) {
        super(String.format(formattedMessage, args));
        this.message = String.format(formattedMessage, args);
    }

    public CommandException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
