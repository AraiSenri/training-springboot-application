package com.example.demo.student;



import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Student {
	
	@Id
	@SequenceGenerator(
			name="student_sequence",
			sequenceName="student_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence"
			)
	private Long id;
	
	@Nonnull
	private String name;
	private String email;
	
	/*
	 * 出身地
	 */
	private String birthplace;
	/*
	 * 生年月日
	 * yyyy/mm/dd
	 */
	private String dateOfBirth;
	/*
	 * スキル
	 * 職務 - 経験年数の箇条書き
	 */
	private String skill;
	
	/*
	 * プロフィール
	 * 自由書き自己紹介
	 */
	private String profile;
	
	public Student() {
		
	}
	
	public Student(Long id, String name, String email) {
		this.id = id;
		this.email = email;
		this.name = name;
	}

	public Student( String name, String email) {
		
		this.email = email;
		this.name = name;
	}
	
	public Student(String name, String email, String dateOfBirth, String birthplace,
			String skill, String profile) {
		
		this.name=name;
		this.email=email;
		this.dateOfBirth = dateOfBirth;
		this.birthplace=birthplace;
		this.skill=skill;
		this.profile=profile; 
		
	}
	
 	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}


	
	public Long getId() {
		return this.id;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}
}
