package com.petproject.TelegramBotApi.repository.mapper;

import com.petproject.TelegramBotApi.repository.dto.Keyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface KeywordMapper {

    Set<Keyword> getAllByChatId(Long id);

    void create(Keyword keyword);

    Integer deleteAllByChatId(Long chatId);

    Integer deleteByChatId(Long chatId, String keyword);

}
