package com.example.android.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hostel implements Serializable {
    private Long id;

    private Date createdDate;

    private Date lastModify;

    private Integer count;

    private Double grade;

    private Integer remain;

}