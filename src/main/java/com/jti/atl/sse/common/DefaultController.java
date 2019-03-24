package com.jti.atl.sse.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jti.atl.sse.exception.ResourceNotFoundException;

@RestController
public class DefaultController {
	@RequestMapping
	public ResponseEntity<?> handleUnmappedRequest(final HttpServletRequest request) {
		throw new ResourceNotFoundException("Invalid resource request: " + request.getRequestURL().toString());
	}
}
