/**
 * 
 */
package com.techior.student.jdbc;

/**
 * @author PRAFUL MESHRAM
 * 
 * @version 0.1
 * 
 *          Created Date : 12-06-2020
 *          
 *          Updated Date : 14-06-2020
 *
 */
public class QueryMaker {

	public static final String CREATE_STUDENT_TABLE = "create table tab_student(student_id int PRIMARY KEY AUTO_INCREMENT, first_name varchar(50), last_name varchar(50), mobile_number varchar(15), email_id varchar(100), image_name varchar(100))";
	public static final String CREATE_APPLOG_TABLE = "create table tab_app_log(log_id int PRIMARY KEY AUTO_INCREMENT, event_name varchar(50), time varchar(150))";

	public static final String APPLOG_INSERT = "insert into tab_app_log(event_name, time)value(?,?)";
	public static final String APPLOG_LIST = "select * from tab_app_log";

	public static final String STUDENT_INSERT = "insert into tab_student(first_name,last_name,mobile_number,email_id,image_name)value(?,?,?,?,?)";
	public static final String STUDENT_LIST = "select * from tab_student";
}
