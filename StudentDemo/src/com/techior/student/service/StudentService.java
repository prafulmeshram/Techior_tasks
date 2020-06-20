/**
 * 
 */
package com.techior.student.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.techior.student.model.Student;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 14-06-2020
 * 
 *          Updated Date : 19-06-2020
 *
 */
public interface StudentService {

	public List<Student> findAllStudents();

	public String saveStudent(Student student);

	public JSONObject paginateStudents(Integer limit, Integer pageNumber);

	public List<Student> filterByFields(String firstName, String lastName, String emailId, String mobileNumber);

	public List<Student> filterByDates(String fromDate, String toDate);

}
