package com.example.java.sms.controller;

import com.example.java.sms.dto.StudentDto;
import com.example.java.sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    // handler method to list students request
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("students")
    public String listStudents (Model model) {
        List<StudentDto> studentDtos = studentService.getAllStudent();
        model.addAttribute("students", studentDtos);
        return "students";
    }

    // handlerMethod to handle new student request
    @GetMapping("/students/new")
    public String newStudent (Model model) {
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent (@Valid @ModelAttribute("student") StudentDto student, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("student", student);
            return "create_student";
        }
        studentService.createStudent(student);
        return "redirect:/students";
    }

    // handler method to handle edit student request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent (@PathVariable("studentId") Long studentId,
                               Model model) {
        StudentDto student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/students/{studentId}")
    public String updateStudent (@PathVariable("studentId") Long studentId, @Valid @ModelAttribute("student") StudentDto studentDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "edit_student";
        }

        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId, Model model) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        return "view_student";
    }

}
