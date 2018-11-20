package com.wickedforge.service;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CreateIdentifierService {
	public static final Integer ID_LENGTH = 12; // length of the random string.
	
	public String createIdentifier() {
		String chars = "ABCDEFGHJKLMNPRSTUVWXYZ23456789";
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();
        while (stringBuilder.length() < ID_LENGTH) { 
        	int index = (int) (rnd.nextFloat() * chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        String identifier = stringBuilder.toString();
		return identifier;
	}
	
	public String createFriendlyIdentifier(String identifier) {
		String friendlyIdentifier;
		friendlyIdentifier = identifier.substring(0, 4) + "-" + identifier.substring(4, 8) + "-" + identifier.substring(8, 12);
		return friendlyIdentifier;
	}
}
