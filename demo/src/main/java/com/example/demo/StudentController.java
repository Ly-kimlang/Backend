package com.example.demo;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import  jakarta.validation.Valid;
@RestController
@RequestMapping("/students")
public class StudentController {
    @GetMapping("/search/older")
    public List<StudentResponse>findStudentsOlderThan(
            @RequestParam int age
    ){
        return studentService.findStudentsOlderThan(age);
    }
    @GetMapping("/search/custom")
    public List<StudentResponse>searchByMajorCustom(
            @RequestParam String major
    ){
        return studentService.searchByMajorCustom(major);
    }
    @GetMapping("/search/or")
    public List<StudentResponse>searchByNameOrMajor(
            @RequestParam String name,
            @RequestParam String major
    ){
        return studentService.searchByNameOrMajor(name,major);
    }
    @GetMapping("/search/ignore-case")
    public List<StudentResponse>searchIngnoreCase(
            @RequestParam String name
    ){
        return studentService.searchByNameIgnoreCase(name);
    }
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    // CREATE
    @PostMapping
    public StudentResponse createStudent(@Valid @RequestBody StudentRequest request) {
        Student student=studentService.convertToEntity(request);
        Student savedStudent=studentService.createStudent(student);
        return studentService.convertToResponse(savedStudent);// save student in mysql
    }


    // READ
    @GetMapping
    public List<StudentResponse> getStudents() {
        return studentService.getAllStudentResponse();//get all student
    }
    //update
    @PutMapping("/{id}")// get PUT request throught ID
    public StudentResponse updateStudent(
            @PathVariable int id,
            @Valid @RequestBody StudentRequest request// get request LSON from client
    ){
        Student student = studentService.convertToEntity(request);
        Student updateStudent=studentService.updateStudent(id,student);
        return studentService.convertToResponse(updateStudent);
    }
    //delete
    @DeleteMapping("/{id}")//delete request
    public String deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
        return "Student ID " + id + " Deleted Successfully!";
    }
    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);

        return studentService.convertToResponse(student);
    }
    
    //API 
    @GetMapping("/page")
    public Page<StudentResponse> getStudentsPage(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue =  "5")int size
    ){
        return studentService.getStudentsPage(page, size);
    }
    //API
    @GetMapping("/sort")
    public List<StudentResponse> getStudentsSorted(
            @RequestParam(defaultValue =  "name")String field
    ){
        return studentService.getStudentsSorted(field);
    }
    @GetMapping("/search")
    public List<StudentResponse> searchStudent(
            @RequestParam String name
    ) {
        return studentService.searchByName(name);
    }
    @GetMapping("/filter/age")
    public List<StudentResponse>filterByAge(
            @RequestParam int age
    ){
        return studentService.filterByAge(age);
    }


}