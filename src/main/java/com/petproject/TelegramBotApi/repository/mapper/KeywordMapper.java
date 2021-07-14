package com.petproject.TelegramBotApi.repository.mapper;

import com.petproject.TelegramBotApi.repository.dto.Keyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KeywordMapper {

    List<Keyword> findAll();

    void create(Keyword keyword);

    Integer delete(String keyword);

}
