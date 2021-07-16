package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("remove_keyword")
public class RemoveKeywordCommand implements Command {

    private final KeywordMapper keywordMapper;

    public RemoveKeywordCommand(KeywordMapper keywordMapper) {
        this.keywordMapper = keywordMapper;
    }

    @Override
    public String getDescription() {
        return "/remove_keyword <keyword> - remove keyword.";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return () -> "Keyword is empty.";
        }

        Integer deletedCount = keywordMapper.deleteByChatId(update.getMessage().getChatId(), keyword);

        if (deletedCount > 0) {
            return () -> String.format("Keyword <%s> deleted.", keyword);
        }

        return () -> String.format("Has no keyword <%s>", keyword);
    }

}
