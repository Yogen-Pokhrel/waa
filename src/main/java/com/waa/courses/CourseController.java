package com.waa.courses;

import com.waa.courses.dto.CourseDto;
import com.waa.courses.dto.CreateCourseDto;
import com.waa.courses.dto.UpdateCourseDto;
import com.waa.courses.entity.Course;
import com.waa.courses.service.CourseService;
import com.waa.helper.ApiResponse;
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
public class CourseController {

    @Autowired
    private final CourseService courseService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getCourses(){
        try{
            return ResponseEntity.ok(ApiResponse.success(courseService.findAll(), "Courses fetched successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> getCourseById(@PathVariable long id){
        try{
            CourseDto u = courseService.getById(id);
            return ResponseEntity.ok(ApiResponse.success(u, "Course fetched successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseDto>> createCourse(@Valid @RequestBody CreateCourseDto createData){
        try{
            CourseDto user = courseService.save(createData);
            return new ResponseEntity<>(ApiResponse.success(user, "Course created Successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> deleteCourseById(@PathVariable("id") @NonNull long id){
        try{
            CourseDto deletedCourse = courseService.delete(id);
            return new ResponseEntity<>(ApiResponse.success(deletedCourse, "Course deleted successfully"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseDto>> updateCourse(@PathVariable("id") @NonNull long id, @Valid @RequestBody UpdateCourseDto updateData){
        try{
            CourseDto user = courseService.update(id, updateData);
            return new ResponseEntity<>(ApiResponse.success(user, "Course Updated Successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
