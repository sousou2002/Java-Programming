package ajincodew.studentapplication.controller;

import ajincodew.studentapplication.entity.StudentEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface StudentController {
    @PostMapping
    void saveStudent(StudentEntity student);
    @GetMapping
    List<StudentEntity> retrieveAllStudents();
    @GetMapping("/{id}")
    StudentEntity retrieveStudentById(@PathVariable String id);
    @DeleteMapping("/{id}")
    void deleteStudentById(@PathVariable String id);
}
