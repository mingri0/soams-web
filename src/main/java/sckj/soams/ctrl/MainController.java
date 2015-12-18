package sckj.soams.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/main")
    public String main(){
        return "views/main";
    }
    
    @RequestMapping("/skinconfig")
    public String skinConfig(){
        return "setting";
    }
}
