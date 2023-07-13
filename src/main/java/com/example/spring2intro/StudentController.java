package com.example.spring2intro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
     @Autowired
    private StudentService studentService;

     @PostMapping("/add_student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
         String response=studentService.addStudent(student);
         return new ResponseEntity<>(response, HttpStatus.CREATED);
     }
     @GetMapping("/getstudent-byid")
    public ResponseEntity<Student> getStudentbyId(@RequestParam int id){
              Student st= studentService.getStudentByid(id);
              if(st==null){
                  return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
              }
              return new ResponseEntity<>(st,HttpStatus.OK);
     }
     @DeleteMapping("/delete-student")
    public String deleteStudent(@RequestParam int id){
         return studentService.deleteStudent(id);
     }

     @PutMapping("/update-age-ofstudent")
    public String updateAge(@RequestParam int id,@RequestParam int age){
         return studentService.updateAgebyId(id,age);
     }
     @GetMapping("/studentsage-above-25")
    public List<Student> getAgeaboVe25(){
         return studentService.getAgeAbove25();
     }
     @DeleteMapping("/deleteAll")
    public String deleteAll(){
         return studentService.deleteAll();
     }

     @GetMapping("/getAllstudent-byName")
    public List<Student> getAllstudentWithSamename(@RequestParam String name){
         return studentService.getAllstudentsWithsameName(name);
     }
}
