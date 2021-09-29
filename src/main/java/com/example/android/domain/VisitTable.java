package com.example.android.domain;

import lombok.Data;

import java.util.Date;
@Data
public class VisitTable {
    private Long id;

    private Long accessedHostel;

    private Long accessedStudent;

    private Date createdDate;

    private Date lastModify;

    private String phone;


}