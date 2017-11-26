package com.intellect.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Custom Message object
 * @author Pavan
 *
 */
@JsonInclude(content=Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class GenericMessage {

	private String resMsg;
	
	private String userId;
	
	private List<ValidationError> valErrors;

	/**
	 * @return the resMsg
	 */
	public String getResMsg() {
		return resMsg;
	}

	/**
	 * @param resMsg the resMsg to set
	 */
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public List<ValidationError> getValErrors() {
		return valErrors;
	}

	public void setValErrors(List<ValidationError> valErrors) {
		this.valErrors = valErrors;
	}

	public GenericMessage(String resMsg, String userId) {
		super();
		this.resMsg = resMsg;
		this.userId = userId;
	}

	public GenericMessage(String resMsg, String userId, List<ValidationError> valErrors) {
		super();
		this.resMsg = resMsg;
		this.userId = userId;
		this.valErrors = valErrors;
	}

	
	

}