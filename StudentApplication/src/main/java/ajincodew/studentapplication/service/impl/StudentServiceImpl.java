package ajincodew.studentapplication.service.impl;

import ajincodew.studentapplication.entity.StudentEntity;
import ajincodew.studentapplication.repository.StudentRepository;
import ajincodew.studentapplication.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Override
    public void saveStudent(StudentEntity student) {
        student.setId(UUID.randomUUID().toString());
        studentRepository.save(student);
        log.info("Student saved successfully");
    }

    @Override
    public StudentEntity retrieveStudentById(String id) {
        log.info("Retrieving student by id {}", id);
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<StudentEntity> retrieveAllStudents() {
        log.info("Retrieving all students");
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(String id) {
        log.info("Deleting student by id {}", id);
        studentRepository.deleteById(id);
    }
}
