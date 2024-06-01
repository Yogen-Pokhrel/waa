package com.waa.students;

import com.waa.courses.dto.CourseDto;
import com.waa.helper.ApiResponse;
import com.waa.students.dto.CreateStudentDto;
import com.waa.students.dto.StudentDetailDto;
import com.waa.students.dto.StudentDto;
import com.waa.students.dto.UpdateStudentDto;
import com.waa.students.entity.Student;
import com.waa.students.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getStudents(){
        try{
            return ResponseEntity.ok(ApiResponse.success(studentService.findAll(), "Students fetched successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDetailDto>> getStudentById(@PathVariable long id){
        try{
            StudentDetailDto u = studentService.getById(id);
            return ResponseEntity.ok(ApiResponse.success(u, "Student fetched successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<ApiResponse<List<CourseDto>>> getCoursesForStudent(@PathVariable Long studentId) {
        try {
            List<CourseDto> courses = studentService.findCoursesByStudentId(studentId);
            return ResponseEntity.ok(ApiResponse.success(courses, "Courses fetched successfully!"));
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<StudentDto>>> findStudentsByProgramAndMaxGpa(@RequestParam String program, @RequestParam float maxGpa) {
        try {
            List<StudentDto> students = studentService.searchStudentWithFilters(maxGpa, program);
            return ResponseEntity.ok(ApiResponse.success(students, "Students fetched successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentDto>> createStudent(@Valid @RequestBody CreateStudentDto userData){
        try{
            StudentDto user = studentService.save(userData);
            return new ResponseEntity<>(ApiResponse.success(user, "Student created Successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> deleteStudentById(@PathVariable("id") @NonNull long id){
        try{
            StudentDto deletedStudent = studentService.delete(id);
            return new ResponseEntity<>(ApiResponse.success(deletedStudent, "Student deleted successfully"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> updateStudent(@PathVariable("id") @NonNull long id, @Valid @RequestBody UpdateStudentDto userData){
        try{
            StudentDto user = studentService.update(id, userData);
            return new ResponseEntity<>(ApiResponse.success(user, "Student Updated Successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    
}
