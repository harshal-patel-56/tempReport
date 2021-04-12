package com.harshal.tempReport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {
    private String name;
    private String descripton;
    private Date date;
}
