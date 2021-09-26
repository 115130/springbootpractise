package com.example.android.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String status;

    private Date createdDate;

    private Date lastModify;

    private Long classInfo;

    private Long hostel;

    private String studentNumber;

    private Integer admin;



}