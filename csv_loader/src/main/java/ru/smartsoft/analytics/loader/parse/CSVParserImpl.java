package ru.smartsoft.analytics.loader.parse;

import ru.smartsoft.analytics.loader.db.dataset.UserHistoryEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParserImpl implements CSVParser {

    private static final String CSV_SEPARATOR = ";";

    @Override
    public List<UserHistoryEntity> parseFile(String fileName) throws IOException {
        List<UserHistoryEntity> parseList = new ArrayList<>();
        File inputFile = new File(fileName);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)))) {
            parseList = br.lines().skip(1).map(CSVParserImpl::mapToMainEntity).collect(Collectors.toList());
        }
        return parseList;
    }

    private static UserHistoryEntity mapToMainEntity(String string) {
        String tokens[] = string.split(CSV_SEPARATOR);
        return new UserHistoryEntity.Builder()
                .userId(tokens[0])
                .eventTime(tokens[1])
                .eventGroup(tokens[2])
                .eventType(tokens[3])
                .eventSubtype(tokens[4])
                .urlEvent(tokens[5])
                .orgId(tokens[6])
                .formId(tokens[7])
                .codeMGPU(tokens[8])
                .build();
    }
}
