package com.example.demo.student;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "api/v1/student")
public class StudentController {

	private final StudentService studentService;
	
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	
	@GetMapping(path = "/index")
	public String index() {
	      return "/index.html";
    }
	
	@GetMapping(path = "/list")
	public String getStudentList(Model model) {
		List<Student> studentList = this.studentService.searchAll();
        model.addAttribute("student_list", studentList);
        return "student_list";
	}
	
	@GetMapping(path="/add")
	public String viewDisplayAdd(@ModelAttribute Student student) {
		return "add";
	}
	
	/*
	 * @PostMapping(path="/register")
	 * 
	 * @ResponseBody public void registerNewStudent(@RequestBody Student student) {
	 * studentService.addNewStudents(student); }
	 */
	
	@PostMapping(path="/register")
	public String registerNewStudent(@ModelAttribute Student student) {
		Student newStudent = new Student();
		BeanUtils.copyProperties(student, newStudent);
		
		studentService.addNewStudents(newStudent);
		return "redirect:list";
	}
	
	/*
	 * @DeleteMapping(path= "/delete/{studentId}")
	 * 
	 * @ResponseBody public void deleteStudent(
	 * 
	 * @PathVariable("studentId") Long studentId ) {
	 * studentService.deleteStudent(studentId); }
	 */
	
	
	@PostMapping(path= "/delete")
	public String deleteStudent(
			@RequestParam Long id,
			Model model) {
		studentService.deleteStudent(id);
		return "redirect:list";
		
	}
	
	
	@GetMapping(path="/detail")
	public String updateStudent(@RequestParam Long id,
			Model model
			//RedirectAttributes redirectAttributes
			) {
		
		Student student = studentService.getStudentById(id);
		/*
		 * Student newStudent = new Student(); BeanUtils.copyProperties(student,
		 * newStudent);
		 */
		
		model.addAttribute("student",student);
		model.addAttribute("studentId", id);
		//redirectAttributes.addFlashAttribute("student",student);
		
		return "detail";
	}

	
	@PostMapping(path="/update")
	public String update(@ModelAttribute Student student, @RequestParam Long id) {
		studentService.update(id, student);
		return "redirect:list";
	}
	
	
	/*
	@PutMapping(path = "/update/{studentId}")
	@ResponseBody
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		studentService.updateStudent(studentId, name, email);
	}
	*/
}
