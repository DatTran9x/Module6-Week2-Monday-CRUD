package main.service;

import main.model.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repository.ClassroomRepo;

import java.util.List;

@Service
public class ClassroomService implements IClassroomService {
    @Autowired
    ClassroomRepo classroomRepo;

    @Override
    public List<Classroom> findAll() {
        return (List<Classroom>) classroomRepo.findAll();
    }
}
