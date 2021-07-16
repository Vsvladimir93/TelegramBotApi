package com.petproject.TelegramBotApi.repository.dto;

import java.util.Objects;

public class Keyword {
    private Long chatId;
    private String keyword;

    public Keyword(Long chatId, String keyword) {
        this.chatId = chatId;
        this.keyword = keyword;
    }

    public Keyword() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyword keyword1 = (Keyword) o;

        if (!Objects.equals(chatId, keyword1.chatId)) return false;
        return Objects.equals(keyword, keyword1.keyword);
    }

    @Override
    public int hashCode() {
        int result = chatId != null ? chatId.hashCode() : 0;
        result = 31 * result + (keyword != null ? keyword.hashCode() : 0);
        return result;
    }
}
