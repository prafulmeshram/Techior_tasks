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
import com.techior.student.util.StudentListUtil;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 12-06-2020
 * 
 *          Updated Date : 19-06-2020
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

				students = StudentListUtil.convertResultToStudent(resultSet);

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
				preparedStatement.setString(6, student.getRegistrationDate());
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

	@Override
	public List<Student> paginateStudents(Integer limit, Integer pageNumber) {
		List<Student> students = new ArrayList<Student>();
		connection = JDBCUtil.getConnection();
		pageNumber -= 1;

		try {
			Integer offset = 0;
			if (pageNumber <= 0) {
				offset = 0;
			} else {
				offset = pageNumber * limit;
			}

			PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.STUDENT_LIST_PAGE);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			students = StudentListUtil.convertResultToStudent(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}
		return students;
	}

	@Override
	public Integer getTotalResults() {
		Integer count = 0;
		connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.STUDENT_COUNT);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				count = resultSet.getInt("count");
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}

		return count;
	}

	@Override
	public List<Student> filterByFields(String firstName, String lastName, String emailId, String mobileNumber) {

		String whereClause = "";
		String query = "select * from tab_student ";

		if ((firstName.equals("") || firstName == null) && (lastName.equals("") || lastName == null)
				&& (emailId.equals("") || emailId == null) && (mobileNumber.equals("") || mobileNumber == null)) {
			whereClause = "";
		} else {
			whereClause = " WHERE ";
		}

		int count = 0;

		if (!firstName.equals("") && firstName != null) {
			count++;
			if (count != 1) {
				whereClause += " AND ";
			}

			whereClause += "first_name like " + "'%" + firstName + "%'";
		}

		if (!lastName.equals("") && lastName != null) {
			count++;
			if (count != 1) {
				whereClause += " AND ";
			}

			whereClause += "last_name like " + "'%" + lastName + "%'";
		}

		if (!emailId.equals("") && emailId != null) {
			count++;
			if (count != 1) {
				whereClause += " AND ";
			}

			whereClause += "email_id like " + "'%" + emailId + "%'";
		}

		if (!mobileNumber.equals("") && mobileNumber != null) {
			count++;
			if (count != 1) {
				whereClause += " AND ";
			}

			whereClause += "mobile_number like " + "'%" + mobileNumber + "%'";
		}

		connection = JDBCUtil.getConnection();
		query += whereClause;
		List<Student> students = new ArrayList<Student>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			students = StudentListUtil.convertResultToStudent(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);

		}
		return students;
	}

	@Override
	public List<Student> filterByDates(String fromDate, String toDate) {

		String query = "";
		List<Student> students = new ArrayList<Student>();

		if ((fromDate.equals("") || fromDate == null) && (toDate.equals("") || toDate == null)) {
			query = "select * from tab_student";
		} else if ((!fromDate.equals("") && fromDate != null) && (!toDate.equals("") && toDate != null)) {
			query = "select * from tab_student where reg_date between '" + fromDate + "' and '" + toDate + "'";
		} else if ((!fromDate.equals("") || fromDate != null) && (toDate.equals("") || toDate == null)) {
			query = "select * from tab_student where reg_date='" + fromDate + "'";
		} else if ((fromDate.equals("") || fromDate == null) && (!toDate.equals("") || toDate != null)) {
			query = "select * from tab_student where reg_date='" + toDate + "'";
		}

		connection = JDBCUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			students = StudentListUtil.convertResultToStudent(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}

		return students;
	}

}
