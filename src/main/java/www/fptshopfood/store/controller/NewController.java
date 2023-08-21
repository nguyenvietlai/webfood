package www.fptshopfood.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class NewController {
	@GetMapping("/new")
	public String news() {
		
		return "User/news.html";
	}
}
