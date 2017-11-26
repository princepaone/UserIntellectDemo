/**
 * 
 */
package com.intellect.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author Pavan
 *
 */
public class BaseController {

	public List<com.intellect.model.ValidationError> getValidationErrors(BindingResult bindingResult){
		List<com.intellect.model.ValidationError> valErrors = new ArrayList<>();
		if(bindingResult.hasErrors()) {
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for(FieldError error : fieldErrors) {
				valErrors.add(new com.intellect.model.ValidationError
						(error.getCode(),error.getField(), "Entered "+error.getField()+" is invalid.Please enter a valid value"));
			}
		}
		return valErrors;

	}
}
