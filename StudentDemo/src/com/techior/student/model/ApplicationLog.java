/**
 * 
 */
package com.techior.student.model;

import java.io.Serializable;

/**
 * @author jack
 *
 */
public class ApplicationLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7995255162428175580L;

	/**
	 * 
	 */
	public ApplicationLog() {
		// TODO Auto-generated constructor stub
	}

	private String eventName;
	private String time;

	/**
	 * @param eventName
	 * @param time
	 */
	public ApplicationLog(String eventName, String time) {
		super();
		this.eventName = eventName;
		this.time = time;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

}
