package ru.smartsoft.analytics.loader.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.smartsoft.analytics.loader.db.dataset.UserHistoryEntity;

import java.util.List;
import java.util.stream.IntStream;

public class DBServiceImpl implements DBService {

    private final SessionFactory sessionFactory;
    private final Configuration configuration;

    public DBServiceImpl() {
        configuration = new Configuration()
                .addAnnotatedClass(UserHistoryEntity.class)
                .configure();
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


    @Override
    public void insertBatch(List<UserHistoryEntity> userHistoryList) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        final int batchSize = Integer.valueOf(configuration
                .getProperties()
                .getProperty("hibernate.jdbc.batch_size"));

        IntStream.range(0, userHistoryList.size())
                .forEach(i -> {
                            session.save(userHistoryList.get(i));
                            if (i % batchSize == 0) {
                                session.flush();
                                session.clear();
                            }
                        }
                );
        tx.commit();
        session.close();
    }

    @Override
    public void shutdown() {
        sessionFactory.close();
    }
}
