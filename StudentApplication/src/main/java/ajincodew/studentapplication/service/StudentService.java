package ajincodew.studentapplication.service;

import ajincodew.studentapplication.entity.StudentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    void saveStudent(StudentEntity student);
    StudentEntity retrieveStudentById(String id);
    List<StudentEntity> retrieveAllStudents();
    void deleteStudent(String id);
}