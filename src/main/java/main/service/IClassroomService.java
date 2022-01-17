package main.service;


import main.model.Classroom;

import java.util.List;

public interface IClassroomService {

    List<Classroom> findAll();
}
