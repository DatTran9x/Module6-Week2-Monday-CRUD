package main.service;

import main.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();
    void save(Student student);
    void delete(Long id);
    Student findById(Long id);

}
