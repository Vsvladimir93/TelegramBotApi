package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.stream.Collectors;

@Component("help")
public class HelpCommand implements Command {

    private final List<Command> commands;

    public HelpCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String getDescription() {

        return "To call a command from a bot, \nuse the following syntax (without triangular brackets)\n\n" +
                "/ <command> <arguments>\n\n" +
                "/help - show description for all commands\n";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String data) {
        String helpText = this.getDescription()
                .concat(
                        commands.stream()
                                .map(c -> c.getDescription().concat("\n"))
                                .collect(Collectors.joining())
                );

        return () -> helpText;
    }

}
