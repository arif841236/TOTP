package com.indusnet.exception;

/**
 * This class for exception and 
 * its extends to runtime exception
 */
public class OtpException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	public OtpException(){

	}
	public OtpException(String msg){
		super(msg);
	}
}
