package org.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.util.Properties;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        DatabaseUtility.setDbConnectionConfig("jdbc:postgresql://localhost:5432/pharos",
                "postgres",
                "jaydeep");

        List<Student> allStudents = StudentRepository.getAllStudents();

        allStudents.forEach(s -> {
            System.out.println(s.getName());
        });

    }
}


