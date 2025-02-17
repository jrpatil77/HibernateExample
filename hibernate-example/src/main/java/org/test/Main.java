package org.test;

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

        DatabaseUtility.closeConnection();

    }
}


