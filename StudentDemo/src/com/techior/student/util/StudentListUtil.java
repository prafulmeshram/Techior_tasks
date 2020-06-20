/**
 * 
 */
package com.techior.student.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.techior.student.model.Student;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 19-06-2020
 *
 *
 *          <p>
 *          Student List Util Converts ResultSet Into Array List Of Students
 *          </p>
 */
public class StudentListUtil {

	/**
	 * 
	 */
	public StudentListUtil() {

	}

	public static List<Student> convertResultToStudent(ResultSet resultSet) throws SQLException {
		List<Student> students = new ArrayList<Student>();
		while (resultSet.next()) {
			Student student = new Student();
			student.setStudentId(resultSet.getInt("student_id"));
			student.setFirstName(resultSet.getString("first_name"));
			student.setLastName(resultSet.getString("last_name"));
			student.setMobileNumber(resultSet.getString("mobile_number"));
			student.setEmailId(resultSet.getString("email_id"));
			student.setImageName(resultSet.getString("image_name"));
			student.setRegistrationDate(resultSet.getString("reg_date"));
			students.add(student);
		}

		return students;
	}

	@SuppressWarnings("unchecked")
	public static JSONArray convertStudentListToJson(List<Student> students) {
		JSONArray jsonArray = new JSONArray();
		for (Student student : students) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("studentId", student.getStudentId());
			jsonObject.put("firstName", student.getFirstName());
			jsonObject.put("lastName", student.getLastName());
			jsonObject.put("emailId", student.getEmailId());
			jsonObject.put("mobileNumber", student.getMobileNumber());
			jsonObject.put("imageName", student.getImageName());
			jsonObject.put("registrationDate", student.getRegistrationDate());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

}
