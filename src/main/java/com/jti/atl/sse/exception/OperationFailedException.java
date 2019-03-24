package com.jti.atl.sse.exception;

public class OperationFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationFailedException(String message) {
		super("Operation failed - " + message);
	}
}
