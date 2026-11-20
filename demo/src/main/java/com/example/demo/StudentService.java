package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import  org.springframework.data.domain.Pageable;
import  org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service // tell spring that class is service component
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //method
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    //method
    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(//create exception has HTTP Status
                                HttpStatus.NOT_FOUND,// set status code =404
                                "Student Not Found!"
                        )
                );
    }

    //method
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // update student
    public Student updateStudent(int id, Student newStudent) {

        Student student = getStudentById(id);//find id and then edit

        student.setName(newStudent.getName());
        student.setAge(newStudent.getAge());
        student.setMajor(newStudent.getMajor());

        return studentRepository.save(student);
    }

    //delete
    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    //connect to studentResponse
    public StudentResponse convertToResponse(Student student) {//convertToResponse()៖ បម្លែង Entity ទៅ DTO។
        return new StudentResponse(//  create new response DTO
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getMajor(),
                // it call Ternary operator
                student.getProfile() !=null
                ? student.getProfile().getAddress()
                        :null
        );
    }

    // API get student
    public List<StudentResponse> getAllStudentResponse() {
        return studentRepository.findAll()
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }

    public Page<StudentResponse> getStudentsPage(int page, int size){
        //pageable : set number page and amount data
        Pageable pageable=PageRequest.of(page,size);
        return studentRepository.findAll(pageable)
                .map(student -> convertToResponse(student));
    }
    public List<StudentResponse> getStudentsSorted(String field){
        Sort sort=Sort.by(Sort.Direction.ASC, field);// orginaise data
        return studentRepository.findAll(sort)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }
    public List<StudentResponse> searchByName(String name) {
        return studentRepository.findByNameContaining(name)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }
    public List<StudentResponse>filterByAge(int age){
        return studentRepository.findByAgeGreaterThan(age)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }
    public List<StudentResponse>searchByNameIgnoreCase(String name){
        return studentRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }
    public List<StudentResponse>searchByNameStartingwith(String name){
        return studentRepository
                .findByNameStartingWith(name)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }
    public List<StudentResponse>searchByNameEndingwith(String name){
        return studentRepository
                .findByNameEndingWith(name)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }
    public List<StudentResponse>searchByMajorAndAgeGreaterThan(String major, int age){
        return studentRepository
                .findByMajorAndAgeGreaterThan(major, age)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();

    }
    public List<StudentResponse> searchByNameOrMajor(
        String name, String major) {

         return studentRepository
            .findByNameOrMajor(name, major)
            .stream()
            .map(student -> convertToResponse(student))
            .toList();
    }
    public List<StudentResponse>searchByMajorCustom(String major){
        return studentRepository.searchByMajorCustom(major)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }
    public List<StudentResponse>findStudentsOlderThan(int age){
        return studentRepository.findStudentsOlderThan(age)
                .stream()
                .map(student -> convertToResponse(student))
                .toList();
    }
    public Student convertToEntity(StudentRequest request){
        Student student = new Student();
                student.setName(request.getName());
                student.setAge(request.getAge());
                student.setMajor(request.getMajor());
                Profile profile=new Profile();
                profile.setAddress(request.getAddress());
                student.setProfile(profile);
                return student;
    }

}
