package ru.smartsoft.analytics.loader.db;

import ru.smartsoft.analytics.loader.db.dataset.UserHistoryEntity;

import java.util.List;

public interface DBService {

    public void insertBatch(List<UserHistoryEntity> userHistoryList);

    public void shutdown();

}
