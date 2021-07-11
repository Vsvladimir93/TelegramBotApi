package com.petproject.TelegramBotApi.service.commands;

import org.springframework.stereotype.Component;

@Component
public final class CommandParser {

    public PairedResult parse(String messageText) {
        messageText = messageText.trim();

        if (messageText.isBlank())
            throw new IllegalArgumentException(
                    String.format("Message should contains at least command! %s", messageText)
            );

        int firstSpaceIndex = messageText.indexOf(" ");

        String arguments = "";
        String command;

        if (firstSpaceIndex != -1) {
            arguments = messageText.substring(firstSpaceIndex + 1);
            command = messageText.substring(1, firstSpaceIndex); // Skip first index slash symbol
        } else {
            command = messageText.substring(1);
        }

        return new PairedResult(command, arguments);
    }

    static class PairedResult {
        public final String command;
        public final String arguments;

        public PairedResult(String command, String arguments) {
            this.command = command;
            this.arguments = arguments;
        }
    }

}
