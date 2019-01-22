package com.camargo.cursomc.resourses.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FieldMessage(String fieldName, String message) {
		super();
		this.fieldName = fieldName;
		this.message = message;
	}

	private String fieldName;
	private String message;
	
	public FieldMessage() {
		
	}

}
