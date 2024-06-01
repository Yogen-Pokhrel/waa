package com.waa.courses.service;

import com.waa.courses.dto.CourseDto;
import com.waa.courses.dto.CreateCourseDto;
import com.waa.courses.dto.UpdateCourseDto;
import com.waa.courses.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public List<Course> findAll() {
        return List.of();
    }

    @Override
    public CourseDto getById(long id) throws Exception {
        return null;
    }

    @Override
    public CourseDto save(CreateCourseDto p) {
        return null;
    }

    @Override
    public CourseDto delete(long id) throws Exception {
        return null;
    }

    @Override
    public CourseDto update(long id, UpdateCourseDto p) throws Exception {
        return null;
    }
}
