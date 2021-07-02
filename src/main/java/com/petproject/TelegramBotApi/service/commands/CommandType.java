package com.petproject.TelegramBotApi.service.commands;

import com.petproject.TelegramBotApi.service.commands.implementations.SetTimezoneCommand;
import com.petproject.TelegramBotApi.service.commands.implementations.GetSettingsCommand;

public enum CommandType {

    GET_MEME {
        @Override
        public Command newCommand(String data) {
            return null;
        }
    },
    ADD_KEYWORD {
        @Override
        public Command newCommand(String data) {
            return null;
        }
    },
    REMOVE_KEYWORD {
        @Override
        public Command newCommand(String data) {
            return null;
        }
    },
    SET_TIME {
        @Override
        public Command newCommand(String data) {
            return null;
        }
    },
    SET_TIMEZONE {
        @Override
        public Command newCommand(String data) {
            return new SetTimezoneCommand(data);
        }
    },
    GET_SETTINGS {
        @Override
        public GetSettingsCommand newCommand(String data) {
            return new GetSettingsCommand();
        }
    };

    public abstract Command newCommand(String data);

    public static CommandType getCommandByString(String command) {
        if (command.isBlank())
            throw new UnknownCommandException(
                    String.format("Command is blank: <%s>", command)
            );

        CommandType commandType = null;

        for (CommandType type : values()) {
            if (type.name().equals(command.trim().toUpperCase())) {
                commandType = type;
            }
        }

        if (commandType == null)
            throw new UnknownCommandException(
                    String.format("Command %s is unknown.", command)
            );

        return commandType;
    }

}
