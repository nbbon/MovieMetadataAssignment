package com.jti.atl.sse.exception;

public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException(String message) {
		super("Application exception - " + message);
		// TODO Auto-generated constructor stub
	}
}
