/**
 * 
 */
package com.techior.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techior.student.dao.StudentDao;
import com.techior.student.jdbc.JDBCUtil;
import com.techior.student.jdbc.QueryMaker;
import com.techior.student.model.Student;
import com.techior.student.util.Constants;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 12-06-2020
 *
 */
public class StudentDaoImpl implements StudentDao {

	private Connection connection = null;

	@Override
	public List<Student> findAllStudents() {
		List<Student> students = new ArrayList<Student>();

		connection = JDBCUtil.getConnection();
		if (connection != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.STUDENT_LIST);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Student student = new Student();
					student.setStudentId(resultSet.getInt("student_id"));
					student.setFirstName(resultSet.getString("first_name"));
					student.setLastName(resultSet.getString("last_name"));
					student.setMobileNumber(resultSet.getString("mobile_number"));
					student.setEmailId(resultSet.getString("email_id"));
					student.setImageName(resultSet.getString("image_name"));
					students.add(student);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection(connection);
			}
		}

		return students;
	}

	@Override
	public String saveStudent(Student student) {

		String status = Constants.STATUS_FAILED;
		connection = JDBCUtil.getConnection();

		if (connection != null) {

			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.STUDENT_INSERT);
				preparedStatement.setString(1, student.getFirstName());
				preparedStatement.setString(2, student.getLastName());
				preparedStatement.setString(3, student.getMobileNumber());
				preparedStatement.setString(4, student.getEmailId());
				preparedStatement.setString(5, student.getImageName());
				preparedStatement.executeUpdate();
				status = Constants.STATUS_SUCCESS;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection(connection);
			}

		}
		return status;
	}

}
