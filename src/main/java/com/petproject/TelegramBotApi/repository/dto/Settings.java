package com.petproject.TelegramBotApi.repository.dto;

import java.util.Objects;

public class Settings {
    private Long chatId;
    private String cronTime;
    private String timeZone;

    public Settings(Long chatId, String cronTime, String timeZone) {
        this.chatId = chatId;
        this.cronTime = cronTime;
        this.timeZone = timeZone;
    }

    public Settings() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getCronTime() {
        return cronTime;
    }

    public void setCronTime(String cronTime) {
        this.cronTime = cronTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Settings settings = (Settings) o;

        if (!Objects.equals(chatId, settings.chatId)) return false;
        if (!Objects.equals(cronTime, settings.cronTime)) return false;
        return Objects.equals(timeZone, settings.timeZone);
    }

    @Override
    public int hashCode() {
        int result = chatId != null ? chatId.hashCode() : 0;
        result = 31 * result + (cronTime != null ? cronTime.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        return result;
    }
}
