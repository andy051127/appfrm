package kr.ac.shingu.soft.appfrm.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @GetMapping("/example")
    public String example(Model model) {
        List<Map<String, String>> prods = new ArrayList<>();
        Map<String, String> prod1 = new HashMap<>();
        prod1.put("name", "아이스아메리카노");
        prod1.put("price", "2,000");
        prod1.put("inStock", "true");
        prods.add(prod1);

        Map<String, String> prod2 = new HashMap<>();
        prod2.put("name", "아이스바닐라라떼");
        prod2.put("price", "2,500");
        prod2.put("inStock", "true");
        prods.add(prod2);
        model.addAttribute("prods", prods);

        model.addAttribute("isLogin", true);
        model.addAttribute("userName", "홍길동");

        return "example/example";
    }
}
