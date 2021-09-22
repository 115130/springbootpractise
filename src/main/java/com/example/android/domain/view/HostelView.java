package com.example.android.domain.view;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.Hostel;
import com.example.android.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelView implements Serializable {
    private Long id;
    private String studentNumber;
    private String studentName;


    public HostelView(User user, Hostel hostel) {
        this.id = hostel.getId();
        this.studentName = user.getUsername();
        this.studentNumber = user.getStudentNumber();
    }
}
