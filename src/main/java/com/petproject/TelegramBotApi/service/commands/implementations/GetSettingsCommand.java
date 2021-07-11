package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("get_settings")
public class GetSettingsCommand implements Command {

    private final KeywordMapper keywordMapper;

    public GetSettingsCommand(KeywordMapper keywordMapper) {
        this.keywordMapper = keywordMapper;
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String arguments) {
        System.out.println(this.keywordMapper.findAll());
        return () -> "Settings: {...}";
    }
}
