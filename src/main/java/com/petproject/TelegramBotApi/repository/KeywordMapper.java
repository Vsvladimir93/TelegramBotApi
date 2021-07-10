package com.petproject.TelegramBotApi.repository;

import com.petproject.TelegramBotApi.repository.dto.Keyword;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface KeywordMapper {

    @Select("SELECT * FROM Keywords")
    List<Keyword> findAll();

    @Select("SELECT * FROM Keywords WHERE keyword = #{keyword}")
    Optional<Keyword> findByKeyword(String keyword);

    @Insert("INSERT INTO Keywords (keyword) VALUES (#{keyword})")
    Integer create(Keyword keyword);

}
