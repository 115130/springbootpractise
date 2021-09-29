package com.example.android.domain;

import lombok.Data;

import java.util.Date;

@Data
public class LateReturnTable {
    private Long id;

    private Date createdDate;

    private Long hostel;

    private Date lastModify;

    private Long studentId;


}