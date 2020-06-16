/**
 * 
 */
package com.techior.student.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.techior.student.dao.StudentDao;
import com.techior.student.dao.impl.StudentDaoImpl;
import com.techior.student.model.Student;
import com.techior.student.util.Constants;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 12-06-2020
 * 
 * 
 *
 */
public class StudentAction extends ActionSupport implements ModelDriven<Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6956402657178146913L;

	HttpServletRequest httpServletRequest = (HttpServletRequest) ActionContext.getContext()
			.get(ServletActionContext.HTTP_REQUEST);
	String jsonResponse;
	String jsonStudentList;
	Student student = new Student();
	String filePath = httpServletRequest.getSession().getServletContext().getRealPath("/");
	List<Student> students = new ArrayList<Student>();
	

	private StudentDao studentDao = new StudentDaoImpl();
	Gson gson = new Gson();

	@SuppressWarnings("unchecked")
	public String execute() throws IOException {

		if (student.getImage() != null) {
			File fileToCreate = new File(filePath, student.getImageName());
			FileUtils.copyFile(student.getImage(), fileToCreate);
		}

		String status = this.studentDao.saveStudent(student);

		if (status.equalsIgnoreCase(Constants.STATUS_SUCCESS)) {

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "Student Registered Successfully");

			jsonResponse = jsonObject.toJSONString();
		
			return SUCCESS;
		} else {
		
			return INPUT;
		}
	}

	@SuppressWarnings("unchecked")
	public String getStudentList() {

		students = this.studentDao.findAllStudents();

		JSONArray jsonArray = new JSONArray();

		for (Student student : students) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("firstName", student.getFirstName());
			jsonObject.put("lastName", student.getLastName());
			jsonObject.put("emailId", student.getEmailId());
			jsonObject.put("mobileNumber", student.getMobileNumber());
			jsonObject.put("imageName", student.getImageName());
			jsonArray.add(jsonObject);
		}

		jsonStudentList = jsonArray.toJSONString();
		return SUCCESS;
	}

	@Override
	public Student getModel() {

		return student;
	}

	/**
	 * @return the httpServletRequest
	 */
	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	/**
	 * @param httpServletRequest the httpServletRequest to set
	 */
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	/**
	 * @return the jsonResponse
	 */
	public String getJsonResponse() {
		return jsonResponse;
	}

	/**
	 * @param jsonResponse the jsonResponse to set
	 */
	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}

	/**
	 * @return the jsonStudentList
	 */
	public String getJsonStudentList() {
		return jsonStudentList;
	}

	/**
	 * @param jsonStudentList the jsonStudentList to set
	 */
	public void setJsonStudentList(String jsonStudentList) {
		this.jsonStudentList = jsonStudentList;
	}

	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the students
	 */
	public List<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
