package ajincodew.studentapplication.controller.impl;

import ajincodew.studentapplication.controller.StudentController;
import ajincodew.studentapplication.entity.StudentEntity;
import ajincodew.studentapplication.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentControllerImpl implements StudentController {
    private StudentService studentService;

    @Override
    public void saveStudent(@RequestBody StudentEntity student) {
        studentService.saveStudent(student);
    }

    @Override
    public List<StudentEntity> retrieveAllStudents() {
        return studentService.retrieveAllStudents();
    }

    @Override
    public StudentEntity retrieveStudentById(String id) {
        return studentService.retrieveStudentById(id);
    }

    @Override
    public void deleteStudentById(String id) {
        studentService.deleteStudent(id);
    }
}