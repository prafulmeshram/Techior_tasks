/**
 * 
 */
package com.techior.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.techior.student.dao.StudentDao;
import com.techior.student.dao.impl.StudentDaoImpl;
import com.techior.student.model.Student;
import com.techior.student.service.StudentService;
import com.techior.student.util.StudentListUtil;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 14-06-2020
 * 
 *          Updated Date : 19-06-2020
 *
 */
public class StudentServiceImpl implements StudentService {

	/**
	 * 
	 */
	public StudentServiceImpl() {

	}

	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public List<Student> findAllStudents() {

		return this.studentDao.findAllStudents();
	}

	@Override
	public String saveStudent(Student student) {

		return this.studentDao.saveStudent(student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject paginateStudents(Integer limit, Integer pageNumber) {
		List<Student> students = new ArrayList<Student>();
		JSONObject jsonObject = new JSONObject();
		Integer totalResults = this.studentDao.getTotalResults();
		Integer numberOfPages = 0;
		numberOfPages = totalResults / limit;
		Integer mod = totalResults % limit;
		if (mod > 0) {
			numberOfPages += 1;
		}
		students = this.studentDao.paginateStudents(limit, pageNumber);

		jsonObject.put("totalPages", numberOfPages);
		jsonObject.put("totalResults", totalResults);
		jsonObject.put("currentPage", pageNumber);
		jsonObject.put("limit", limit);
		jsonObject.put("students", StudentListUtil.convertStudentListToJson(students));

		return jsonObject;

	}

	@Override
	public List<Student> filterByFields(String firstName, String lastName, String emailId, String mobileNumber) {

		return this.studentDao.filterByFields(firstName, lastName, emailId, mobileNumber);

	}

	@Override
	public List<Student> filterByDates(String fromDate, String toDate) {
		return this.studentDao.filterByDates(fromDate, toDate);
	}

}
