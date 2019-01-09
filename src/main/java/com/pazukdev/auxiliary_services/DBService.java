package com.pazukdev.auxiliary_services;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * Before compile and run application run main(String[] args) method to create table described at db.changelog.xml
 *
 */


public class DBService {

    private static Connection connection;

    private static final String URL="jdbc:mysql://localhost:3306/bearings?autoReconnect=true&amp;serverTimezone=UTC";
    private static final String USERNAME="demo";
    private static final String PASSWORD="demo";

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new FabricMySQLDriver());
            connection=DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("db/db.changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
            database.close();
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e.getClass() + ": " + e.getMessage());
                }
            }
        }
    }

}
