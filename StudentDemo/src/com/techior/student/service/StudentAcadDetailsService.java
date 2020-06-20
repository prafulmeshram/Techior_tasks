/**
 * 
 */
package com.techior.student.service;

import com.techior.student.model.StudentAcadDetails;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 18-06-2020
 * 
 *
 */
public interface StudentAcadDetailsService {

	public StudentAcadDetails getStudentAcadDetails(Integer studentId);

	public String saveStudentAcadDetails(StudentAcadDetails studentAcadDetails);

	public String updateStudentAcadDetails(StudentAcadDetails studentAcadDetails);

}
