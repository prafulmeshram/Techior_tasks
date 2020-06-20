/**
 * 
 */
package com.techior.student.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.techior.student.model.StudentAcadDetails;
import com.techior.student.service.StudentAcadDetailsService;
import com.techior.student.service.impl.StudentAcadDetailsServiceImpl;
import com.techior.student.util.Constants;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 18-06-2020
 *
 */
public class StudentAcadDetAction extends ActionSupport implements ModelDriven<StudentAcadDetails> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1376880812887590885L;

	private StudentAcadDetailsService acadDetailsService = new StudentAcadDetailsServiceImpl();
	private StudentAcadDetails studentAcadDetails = new StudentAcadDetails();
	private String saveResponseJson;
	private Integer studentId;
	private String studentAcadDetailsJson;
	private HttpServletRequest httpServletRequest = (HttpServletRequest) ActionContext.getContext()
			.get(ServletActionContext.HTTP_REQUEST);

	@SuppressWarnings("unchecked")
	public String getAcadDetails() {
		studentId = Integer.parseInt(httpServletRequest.getParameter("studentId"));
		studentAcadDetails = this.acadDetailsService.getStudentAcadDetails(studentId);
		JSONObject jsonObject = new JSONObject();
		if (studentAcadDetails.getDetId() != null) {
			jsonObject.put("detId", studentAcadDetails.getDetId());
			jsonObject.put("academicYear", studentAcadDetails.getAcademicYear());
			jsonObject.put("standard", studentAcadDetails.getStandard());
			jsonObject.put("section", studentAcadDetails.getSection());
			jsonObject.put("schoolName", studentAcadDetails.getSchoolName());
			jsonObject.put("studentId", studentAcadDetails.getStudentId());
		} else {
			jsonObject.put("detId", 0);
		}
		studentAcadDetailsJson = jsonObject.toJSONString();
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String execute() {

		/*
		 * System.out.println("Det Id==" + httpServletRequest.getParameter("detId"));
		 * System.out.println("Academic Year==" +
		 * httpServletRequest.getParameter("academicYear"));
		 * System.out.println("Standard==" +
		 * httpServletRequest.getParameter("standard")); System.out.println("Section=="
		 * + httpServletRequest.getParameter("section"));
		 * System.out.println("School Name==" +
		 * httpServletRequest.getParameter("schoolName"));
		 * System.out.println("Student Id==" +
		 * httpServletRequest.getParameter("studentId"));
		 */

		studentAcadDetails.setDetId(Integer.parseInt(httpServletRequest.getParameter("detId")));
		studentAcadDetails.setAcademicYear(httpServletRequest.getParameter("academicYear"));
		studentAcadDetails.setStandard(httpServletRequest.getParameter("standard"));
		studentAcadDetails.setSection(httpServletRequest.getParameter("section"));
		studentAcadDetails.setSchoolName(httpServletRequest.getParameter("schoolName"));
		studentAcadDetails.setStudentId(Integer.parseInt(httpServletRequest.getParameter("studentId")));

		if (studentAcadDetails.getDetId() == 0) {
			String status = this.acadDetailsService.saveStudentAcadDetails(studentAcadDetails);
			if (status.equalsIgnoreCase(Constants.STATUS_SUCCESS)) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("message", "Data Saved Successfully.");
				saveResponseJson = jsonObject.toJSONString();
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("message", "Error Occured While Saving Data.");
				saveResponseJson = jsonObject.toJSONString();
			}
		} else {
			String status = this.acadDetailsService.updateStudentAcadDetails(studentAcadDetails);
			if (status.equalsIgnoreCase(Constants.STATUS_SUCCESS)) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("message", "Data Updated Successfully.");
				saveResponseJson = jsonObject.toJSONString();
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("message", "Error Occured While Updating Data.");
				saveResponseJson = jsonObject.toJSONString();
			}
		}
		return SUCCESS;
	}

	/**
	 * @return the acadDetailsService
	 */
	public StudentAcadDetailsService getAcadDetailsService() {
		return acadDetailsService;
	}

	/**
	 * @param acadDetailsService the acadDetailsService to set
	 */
	public void setAcadDetailsService(StudentAcadDetailsService acadDetailsService) {
		this.acadDetailsService = acadDetailsService;
	}

	/**
	 * @return the studentAcadeDetails
	 */
	public StudentAcadDetails getStudentAcadDetails() {
		return studentAcadDetails;
	}

	/**
	 * @param studentAcadeDetails the studentAcadeDetails to set
	 */
	public void setStudentAcadDetails(StudentAcadDetails studentAcadDetails) {
		this.studentAcadDetails = studentAcadDetails;
	}

	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the studentAcadDetailsJson
	 */
	public String getStudentAcadDetailsJson() {
		return studentAcadDetailsJson;
	}

	/**
	 * @param studentAcadDetailsJson the studentAcadDetailsJson to set
	 */
	public void setStudentAcadDetailsJson(String studentAcadDetailsJson) {
		this.studentAcadDetailsJson = studentAcadDetailsJson;
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
	 * @return the saveResponseJson
	 */
	public String getSaveResponseJson() {
		return saveResponseJson;
	}

	/**
	 * @param saveResponseJson the saveResponseJson to set
	 */
	public void setSaveResponseJson(String saveResponseJson) {
		this.saveResponseJson = saveResponseJson;
	}

	@Override
	public StudentAcadDetails getModel() {
		// TODO Auto-generated method stub
		return studentAcadDetails;
	}

}
