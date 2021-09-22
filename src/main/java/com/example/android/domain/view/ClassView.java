package com.example.android.domain.view;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassView implements Serializable {
    private Long id;
    private String username;
    private String studentNumber;
    private String status;
    private String speciality;
    private String classes;
    private String grade;

    public ClassView(User user, ClassInfo classInfo) {
        this.id = classInfo.getId();
        this.username = user.getUsername();
        this.studentNumber = user.getStudentNumber();
        this.status = user.getStatus();
        this.speciality = classInfo.getSpeciality();
        this.classes = classInfo.getClasses();
        this.grade = classInfo.getGrade();
    }
}
