package org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.util.Properties;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Properties hibernateProperties = new Properties();

        // Database connection settings
        hibernateProperties.put(Environment.DRIVER, "org.postgresql.Driver");
        hibernateProperties.put(Environment.URL, "jdbc:postgresql://localhost:5432/pharos");
        hibernateProperties.put(Environment.USER, "postgres");
        hibernateProperties.put(Environment.PASS, "jaydeep");

        // Hibernate properties
        hibernateProperties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.put(Environment.SHOW_SQL, "true");
        hibernateProperties.put(Environment.FORMAT_SQL, "true");
        hibernateProperties.put(Environment.HBM2DDL_AUTO, "none");

        // Create configuration
        Configuration configuration = new Configuration();
        configuration.setProperties(hibernateProperties);
        configuration.addAnnotatedClass(Student.class);

        // Create session factory
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            // Create session
            try (Session session = sessionFactory.openSession()) {
                // Method 1: Get all students
                List<Student> students = session.createQuery("from Student", Student.class).list();
                System.out.println("All Students:");
                for (Student student : students) {
                    System.out.println(student);
                }

                // Method 2: Get student by ID
                Student student = session.get(Student.class, 1);
                if (student != null) {
                    System.out.println("Student with ID 1: " + student);
                }

                // Method 3: Query with conditions
                List<Student> filteredStudents = session.createQuery(
                                "from Student where name = :fname", Student.class)
                        .setParameter("fname", "John")
                        .list();
                System.out.println("Students named John:");
                for (Student s : filteredStudents) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


