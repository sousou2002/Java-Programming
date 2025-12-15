package ajincodew.studentapplication.repository;

import ajincodew.studentapplication.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {
    // jpql / hql / sql
}