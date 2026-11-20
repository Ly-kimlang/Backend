package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import  org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface StudentRepository
        extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.major = :major")
    List<Student> searchByMajorCustom(@Param("major") String major);

    @Query("SELECT s FROM Student s WHERE s.age > :age")
    List<Student> findStudentsOlderThan(@Param("age") int age);
    List<Student> findByName(String name);

    List<Student> findByNameContaining(String name);

    List<Student> findByAgeGreaterThan(int age);

    List<Student> findByAgeLessThan(int age);

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByNameStartingWith(String name);

    List<Student> findByNameEndingWith(String name);

    List<Student> findByMajorAndAgeGreaterThan(String major, int age);

    List<Student> findByNameOrMajor(String name, String major);
}