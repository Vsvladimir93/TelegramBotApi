package com.petproject.TelegramBotApi.repository.dto;

public class Keyword {
    private Long id;
    private String keyword;

    public Keyword(Long id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public Keyword() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
