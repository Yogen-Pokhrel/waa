package com.waa.students.service;

import com.waa.courses.dto.CourseDto;
import com.waa.courses.entity.Course;
import com.waa.helper.ListMapper;
import com.waa.students.StudentRepository;
import com.waa.students.dto.CreateStudentDto;
import com.waa.students.dto.StudentDetailDto;
import com.waa.students.dto.StudentDto;
import com.waa.students.dto.UpdateStudentDto;
import com.waa.students.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Autowired
    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public StudentDetailDto getById(long id) throws Exception {
        return null;
    }

    @Override
    public StudentDto save(CreateStudentDto p) {
        return null;
    }

    @Override
    public StudentDto delete(long id) throws Exception {
        return null;
    }

    @Override
    public StudentDto update(long id, UpdateStudentDto p) throws Exception {
        return null;
    }

    @Override
    public List<CourseDto> findCoursesByStudentId(long id) throws Exception {
        return List.of();
    }

    @Override
    public List<StudentDto> searchStudentWithFilters(float gpa, String program) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> studentRoot = cq.from(Student.class);
        Join<Student, Course> courseJoin = studentRoot.join("courses");

        Predicate programPredicate = cb.equal(courseJoin.get("program"), program);
        Predicate gpaPredicate = cb.lessThanOrEqualTo(studentRoot.get("gpa"), gpa);
        cq.where(cb.and(programPredicate, gpaPredicate));

        List <Student> res = entityManager.createQuery(cq).getResultList();
        return (List<StudentDto>) listMapper.mapList(res,new StudentDto());
    }


}
