/**
 * 
 */
package com.techior.student.service.impl;

import com.techior.student.dao.StudentAcadDetailsDao;
import com.techior.student.dao.impl.StudentAcadDetailsDaoImpl;
import com.techior.student.model.StudentAcadDetails;
import com.techior.student.service.StudentAcadDetailsService;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date :18-06-2020
 *
 */
public class StudentAcadDetailsServiceImpl implements StudentAcadDetailsService {

	/**
	 * 
	 */
	public StudentAcadDetailsServiceImpl() {

	}

	private StudentAcadDetailsDao acadDetailsDao = new StudentAcadDetailsDaoImpl();

	@Override
	public StudentAcadDetails getStudentAcadDetails(Integer studentId) {

		return this.acadDetailsDao.getStudentAcadDetails(studentId);
	}

	@Override
	public String saveStudentAcadDetails(StudentAcadDetails studentAcadDetails) {

		return this.acadDetailsDao.saveStudentAcadDetails(studentAcadDetails);
	}

	@Override
	public String updateStudentAcadDetails(StudentAcadDetails studentAcadDetails) {

		return this.acadDetailsDao.updateStudentAcadDetails(studentAcadDetails);
	}

}
