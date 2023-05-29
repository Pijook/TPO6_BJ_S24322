package pl.pijok.tpo6_bj_s24322;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import pl.pijok.tpo6_bj_s24322.book.repository.BookRepository;

import java.sql.Connection;

public class MainListener implements ServletContextListener {

    @Inject
    private BookRepository repository;

    @Inject
    private BaseRunner baseRunner;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        DataSource.initProps();

        repository.initTable();

        //baseRunner.start();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destroy!");
        ServletContextListener.super.contextDestroyed(sce);
    }
}
