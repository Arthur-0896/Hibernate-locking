package com.wissen.app.repository;

import org.springframework.data.repository.CrudRepository;
import com.wissen.app.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
