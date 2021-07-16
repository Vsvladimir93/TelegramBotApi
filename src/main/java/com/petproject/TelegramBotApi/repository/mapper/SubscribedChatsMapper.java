package com.petproject.TelegramBotApi.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscribedChatsMapper {

    List<Long> getAll();

    Long get(Long chatId);

    void create(Long chatId);

    Integer delete(Long chatId);

    default Boolean isSubscribed(Long chatId) {
        return get(chatId) != null;
    }

}
