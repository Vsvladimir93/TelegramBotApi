package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("set_time")
@Scope("prototype")
public class SetTimeCommand implements Command {

    private String time;

    public SetTimeCommand(String time) {
        this.time = time;
    }

    @Override
    public CommandResponse execute() {
        return () -> "Set time command work.";
    }

}
