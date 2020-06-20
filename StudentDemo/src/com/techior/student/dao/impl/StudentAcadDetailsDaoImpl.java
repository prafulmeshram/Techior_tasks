/**
 * 
 */
package com.techior.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techior.student.dao.StudentAcadDetailsDao;
import com.techior.student.jdbc.JDBCUtil;
import com.techior.student.jdbc.QueryMaker;
import com.techior.student.model.StudentAcadDetails;
import com.techior.student.util.Constants;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 18-06-2020
 *
 */
public class StudentAcadDetailsDaoImpl implements StudentAcadDetailsDao {

	/**
	 * 
	 */
	public StudentAcadDetailsDaoImpl() {

	}

	private Connection connection = null;

	@Override
	public StudentAcadDetails getStudentAcadDetails(Integer studentId) {

		StudentAcadDetails studentAcadDetails = new StudentAcadDetails();
		connection = JDBCUtil.getConnection();

		if (connection != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.STUD_ACAD_GET);
				preparedStatement.setInt(1, studentId);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					studentAcadDetails.setDetId(resultSet.getInt("det_id"));
					studentAcadDetails.setAcademicYear(resultSet.getString("acad_year"));
					studentAcadDetails.setStandard(resultSet.getString("standard"));
					studentAcadDetails.setSection(resultSet.getString("section"));
					studentAcadDetails.setSchoolName(resultSet.getString("school_name"));
					studentAcadDetails.setStudentId(studentId);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection(connection);
			}
		}
		return studentAcadDetails;
	}

	@Override
	public String saveStudentAcadDetails(StudentAcadDetails studentAcadDetails) {
		connection = JDBCUtil.getConnection();
		String status = Constants.STATUS_FAILED;

		if (connection != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.STUD_ACAD_INSERT);
				preparedStatement.setString(1, studentAcadDetails.getAcademicYear());
				preparedStatement.setString(2, studentAcadDetails.getStandard());
				preparedStatement.setString(3, studentAcadDetails.getSection());
				preparedStatement.setString(4, studentAcadDetails.getSchoolName());
				preparedStatement.setInt(5, studentAcadDetails.getStudentId());
				preparedStatement.executeUpdate();
				status = Constants.STATUS_SUCCESS;

			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection(connection);
			}
		}

		return status;
	}

	@Override
	public String updateStudentAcadDetails(StudentAcadDetails studentAcadDetails) {
		connection = JDBCUtil.getConnection();
		String status = Constants.STATUS_FAILED;

		if (connection != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.STUD_ACAD_UPDATE);
				preparedStatement.setString(1, studentAcadDetails.getAcademicYear());
				preparedStatement.setString(2, studentAcadDetails.getStandard());
				preparedStatement.setString(3, studentAcadDetails.getSection());
				preparedStatement.setString(4, studentAcadDetails.getSchoolName());
				preparedStatement.setInt(5, studentAcadDetails.getStudentId());
				preparedStatement.executeUpdate();
				status = Constants.STATUS_SUCCESS;

			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection(connection);
			}
		}

		return status;
	}

}
