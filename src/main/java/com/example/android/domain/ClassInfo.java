package com.example.android.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfo {
    private Long id;

    private String speciality;

    private String classes;

    private String grade;

}