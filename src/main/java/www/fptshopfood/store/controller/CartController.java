package www.fptshopfood.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CartController {
	@GetMapping("/cart")
	public String cart(Model model, @RequestParam(required = false) Integer id) {
		return "User/cart.html";
	}
}
