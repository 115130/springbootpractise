package com.example.android.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LateReturnTable implements Serializable {
    private Long id;

    private Date createdDate;

    private Date lastModify;

    private Long hostel;

    private Long studentId;

}