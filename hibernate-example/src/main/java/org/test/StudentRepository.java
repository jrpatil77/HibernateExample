package org.test;

import org.hibernate.Session;

import java.util.List;

public class StudentRepository {

    public static List<Student> getAllStudents() {
        Session session = DatabaseUtility.getSession();
        List<Student> students = session.createQuery("FROM Student", Student.class).list();
        session.close();
        return students;
    }
}
