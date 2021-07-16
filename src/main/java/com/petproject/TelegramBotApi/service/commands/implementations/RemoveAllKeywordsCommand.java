package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("remove_all_keywords")
public class RemoveAllKeywordsCommand implements Command {

    private final KeywordMapper keywordMapper;

    public RemoveAllKeywordsCommand(KeywordMapper keywordMapper) {
        this.keywordMapper = keywordMapper;
    }

    @Override
    public String getDescription() {
        return "/remove_all_keywords - remove all keyword for current chat.";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String data) {
        Integer deletedCount = keywordMapper.deleteAllByChatId(update.getMessage().getChatId());

        return () -> String.format("%d - keywords deleted.", deletedCount);
    }

}
