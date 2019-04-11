package ru.smartsoft.analytics.loader.web.db.resultset;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LastHourStatisticsEntity {

    private String userId;
    private LocalDateTime eventTime;
    private String formId;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }


    public static class Builder {
        private LastHourStatisticsEntity lastHourStatisticsEntity;

        public Builder() {
            lastHourStatisticsEntity = new LastHourStatisticsEntity();
        }

        public Builder userId(String userId) {
            lastHourStatisticsEntity.userId = userId;
            return this;
        }

        public Builder eventTime(String time) {
            lastHourStatisticsEntity.eventTime = LocalDateTime.parse(time, formatter);
            return this;
        }

        public Builder formId(String formId) {
            lastHourStatisticsEntity.formId = formId;
            return this;
        }

        public LastHourStatisticsEntity build() {
            return lastHourStatisticsEntity;
        }
    }
}
