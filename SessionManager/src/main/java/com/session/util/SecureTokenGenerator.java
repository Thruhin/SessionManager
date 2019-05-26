package com.session.util;

import java.security.SecureRandom;

public class SecureTokenGenerator {
	
	
	 private static final String ALLOWED_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	    /**
	     * Generates a secure random token of the desired length
	     *
	     * @return the generated token
	     */
	    public String generateToken(int tokenLength) {
	        SecureRandom random = new SecureRandom();
	        StringBuilder tokenBuilder = new StringBuilder();

	        int bounds = ALLOWED_CHARACTERS.length();

	        for (int i = 0; i < tokenLength; i++) {
	            tokenBuilder.append(ALLOWED_CHARACTERS.charAt(random.nextInt(bounds)));
	        }

	        return tokenBuilder.toString();
	    }

}
