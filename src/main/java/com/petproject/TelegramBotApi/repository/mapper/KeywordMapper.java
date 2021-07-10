package com.petproject.TelegramBotApi.repository.mapper;

import com.petproject.TelegramBotApi.repository.dto.Keyword;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface KeywordMapper {

    @Select("SELECT * FROM Keywords")
    List<Keyword> findAll();

    @Insert("INSERT INTO Keywords (keyword) VALUES (#{keyword})")
    Integer create(Keyword keyword);

    @Delete("DELETE FROM Keywords WHERE keyword = #{keyword} RETURNING keyword")
    Optional<String> delete(String keyword);

}
