package com.example.android.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfo implements Serializable {
    private Long id;

    private String speciality;

    private String classes;

    private String grade;

}