/**
 * 
 */
package com.intellect.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.intellect.utils.CustomDateSerializer;

/**
 * @author Pavan
 *
 */
public class User {

	private String id;
	@NotEmpty
	private String fName;
	@NotEmpty
	private String lName;
	@NotEmpty
	private String email;
	@NotNull
	private Integer pinCode;
	@NotEmpty
	@JsonDeserialize(using=CustomDateSerializer.class)
	private String birthDate;
	
	private Boolean isActive;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}
	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}
	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the pinCode
	 */
	public Integer getPinCode() {
		return pinCode;
	}
	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	
	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
