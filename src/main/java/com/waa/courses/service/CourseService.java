package com.waa.courses.service;

import com.waa.courses.dto.CourseDto;
import com.waa.courses.dto.CreateCourseDto;
import com.waa.courses.dto.UpdateCourseDto;
import com.waa.courses.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    CourseDto getById(long id) throws Exception;

    CourseDto save(CreateCourseDto p);

    CourseDto delete(long id) throws Exception;

    CourseDto update(long id, UpdateCourseDto p) throws Exception;
}
