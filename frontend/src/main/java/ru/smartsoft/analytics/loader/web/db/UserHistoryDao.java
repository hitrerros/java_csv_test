package ru.smartsoft.analytics.loader.web.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.smartsoft.analytics.loader.web.db.resultset.IncompleteActionsEntity;
import ru.smartsoft.analytics.loader.web.db.resultset.LastHourStatisticsEntity;
import ru.smartsoft.analytics.loader.web.db.resultset.UsageFrequencyEntity;

import java.util.List;

@Service
public class UserHistoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static final String SQL_SELECT_LAST_HOUR = "select ssoid, form_id,ts from user_history\n" +
            "where ts >= ( select max(ts)  - interval '1 hour' from user_history )";

    private static String SQL_SELECT_USAGE_FREQUENCY = "select count(grp)  frequency,\n" +
            "       grp\n" +
            "from user_history\n" +
            "where grp is not null and grp <> ''\n" +
            "group by grp\n" +
            "order by  frequency desc\n" +
            "limit 5\n";

    private static final String SQL_SELECT_INCOMPLETE_ACTIONS = "select total.ssoid,\n" +
            "       total.grp,\n" +
            "       total.ts,\n" +
            "       history.sub_type\n" +
            "\n" +
            "from (\n" +
            "select  res.ssoid ssoid,\n" +
            "        res.grp grp,\n" +
            "        max(res.step) step,\n" +
            "        max(res.ts) ts,\n" +
            "        max(res.max_step) max_step\n" +
            "from (\n" +
            "         select distinct usr.ssoid,\n" +
            "                         usr.ts,\n" +
            "                         usr.grp,\n" +
            "                         usr.sub_type,\n" +
            "                         ROW_NUMBER() OVER (PARTITION BY usr.ssoid,usr.grp order by usr.ts) step,\n" +
            "                         totals.max_step\n" +
            "         from user_history usr\n" +
            "                  join (select count(distinct sub_type)\n" +
            "                                      max_step,\n" +
            "                               grp    grp\n" +
            "                        from user_history\n" +
            "                        group by grp) totals\n" +
            "                       on usr.grp = totals.grp\n" +
            "         where ssoid not in ('Unauthorized', '')\n" +
            "\n" +
            "         order by usr.ssoid, usr.ts\n" +
            "     ) res\n" +
            "\n" +
            "group by  res.ssoid, res.grp\n" +
            "having max(step)<max(max_step)) total\n" +
            "inner join user_history history\n" +
            "on  total.ssoid = history.ssoid and\n" +
            "     total.grp = history.grp and\n" +
            "     total.ts = history.ts";

    public List<LastHourStatisticsEntity> collectLastHourStatistics() {

        return jdbcTemplate.query(SQL_SELECT_LAST_HOUR, new Object[]{},
                (resultSet, rowNum) -> {
                    return new LastHourStatisticsEntity
                            .Builder()
                            .userId(resultSet.getString("ssoid"))
                            .formId(resultSet.getString("form_id"))
                            .eventTime(resultSet.getString("ts"))
                            .build();
                });
    }

    public List<UsageFrequencyEntity> collectTopUsedForms() {

        return jdbcTemplate.query(SQL_SELECT_USAGE_FREQUENCY, new Object[]{},
                (resultSet, rowNum) -> {
                    return new UsageFrequencyEntity
                            .Builder()
                            .formId(resultSet.getString("grp"))
                            .frequency(resultSet.getString("frequency"))
                            .build();
                });
    }

    public List<IncompleteActionsEntity> collectIncompleteActions() {

        return  jdbcTemplate.query(SQL_SELECT_INCOMPLETE_ACTIONS, new Object[]{},
                (resultSet, rowNum) -> {
                    return new IncompleteActionsEntity
                            .Builder()
                            .userId(resultSet.getString("ssoid"))
                            .grpId(resultSet.getString("grp"))
                            .subType(resultSet.getString("sub_type"))
                            .build();
                });
    }
}
