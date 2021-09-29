package com.example.android.domain;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Long id;

    private String studentNumber;

    private String username;

    private String password;

    private Integer admin;

    private Long classInfo;

    private Date createdDate;

    private Long hostel;

    private Date lastModify;

    private String status;

}