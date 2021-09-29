package com.example.android.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Hostel {
    private Long id;

    private Integer count;

    private Date createdDate;

    private Double grade;

    private Date lastModify;

    private Integer remain;

}