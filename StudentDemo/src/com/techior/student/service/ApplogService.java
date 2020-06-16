/**
 * 
 */
package com.techior.student.service;

import java.util.List;

import com.techior.student.model.ApplicationLog;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 14-06-2020
 *
 */
public interface ApplogService {

	public List<ApplicationLog> findAllAppLogs();

	public String saveApplicationLog(ApplicationLog applicationLog);

}
