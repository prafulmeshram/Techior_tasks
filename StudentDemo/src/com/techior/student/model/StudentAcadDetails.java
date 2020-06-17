/**
 * 
 */
package com.techior.student.model;

import java.io.Serializable;

/**
 * @author PRAFUL MESHRAM
 * @version 0.1
 * 
 *          Created Date : 17-06-2020
 *
 */
public class StudentAcadDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3927791295852042934L;

	/**
	 * 
	 */
	public StudentAcadDetails() {

	}

	private Integer detId;
	private String academicYear;
	private String standard;
	private Character section;
	private String schoolName;
	private Integer studentId;

	/**
	 * @param detId
	 * @param academicYear
	 * @param standard
	 * @param section
	 * @param schoolName
	 * @param studentId
	 */
	public StudentAcadDetails(Integer detId, String academicYear, String standard, Character section, String schoolName,
			Integer studentId) {
		super();
		this.detId = detId;
		this.academicYear = academicYear;
		this.standard = standard;
		this.section = section;
		this.schoolName = schoolName;
		this.studentId = studentId;
	}

	/**
	 * @return the detId
	 */
	public Integer getDetId() {
		return detId;
	}

	/**
	 * @param detId the detId to set
	 */
	public void setDetId(Integer detId) {
		this.detId = detId;
	}

	/**
	 * @return the academicYear
	 */
	public String getAcademicYear() {
		return academicYear;
	}

	/**
	 * @param academicYear the academicYear to set
	 */
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}

	/**
	 * @return the section
	 */
	public Character getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(Character section) {
		this.section = section;
	}

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return "StudentAcadDetails [detId=" + detId + ", academicYear=" + academicYear + ", standard=" + standard
				+ ", section=" + section + ", schoolName=" + schoolName + ", studentId=" + studentId + "]";
	}

}
