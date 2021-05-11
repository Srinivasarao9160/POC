package com.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.student.dao.StudentDao;
import com.student.details.Student;

@Service
	public class StudentService {
		
		@Autowired
		private StudentDao studentRepo;
		
		public Student addStudent(Student student) {
			return studentRepo.save(student);
		}
		
		public List<Student> getAllStudents(Integer pageNo, Integer pageSize){
			Pageable paging = PageRequest.of(pageNo, pageSize);
			Page<Student> pagedResult = studentRepo.findAll(paging);
			 if(pagedResult.hasContent()) {
		            return pagedResult.getContent();
		        } else {
		            return new ArrayList<Student>();
		        }
			 
			 }
		
		public int updatePhnNo(int id,String phoneNo) {
			int result = 1;
			Student  stud = studentRepo.findById(id).orElse(new Student());
			
			if(stud.getId()==0) {
				return result;
			}else {
				stud.setMnumber(phoneNo);
				studentRepo.save(stud);
				return result=0;
			}
		}
		
		public int updateName(int id , String name) {
			int result = 1;
			Student stud = studentRepo.findByName(name);
			if(stud.getId()==0) {
				return result;
			}else {
				stud.setName(name);
				studentRepo.save(stud);
				return result = 0;
			}
			}
		}

	

