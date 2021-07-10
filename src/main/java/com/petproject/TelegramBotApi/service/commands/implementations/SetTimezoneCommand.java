package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("set_timezone")
@Scope("prototype")
public class SetTimezoneCommand implements Command {

    private String timezone;

    public SetTimezoneCommand(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public CommandResponse execute() {
        return () -> "Set timezone command work.";
    }

}
