/**
 * 
 */
package com.techior.student.actions;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.techior.student.model.Student;
import com.techior.student.service.StudentService;
import com.techior.student.service.impl.StudentServiceImpl;
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
public class StudentAction extends ActionSupport implements ModelDriven<Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6956402657178146913L;

	HttpServletRequest httpServletRequest = (HttpServletRequest) ActionContext.getContext()
			.get(ServletActionContext.HTTP_REQUEST);
	String jsonResponse;
	String jsonStudentList;
	String jsonStudentPageList;
	String jsonStudentFieldSearch;
	String jsonStudentDateSearch;
	Student student = new Student();
	String filePath = httpServletRequest.getSession().getServletContext().getRealPath("/");
	List<Student> students = new ArrayList<Student>();
	private StudentService studentService = new StudentServiceImpl();

	private String firstNameSearch;
	private String lastNameSearch;
	private String emailIdSearch;
	private String mobileNumberSearch;

	private String fromDate;
	private String toDate;

	@SuppressWarnings("unchecked")
	public String execute() throws IOException {
		if (student.getImage() != null) {
			File fileToCreate = new File(filePath, student.getImageName());
			FileUtils.copyFile(student.getImage(), fileToCreate);
		}
		student.setRegistrationDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		String status = this.studentService.saveStudent(student);
		if (status.equalsIgnoreCase(Constants.STATUS_SUCCESS)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "Student Registered Successfully");
			jsonResponse = jsonObject.toJSONString();
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String getStudentList() {
		students = this.studentService.findAllStudents();
		jsonStudentList = StudentListUtil.convertStudentListToJson(students).toJSONString();
		return SUCCESS;
	}

	public String paginateStudents() {
		Integer pageNumber = 0;
		Integer limit = 0;

		pageNumber = Integer.parseInt(httpServletRequest.getParameter("limit"));
		limit = Integer.parseInt(httpServletRequest.getParameter("pageNumber"));

		JSONObject jsonObject = new JSONObject();
		jsonObject = this.studentService.paginateStudents(pageNumber, limit);

		jsonStudentPageList = jsonObject.toJSONString();

		return SUCCESS;
	}

	public String searchByFields() {

		List<Student> students = new ArrayList<Student>();

		students = this.studentService.filterByFields(firstNameSearch, lastNameSearch, emailIdSearch,
				mobileNumberSearch);

		jsonStudentFieldSearch = StudentListUtil.convertStudentListToJson(students).toJSONString();
		return SUCCESS;
	}

	public String searchByDates() throws ParseException {
		List<Student> students = new ArrayList<Student>();

		students = this.studentService.filterByDates(fromDate, toDate);

		jsonStudentDateSearch = StudentListUtil.convertStudentListToJson(students).toJSONString();
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

	/**
	 * @return the jsonStudentPageList
	 */
	public String getJsonStudentPageList() {
		return jsonStudentPageList;
	}

	/**
	 * @param jsonStudentPageList the jsonStudentPageList to set
	 */
	public void setJsonStudentPageList(String jsonStudentPageList) {
		this.jsonStudentPageList = jsonStudentPageList;
	}

	/**
	 * @return the studentService
	 */
	public StudentService getStudentService() {
		return studentService;
	}

	/**
	 * @param studentService the studentService to set
	 */
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * @return the jsonStudentFieldSearch
	 */
	public String getJsonStudentFieldSearch() {
		return jsonStudentFieldSearch;
	}

	/**
	 * @param jsonStudentFieldSearch the jsonStudentFieldSearch to set
	 */
	public void setJsonStudentFieldSearch(String jsonStudentFieldSearch) {
		this.jsonStudentFieldSearch = jsonStudentFieldSearch;
	}

	/**
	 * @return the jsonStudentDateSearch
	 */
	public String getJsonStudentDateSearch() {
		return jsonStudentDateSearch;
	}

	/**
	 * @param jsonStudentDateSearch the jsonStudentDateSearch to set
	 */
	public void setJsonStudentDateSearch(String jsonStudentDateSearch) {
		this.jsonStudentDateSearch = jsonStudentDateSearch;
	}

	/**
	 * @return the firstNameSearch
	 */
	public String getFirstNameSearch() {
		return firstNameSearch;
	}

	/**
	 * @param firstNameSearch the firstNameSearch to set
	 */
	public void setFirstNameSearch(String firstNameSearch) {
		this.firstNameSearch = firstNameSearch;
	}

	/**
	 * @return the lastNameSearch
	 */
	public String getLastNameSearch() {
		return lastNameSearch;
	}

	/**
	 * @param lastNameSearch the lastNameSearch to set
	 */
	public void setLastNameSearch(String lastNameSearch) {
		this.lastNameSearch = lastNameSearch;
	}

	/**
	 * @return the emailIdSearch
	 */
	public String getEmailIdSearch() {
		return emailIdSearch;
	}

	/**
	 * @param emailIdSearch the emailIdSearch to set
	 */
	public void setEmailIdSearch(String emailIdSearch) {
		this.emailIdSearch = emailIdSearch;
	}

	/**
	 * @return the mobileNumberSearch
	 */
	public String getMobileNumberSearch() {
		return mobileNumberSearch;
	}

	/**
	 * @param mobileNumberSearch the mobileNumberSearch to set
	 */
	public void setMobileNumberSearch(String mobileNumberSearch) {
		this.mobileNumberSearch = mobileNumberSearch;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
