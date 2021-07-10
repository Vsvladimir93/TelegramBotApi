package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("remove_keyword")
@Scope("prototype")
public class RemoveKeywordCommand implements Command {

    private KeywordMapper keywordMapper;
    private final String keyword;

    public RemoveKeywordCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResponse execute() {

        if (keyword == null || keyword.isBlank()) {
            return () -> "Keyword is empty.";
        }

        Optional<String> deletedKeyword = keywordMapper.delete(keyword);

        return () -> deletedKeyword
                .map(k -> String.format("Keyword <%s> deleted.", k))
                .orElse(String.format("Cannot delete <%s>", keyword));
    }

    @Autowired
    public void setKeywordMapper(KeywordMapper keywordMapper) {
        this.keywordMapper = keywordMapper;
    }
}
