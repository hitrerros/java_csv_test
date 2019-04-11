package ru.smartsoft.analytics.loader.parse;

import ru.smartsoft.analytics.loader.db.dataset.UserHistoryEntity;

import java.io.IOException;
import java.util.List;

public interface CSVParser {
    List<UserHistoryEntity> parseFile(String fileName) throws IOException;
}
