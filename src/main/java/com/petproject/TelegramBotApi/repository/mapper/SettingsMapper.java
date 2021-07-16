package com.petproject.TelegramBotApi.repository.mapper;

import com.petproject.TelegramBotApi.repository.dto.Settings;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SettingsMapper {

    Settings getByChatId(Long chatId);

    void createOrUpdate(Settings settings);

    Integer deleteByChatId(Long chatId);

}
