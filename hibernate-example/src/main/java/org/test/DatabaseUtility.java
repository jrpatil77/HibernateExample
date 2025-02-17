package org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DatabaseUtility {

    public  static Configuration configuration;
    public  static SessionFactory sessionFactory;

    public static void setDbConnectionConfig(String url, String username, String password) {
        if(configuration == null) {
            Properties hibernateProperties = new Properties();

            // Database connection settings
            hibernateProperties.put(Environment.DRIVER, "org.postgresql.Driver");
            hibernateProperties.put(Environment.URL, "jdbc:postgresql://localhost:5432/pharos");
            hibernateProperties.put(Environment.USER, "postgres");
            hibernateProperties.put(Environment.PASS, "jaydeep");

            hibernateProperties.put(Environment.URL, url);
            hibernateProperties.put(Environment.USER, username);
            hibernateProperties.put(Environment.PASS, password);

            // Hibernate properties
            hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            hibernateProperties.put(Environment.SHOW_SQL, "false");
            hibernateProperties.put(Environment.FORMAT_SQL, "true");
            hibernateProperties.put(Environment.HBM2DDL_AUTO, "none");

            // Create configuration
            configuration = new Configuration();
            configuration.setProperties(hibernateProperties);
            configuration.addAnnotatedClass(Student.class);
        }


    }

    public static Session getSession() {
        if(sessionFactory == null) {
            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory.openSession();
    }

    public static void closeConnection() {

        if(sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
}
