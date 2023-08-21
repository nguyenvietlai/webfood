package www.fptshopfood.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;

@Controller
public class AboutController {
@GetMapping("/about")
public String about(Model model){
	
	return "User/about.html";
}
}
