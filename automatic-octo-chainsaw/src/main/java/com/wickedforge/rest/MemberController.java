package com.wickedforge.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.wickedforge.service.CreateIdentifierService;


@RestController
public class MemberController {
	
	@Autowired
	CreateIdentifierService createIdentifierService;
    
	@GetMapping("/welcome/user")
    public String welcomeUser() {
        return "You did it boss!";
    }
	
	@GetMapping(value = "/id/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Identifier createIdentifier() {
		Identifier identifier = new Identifier();
		identifier.setIdentifier(getCreateIdentifierService().createIdentifier());
		identifier.setFriendlyIdentifier(getCreateIdentifierService().createFriendlyIdentifier(identifier.getIdentifier()));
		return identifier;
    }
	
	@GetMapping(value = "/id/group/create/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Identifier> createGroupOfIdentifiers(@PathVariable("value") int value) {
		List<Identifier> identifierList = new ArrayList<Identifier>();
		for(int i = 0; i < value; i++) {
			Identifier identifier = new Identifier();
			identifier.setIdentifier(getCreateIdentifierService().createIdentifier());
			identifier.setFriendlyIdentifier(getCreateIdentifierService().createFriendlyIdentifier(identifier.getIdentifier()));
			
			identifierList.add(identifier);
		}
		
		return identifierList;
    }
	
	@GetMapping(value = "/id/dupe-challenge")
    public String findDuplicateChallenge() {
		Map<String, Identifier> idMap = new HashMap<String, Identifier>();
		String firstDupe = "No dupes happened";
		int counter = 0;
		for(int i = 0; i < 1000000; i++) {
			Identifier identifier = new Identifier();
			identifier.setIdentifier(getCreateIdentifierService().createIdentifier());
			identifier.setFriendlyIdentifier(getCreateIdentifierService().createFriendlyIdentifier(identifier.getIdentifier()));
			if(!idMap.containsKey(identifier.getIdentifier())) {
				idMap.put(identifier.getIdentifier(), identifier);
				counter++;
				/*System.out.println(identifier.getFriendlyIdentifier());*/
			} else {
				firstDupe = identifier.getIdentifier() + " happened at counter" + counter;
				break;
			}
		}
		
		return firstDupe + "!";
    }

	public CreateIdentifierService getCreateIdentifierService() {
		return createIdentifierService;
	}

	public void setCreateIdentifierService(CreateIdentifierService createIdentifierService) {
		this.createIdentifierService = createIdentifierService;
	}
}
