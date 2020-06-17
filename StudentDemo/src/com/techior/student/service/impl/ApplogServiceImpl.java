/**
 * 
 */
package com.techior.student.service.impl;

import java.util.List;

import com.techior.student.dao.ApplogDao;
import com.techior.student.dao.impl.ApplogDaoImpl;
import com.techior.student.model.ApplicationLog;
import com.techior.student.service.ApplogService;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date ; 14-06-2020
 *
 */
public class ApplogServiceImpl implements ApplogService {

	/**
	 * 
	 */
	public ApplogServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	private ApplogDao applogDao = new ApplogDaoImpl();

	@Override
	public List<ApplicationLog> findAllAppLogs() {

		return this.applogDao.findAllAppLogs();
	}

	@Override
	public String saveApplicationLog(ApplicationLog applicationLog) {

		return this.applogDao.saveApplicationLog(applicationLog);
	}

}
