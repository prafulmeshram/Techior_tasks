/**
 * 
 */
package com.techior.student.service.impl;

import java.util.List;

import com.techior.student.dao.StudentDao;
import com.techior.student.dao.impl.StudentDaoImpl;
import com.techior.student.model.Student;
import com.techior.student.service.StudentService;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 14-06-2020
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

}
