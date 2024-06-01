package com.waa.students.dto;

import com.waa.address.dto.AddressDto;
import lombok.Data;

@Data
public class StudentDetailDto {
    long student_id;
    String name;
    float gpa;
    AddressDto address;
}
