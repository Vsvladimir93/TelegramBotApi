package com.petproject.TelegramBotApi.service.commands;

public final class CommandRunner {

    private CommandParser parser;

    public CommandRunner(CommandParser parser) {
        this.parser = parser;
    }

    public CommandResponse execute() {
        CommandParser.PairedResult result = parser.parse();
        return result.getCommandType().newCommand(result.getArguments()).execute();
    }

}
