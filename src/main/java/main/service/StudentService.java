package main.service;

import main.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repository.StudentRepo;

import java.util.List;

@Service
public class StudentService implements IStudentService{
    @Autowired
    StudentRepo studentRepo;

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepo.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepo.findById(id).get();
    }
}
