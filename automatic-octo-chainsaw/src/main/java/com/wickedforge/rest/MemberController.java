package com.wickedforge.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    
	@GetMapping("/welcome/user")
    public String welcomeUser() {
        return "You did it boss!";
    }
}
