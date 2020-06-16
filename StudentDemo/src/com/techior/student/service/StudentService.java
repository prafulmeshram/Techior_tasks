/**
 * 
 */
package com.techior.student.service;

import java.util.List;

import com.techior.student.model.Student;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 14-06-2020
 *
 */
public interface StudentService {

	public List<Student> findAllStudents();

	public String saveStudent(Student student);

}
