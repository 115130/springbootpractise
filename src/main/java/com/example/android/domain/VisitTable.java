package com.example.android.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitTable {
    private Long id;

    private Date createdDate;

    private Date lastModify;

    private Long accessedHostel;

    private Long accessedStudent;

    private String phone;

}