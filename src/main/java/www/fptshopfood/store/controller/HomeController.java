package www.fptshopfood.store.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping(value = {"/home" , "/" , ""})
    public String Home(){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "Admin/index-3.html";
        }
        return "User/index_2.html";
    }

    @GetMapping(value = {"/admin"})
    public String HomeAdmin(){
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "Admin/index-3.html";
        }
        return "redirect:/home";
    }
    
}
