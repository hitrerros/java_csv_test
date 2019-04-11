package ru.smartsoft.analytics.loader.web.db.resultset;

public class UsageFrequencyEntity {

    private String formId;
    private int frequency;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public static class Builder {
        private UsageFrequencyEntity usageFrequencyEntity;

        public Builder() {
            usageFrequencyEntity = new UsageFrequencyEntity();
        }

        public Builder formId(String formId) {
            usageFrequencyEntity.formId = formId;
            return this;
        }

        public Builder frequency(String frequency) {
            usageFrequencyEntity.frequency = Integer.valueOf(frequency);
            return this;
        }

        public UsageFrequencyEntity build() {
            return usageFrequencyEntity;
        }
    }
}
