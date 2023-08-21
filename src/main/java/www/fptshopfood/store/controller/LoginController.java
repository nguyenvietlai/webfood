package www.fptshopfood.store.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import www.fptshopfood.store.Services.AccountService;

@Controller
public class LoginController {
	@Autowired
	AccountService accountService;
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(
			@RequestParam(required = false , value = "error") String error,
			@RequestParam(required = false , value="logout") String logout , Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String errorMessage = "";
		if(error != null){
			errorMessage = "Login failure ! check your username or password";
		}
		if(logout != null){
			errorMessage = "Your logout";
			model.addAttribute("logout" , true);
		}
		model.addAttribute("msg" , errorMessage);
		return "User/login.html";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}
	@GetMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2){
		accountService.loginFromOAuth2(oauth2);
		return "redirect:/home";
	}
}
