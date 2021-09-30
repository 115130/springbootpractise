package com.example.android.domain.view;

import com.example.android.domain.ClassInfo;
import com.example.android.domain.Hostel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentView implements Serializable {
    private Long id;

    private ClassInfo classInfo;

    private Hostel hostel;

    private String status;

    private String studentNumber;

    private String username;

}
