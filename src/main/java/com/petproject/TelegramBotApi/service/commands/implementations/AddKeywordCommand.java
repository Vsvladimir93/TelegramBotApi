package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import com.petproject.TelegramBotApi.repository.dto.Keyword;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component("add_keyword")
@Scope("prototype")
public class AddKeywordCommand implements Command {

    private KeywordMapper keywordMapper;
    private Logger logger;
    private final String keyword;

    public AddKeywordCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResponse execute() {
        if (keyword == null || keyword.isBlank()) {
            return () -> "Keyword is empty.";
        }

        try {
            Keyword k = new Keyword();
            k.setKeyword(keyword);
            keywordMapper.create(k);
        } catch (DuplicateKeyException e) {
            return () -> "Keyword already exists.";
        }

        String message = String.format("New keyword <%s> added.", keyword);

        logger.info(message);

        return () -> message;
    }

    @Autowired
    public void setKeywordMapper(KeywordMapper keywordMapper) {
        this.keywordMapper = keywordMapper;
    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
