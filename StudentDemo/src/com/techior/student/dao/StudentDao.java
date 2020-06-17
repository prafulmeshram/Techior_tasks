/**
 * 
 */
package com.techior.student.dao;

import java.util.List;

import com.techior.student.model.Student;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 12-06-2020
 *
 */
public interface StudentDao {

	public List<Student> findAllStudents();

	public String saveStudent(Student student);

}
