package com.example.spring2intro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    public String addStudent(Student student) {
        studentRepo.save(student);
        return "student added successfully";
    }

    public Student getStudentByid(int id) {
        Optional<Student> student=studentRepo.findById(id);
        if(student.isEmpty()){
            return null;
        }
        return student.get();
    }

    public String deleteStudent(int id) {
        Optional<Student> st=studentRepo.findById(id);
        if(st.isEmpty()){
            return "student doesn't exist with roll no "+id;
        }
        else{
            studentRepo.delete(st.get());
            return "deleted successfully";
        }
    }

    public String updateAgebyId(int id, int age) {
        Optional<Student> student=studentRepo.findById(id);
        if(student.isEmpty()){
            return "student doesn't exist with roll no "+id;
        }
        Student st=student.get();
        st.setAge(age);
        addStudent(st);
        return "updated successfully";
    }

    public List<Student> getAgeAbove25() {
        List<Student> students=studentRepo.findAll();
        List<Student> ageAbove25=new ArrayList<>();
        for(Student student:students){
            if(student.getAge()>25){
                ageAbove25.add(student);
            }
        }
        return ageAbove25;
    }

    public String deleteAll() {
        studentRepo.deleteAll();
        return "deleted";
    }

    public List<Student> getAllstudentsWithsameName(String name) {
        List<Student> students=studentRepo.findAll();
        List<Student> sameName=new ArrayList<>();
        for(Student st:students){
            if(st.getName().equals(name)){
                sameName.add(st);
            }
        }
        return sameName;
    }
}
