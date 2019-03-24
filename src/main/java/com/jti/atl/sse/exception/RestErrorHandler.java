package com.jti.atl.sse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleMovieNotFoundException(final ResourceNotFoundException ex) {
		return handleExceptionInternal(ex, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OperationFailedException.class)
	public ResponseEntity<Object> handleRuntimeException(final OperationFailedException ex) {
		return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<Object> handleApplicationException(final ApplicationException ex) {
		return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(final RuntimeException ex) {
		return handleExceptionInternal(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<Object> handleUnsupportedOperationException(final UnsupportedOperationException ex) {

		return handleExceptionInternal(ex, HttpStatus.NOT_IMPLEMENTED);
	}

	private ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpStatus status) {
		return ResponseEntity.status(status).body(ex.getMessage());
	}

}
