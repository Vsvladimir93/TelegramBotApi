package com.petproject.TelegramBotApi.service.commands;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommandTypeResolver {

    private final ApplicationContext context;

    public CommandTypeResolver(ApplicationContext context) {
        this.context = context;
    }

    public Command getCommandByString(String command, String args) {
        if (command.isBlank())
            throw new UnknownCommandException(
                    String.format("Command is blank: <%s>", command)
            );

        return (Command) context.getBean(command, args);
    }

}
