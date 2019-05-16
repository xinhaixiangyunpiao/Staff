package com.example.demo.controller;

import com.example.demo.Repository.StudentRepository;
import com.example.demo.Model.Result;
import com.example.demo.Model.Student;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public Result add(Student student){
        if(student.getId()==null || student == null || student.getName() == null || student.getClass() == null){
            return new Result("空指针");
        }
        if (student.getId() > 50000000 || student.getId() < 40000000){
            return new Result("id错误");
        }
        student.setTime(new Date());

        this.studentRepository.save(student);
        return new Result();
    }

    @PostMapping("/getall")
    public Result getall(){
        List<Student>list = (List<Student>)this.studentRepository.findAll();
        return new Result(list);
    }

    @PostMapping("/deleteall")
    public Result deleteall(){
        this.studentRepository.deleteAll();
        return new Result();
    }
}
