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

import com.techior.student.dao.ApplogDao;
import com.techior.student.jdbc.JDBCUtil;
import com.techior.student.jdbc.QueryMaker;
import com.techior.student.model.ApplicationLog;
import com.techior.student.util.Constants;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 14-06-2020
 *
 */
public class ApplogDaoImpl implements ApplogDao {

	/**
	 * 
	 */
	public ApplogDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	private Connection connection = null;

	@Override
	public List<ApplicationLog> findAllAppLogs() {
		List<ApplicationLog> logs = new ArrayList<ApplicationLog>();
		connection = JDBCUtil.getConnection();
		if (connection != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.APPLOG_LIST);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					logs.add(new ApplicationLog(resultSet.getString("event_name"), resultSet.getString("time")));
				}

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				JDBCUtil.closeConnection(connection);
			}
		}

		return logs;
	}

	@Override
	public String saveApplicationLog(ApplicationLog applicationLog) {

		String status = Constants.STATUS_FAILED;
		connection = JDBCUtil.getConnection();
		if (connection != null) {
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(QueryMaker.APPLOG_INSERT);
				preparedStatement.setString(1, applicationLog.getEventName());
				preparedStatement.setString(2, applicationLog.getTime());
				preparedStatement.executeUpdate();
				status = Constants.STATUS_SUCCESS;
			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				JDBCUtil.closeConnection(connection);
			}

			return status;
		} else {
			return status;
		}
	}

}
