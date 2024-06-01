package com.waa.students.service;

import com.waa.courses.dto.CourseDto;
import com.waa.students.dto.CreateStudentDto;
import com.waa.students.dto.StudentDetailDto;
import com.waa.students.dto.StudentDto;
import com.waa.students.dto.UpdateStudentDto;
import com.waa.students.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    StudentDetailDto getById(long id) throws Exception;

    StudentDto save(CreateStudentDto p);

    StudentDto delete(long id) throws Exception;

    StudentDto update(long id, UpdateStudentDto p) throws Exception;

    List<CourseDto> findCoursesByStudentId(long id) throws Exception;

    List<StudentDto> searchStudentWithFilters(float gpa, String program) throws Exception;

}
