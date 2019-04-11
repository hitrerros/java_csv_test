package ru.smartsoft.analytics.loader.web.db.resultset;

public class IncompleteActionsEntity {

    private String userId;
    private String grpId;
    private String subType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGrpId() {
        return grpId;
    }

    public void setGrpId(String grpId) {
        this.grpId = grpId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public static class Builder {
        private IncompleteActionsEntity incompleteActionsEntity;

        public Builder() {
            incompleteActionsEntity = new IncompleteActionsEntity();
        }

        public Builder userId(String userId) {
            incompleteActionsEntity.userId = userId;
            return this;
        }

        public Builder grpId(String grpId) {
            incompleteActionsEntity.grpId = grpId;
            return this;
        }

        public Builder subType(String subType) {
            incompleteActionsEntity.subType = subType;
            return this;
        }

        public IncompleteActionsEntity build() {
            return incompleteActionsEntity;
        }

    }

}
