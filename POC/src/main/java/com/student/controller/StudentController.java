package com.student.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.details.Student;
import com.student.service.StudentService;
import com.sun.net.httpserver.Authenticator.Result;

@RestController
@RequestMapping("api/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/display")
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "4") Integer pageSize) {
		List<Student> list = studentService.getAllStudents(pageNo, pageSize);

		return new ResponseEntity<List<Student>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public Student addStudent(@RequestBody Student student) {
		return studentService.addStudent(student);
	}

	@PutMapping("/update/{id}/{poneNo}")
	public String updatePhoneNo(@PathVariable int id, @PathVariable String poneNo) {
		int result = studentService.updatePhnNo(id, poneNo);

		if (result == 0) {
			return "update sucessfully";

		} else {

			return "Not Updated";
		}
	}

	@PutMapping("updatename/{id}/{name}")
	public String updateName(@PathVariable int id, @PathVariable String name) {
		int result = studentService.updateName(id, name);

		if (result == 0) {
			return "update sucessfully";
		} else {
			return " Not Updated";
		}
	}

}
