package com.waa.students;

import com.waa.courses.entity.Course;
import com.waa.students.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT sc.course FROM StudentCourse sc WHERE sc.student.student_id = :studentId")
    List<Course> findByStudentIdWithCourses(long studentId);

    @Query("SELECT s FROM Student s WHERE s.gpa <= :maxGpa")
    List<Student> findByGpaLessThanOrEqual(float maxGpa);

}
