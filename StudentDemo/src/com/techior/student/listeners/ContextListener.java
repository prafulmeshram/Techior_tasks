/**
 * 
 */
package com.techior.student.listeners;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.techior.student.model.ApplicationLog;
import com.techior.student.service.ApplogService;
import com.techior.student.service.MailService;
import com.techior.student.service.impl.ApplogServiceImpl;
import com.techior.student.service.impl.MailServiceImpl;
import com.techior.student.util.Constants;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 14-06-2020
 *
 */
public class ContextListener implements ServletContextListener {

	/**
	 * 
	 */
	public ContextListener() {

	}

	private MailService mailService = new MailServiceImpl();
	private ApplogService applogService = new ApplogServiceImpl();

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		String message = "Hello Sir / Madam, Your Application " + sce.getServletContext().getContextPath()
				+ " is Terminated /  Ended At " + new Date().toString() + "Please Contact To Support For Queries ";

		String status = this.applogService.saveApplicationLog(new ApplicationLog("end", new Date().toString()));
		boolean sent = this.mailService.sendApplogMail("Application End Event", message);
		if (status.equalsIgnoreCase(Constants.STATUS_FAILED)) {
			System.out.println("Error While Saving Application Log");
		}

		if (!sent) {
			System.out.println("Error Occured While Sending Email");
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		String message = "Hello Sir / Madam, Your Application " + sce.getServletContext().getContextPath()
				+ " is Started  At " + new Date().toString() + "Please Contact To Support For Any Queries ";
		String status = this.applogService.saveApplicationLog(new ApplicationLog("start", new Date().toString()));
		boolean sent = this.mailService.sendApplogMail("Application Start Event", message);

		if (status.equalsIgnoreCase(Constants.STATUS_FAILED)) {
			System.out.println("Error While Saving Application Log");
		}

		if (!sent) {
			System.out.println("Error Occured While Sending Email");
		}

	}

}
