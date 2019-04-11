package ru.smartsoft.analytics.loader.db.dataset;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "user_history")
public class UserHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "ssoid")
    private String userId;

    @Column(name = "ts")
    private LocalDateTime eventTime;

    @Column(name = "grp")
    private String eventGroup;

    @Column(name = "type")
    private String eventType;

    @Column(name = "sub_type")
    private String eventSubtype;

    @Column(name = "url")
    private String urlEvent;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "form_id")
    private String formId;

    @Column(name = "code")
    private String codeMGPU;

    @Column(name = "ltpa")
    private String sessionKey;
    @Column(name = "subdirresponse")
    private String subdirResponse;

    public UserHistoryEntity() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getEventGroup() {
        return eventGroup;
    }

    public void setEventGroup(String eventGroup) {
        this.eventGroup = eventGroup;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventSubtype() {
        return eventSubtype;
    }

    public void setEventSubtype(String eventSubtype) {
        this.eventSubtype = eventSubtype;
    }

    public String getUrlEvent() {
        return urlEvent;
    }

    public void setUrlEvent(String urlEvent) {
        this.urlEvent = urlEvent;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getCodeMGPU() {
        return codeMGPU;
    }

    public void setCodeMGPU(String codeMGPU) {
        this.codeMGPU = codeMGPU;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSubdirResponse() {
        return subdirResponse;
    }

    public void setSubdirResponse(String subdirResponse) {
        this.subdirResponse = subdirResponse;
    }

    public static class Builder {
        private UserHistoryEntity userHistoryEntity;

        public Builder() {
            userHistoryEntity = new UserHistoryEntity();
        }

        public Builder userId(String userId) {
            userHistoryEntity.userId = userId;
            return this;
        }

        public Builder eventTime(String time) {
            userHistoryEntity.eventTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(time)),
                    ZoneId.systemDefault());
            return this;
        }

        public Builder eventGroup(String eventGroup) {
            userHistoryEntity.eventGroup = eventGroup;
            return this;
        }

        public Builder eventSubtype(String eventSubtype) {
            userHistoryEntity.eventSubtype = eventSubtype;
            return this;
        }

        public Builder eventType(String eventType) {
            userHistoryEntity.eventType = eventType;
            return this;
        }

        public Builder urlEvent(String urlEvent) {
            userHistoryEntity.urlEvent = urlEvent;
            return this;
        }

        public Builder orgId(String orgId) {
            userHistoryEntity.orgId = orgId;
            return this;
        }

        public Builder formId(String formId) {
            userHistoryEntity.formId = formId;
            return this;
        }

        public Builder codeMGPU(String codeMGPU) {
            userHistoryEntity.codeMGPU = codeMGPU;
            return this;
        }

        public UserHistoryEntity build() {
            return userHistoryEntity;
        }
    }


}
