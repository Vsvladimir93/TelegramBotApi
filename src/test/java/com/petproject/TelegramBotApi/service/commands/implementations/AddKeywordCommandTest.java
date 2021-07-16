package com.petproject.TelegramBotApi.service.commands.implementations;

import com.petproject.TelegramBotApi.repository.dto.Keyword;
import com.petproject.TelegramBotApi.repository.mapper.KeywordMapper;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class AddKeywordCommandTest {

    private KeywordMapper keywordMapper;
    private Update mockUpdate;
    private Message mockMessage;
    private AddKeywordCommand addKeywordCommand;
    private final List<Keyword> mockStorage = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mockUpdate = mock(Update.class);
        mockMessage = mock(Message.class);

        when(mockUpdate.getMessage()).thenReturn(mockMessage);

        keywordMapper = mock(KeywordMapper.class);
        addKeywordCommand = new AddKeywordCommand(keywordMapper, mock(Logger.class));
        mockStorage.clear();
    }

    @Test
    void executeShouldCreateKeywordRecord() {
        String keyword = "Hello";
        Long chatId = 1L;

        Keyword k = new Keyword();
        k.setChatId(chatId);
        k.setKeyword(keyword);

        doAnswer(a -> {
            mockStorage.add(a.getArgument(0, Keyword.class));
            return 1;
        }).when(keywordMapper).create(k);

        when(mockMessage.getChatId()).thenReturn(chatId);

        addKeywordCommand.execute(null, mockUpdate, keyword);

        Condition<Keyword> keywordInStorage = new Condition<>(
                (ka) -> ka.getKeyword().equals(keyword),
                "keyword in storage"
        );

        assertThat(mockStorage)
                .hasSize(1)
                .contains(k)
                .has(keywordInStorage, Index.atIndex(0));
    }

}