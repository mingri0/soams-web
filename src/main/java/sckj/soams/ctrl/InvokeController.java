package sckj.soams.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sckj.soams.bean.InvokeBean;
import sckj.soams.bean.PageBean;
import sckj.soams.service.InvokeChainService;

@Controller
public class InvokeController {
	
	@RequestMapping("/invoke")
	public String invoke(){
		return "views/invoke";
	}
	
	@RequestMapping("/invokemore")
	public String invokemore(){
		return "views/invokemore";
	}
	
}
