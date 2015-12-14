package sckj.soams.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HostController {
	
	@RequestMapping("/hosts")
	public String hosts(){
		return "views/host";
	}
}
