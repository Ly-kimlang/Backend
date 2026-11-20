package com.example.demo;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import  jakarta.validation.constraints.*;
import java.util.List;
@Entity
@Table(name ="students")
public class Student{
    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<Order> orders;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name ="profile_id")
    private Profile profile;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @NotBlank(message="Name cannot be empty!")
    private String name;

    @Min(value=18, message="Age must be at least 18!")
    @Max(value=100, message="Age cannot exceed 100!")
    private int age;

    @NotBlank(message ="Major cannot be empty!")
    private String major;
    public Student(){

    }
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }
    public void setMajor(String major){
        this.major=major;
    }
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
