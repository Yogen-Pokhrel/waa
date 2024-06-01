package com.waa.courses.repository;

import com.waa.courses.entity.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDetailRepository extends JpaRepository<CourseDetails, Long> {
}
