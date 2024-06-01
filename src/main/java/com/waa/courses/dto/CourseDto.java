package com.waa.courses.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CourseDto {
    long course_id;
    String name;
}
