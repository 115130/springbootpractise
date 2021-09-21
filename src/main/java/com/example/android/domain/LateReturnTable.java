package com.example.android.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LateReturnTable {
    private Long id;

    private Date createdDate;

    private Date lastModify;

    private Long hostel;

    private Long studentId;

}