package kr.ac.shingu.soft.appfrm.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {
    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    public LoginController() {
    }

    @GetMapping("")
    public String index() {
        this.log.debug("index");
        return "index";
    }

    @PostMapping("main")
    public String main(@RequestParam("nickname") String nickname, Model model) {
        this.log.debug("main");
        model.addAttribute("nickname", nickname);
        return "main";
    }
}