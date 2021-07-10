package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.KeywordMapper;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("get_settings")
public class GetSettingsCommand implements Command {

    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public CommandResponse execute() {

        System.out.println(this.keywordMapper.findAll());


        return () -> "Settings: {...}";
    }
}
