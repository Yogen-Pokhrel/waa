package com.waa.students.dto;

import lombok.Data;

@Data
public class CreateStudentDto {
    String name;
    float gpa;
    long address_id;
}
