package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.dto.Keyword;
import com.petproject.TelegramBotApi.repository.dto.Settings;
import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import com.petproject.TelegramBotApi.repository.mapper.SettingsMapper;
import com.petproject.TelegramBotApi.service.commands.Command;
import com.petproject.TelegramBotApi.service.commands.CommandResponse;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;
import java.util.stream.Collectors;

@Component("settings")
public class SettingsCommand implements Command {

    private final SettingsMapper settingsMapper;
    private final KeywordMapper keywordMapper;

    public SettingsCommand(SettingsMapper settingsMapper, KeywordMapper keywordMapper) {
        this.settingsMapper = settingsMapper;
        this.keywordMapper = keywordMapper;
    }

    @Override
    public String getDescription() {
        return "/get_settings - show current settings";
    }

    @Override
    public CommandResponse execute(DefaultAbsSender bot, Update update, String arguments) {
        Long chatId = update.getMessage().getChatId();

        Settings settings = settingsMapper.getByChatId(chatId);
        Set<Keyword> keywordSet = keywordMapper.getAllByChatId(chatId);

        String settingsString;
        if (settings != null) {
            settingsString = String.format(
                    "Settings: { \ncron: %s, \ntimeZone: %s\n}",
                    settings.getCronTime(),
                    settings.getTimeZone()
            );
        } else {
            settingsString = "Settings: {}";
        }

        String keywordsString = String.format(
                "Keywords: %s",
                keywordSet.stream()
                        .map(Keyword::getKeyword)
                        .collect(Collectors.joining(", ", "[", "]"))
        );

        String settingsResult = settingsString.concat("\n").concat(keywordsString);

        return () -> settingsResult;
    }
}
