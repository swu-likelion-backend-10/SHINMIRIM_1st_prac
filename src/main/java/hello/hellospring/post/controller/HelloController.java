package hello.hellospring.post.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("name","Likelion 10th MiRim" );
        return "hello";
    }
}
