package com.petproject.TelegramBotApi.service.commands;

public final class CommandParser {

    private final String messageText;

    public CommandParser(String messageText) {
        this.messageText = messageText.trim();

        if (messageText.isBlank())
            throw new IllegalArgumentException(
                    String.format("Message should contains at least command! %s", messageText)
            );
    }

    public PairedResult parse() {
        int firstSpaceIndex = messageText.indexOf(" ");

        String arguments = "";
        String command;

        if (firstSpaceIndex != -1) {
            arguments = messageText.substring(firstSpaceIndex + 1);
            command = messageText.substring(1, firstSpaceIndex); // Skip first index slash symbol
        } else {
            command = messageText.substring(1);
        }

        return new PairedResult(CommandType.getCommandByString(command), arguments);
    }

    public static class PairedResult {
        private final CommandType commandType;
        private final String arguments;

        public PairedResult(CommandType commandType, String arguments) {
            this.commandType = commandType;
            this.arguments = arguments;
        }

        public CommandType getCommandType() {
            return commandType;
        }

        public String getArguments() {
            return arguments;
        }
    }

}
