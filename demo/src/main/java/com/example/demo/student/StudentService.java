package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudent() {
		return studentRepository.findAll();
	}
	
	 /**
	   * ユーザー情報 全検索
	   * @return 検索結果
	   */
	public List<Student> searchAll() {
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Long studentId){
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
				"student is :" + studentId));
		return student;
	}

	public void addNewStudents(Student student) {
		// TODO Auto-generated method stub
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException(
					"student is ;" + studentId);
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
				"student is :" + studentId));
		
		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			
			student.setEmail(email);
		}
	}
	
	@Transactional
	public void update(Long id, Student student) {
		Student originStudent = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
				"student is :" + student.getId()));
		
		if (! student.getName().isEmpty()) {
			originStudent.setName(student.getName());
		}
		
		if (! student.getEmail().isEmpty()) {
			originStudent.setEmail(student.getEmail());
		}
		
		if (! student.getBirthplace().isEmpty()) {
			originStudent.setBirthplace(student.getBirthplace());
		}
		
		if (! student.getDateOfBirth().isEmpty()) {
			originStudent.setDateOfBirth(student.getDateOfBirth());
		}
		if (! student.getSkill().isEmpty()) {
			originStudent.setSkill(student.getSkill());
		}
		if (! student.getProfile().isEmpty()) {
			originStudent.setProfile(student.getProfile());
		}
	}

	
}
