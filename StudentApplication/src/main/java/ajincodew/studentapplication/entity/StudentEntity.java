package ajincodew.studentapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//L'entité principale Student contient les champs id, firstName, lastName, email et age.
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String age;
}