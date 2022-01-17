package main.repository;

import main.model.Classroom;
import org.springframework.data.repository.CrudRepository;

public interface ClassroomRepo extends CrudRepository<Classroom,Long> {
}
