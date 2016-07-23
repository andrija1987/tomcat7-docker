package si.asoft.bugitracker.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
  
	private static Logger log = Logger.getLogger(LoginController.class);
		
	@RequestMapping(value="/login", method=RequestMethod.GET) 
	public String login() {
		log.debug("login()");
		return "login";
	}
}

