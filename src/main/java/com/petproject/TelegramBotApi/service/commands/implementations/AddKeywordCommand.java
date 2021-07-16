package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import com.petproject.TelegramBotApi.repository.dto.Keyword;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.slf4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("add_keyword")
public class AddKeywordCommand implements Command {

    private final KeywordMapper keywordMapper;
    private final Logger logger;

    public AddKeywordCommand(KeywordMapper keywordMapper, Logger logger) {
        this.keywordMapper = keywordMapper;
        this.logger = logger;
    }

    @Override
    public String getDescription() {
        return "/add_keyword <keyword> - add new keyword for current chat";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return () -> "Keyword is empty.";
        }

        try {
            Keyword k = new Keyword();
            k.setChatId(update.getMessage().getChatId());
            k.setKeyword(keyword);
            keywordMapper.create(k);
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            return () -> "Keyword already exists.";
        }

        String message = String.format("New keyword <%s> added.", keyword);

        logger.info(message);

        return () -> message;
    }

}
