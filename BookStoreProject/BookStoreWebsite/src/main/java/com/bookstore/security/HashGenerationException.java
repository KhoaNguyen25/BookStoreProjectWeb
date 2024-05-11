package com.bookstore.security;

public class HashGenerationException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4540961868531389305L;
	public HashGenerationException(String message) {
		super(message);
	}

	public HashGenerationException(String message, Throwable cause) {
		super(message, cause);
	}

}
