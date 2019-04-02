package com.meter.adapter.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MeterNotFoundException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8560503103505549291L;
	
	public MeterNotFoundException(String exception) {
		    super(exception);
		  }
    
}
