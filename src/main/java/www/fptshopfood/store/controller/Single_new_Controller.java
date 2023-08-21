package www.fptshopfood.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import www.fptshopfood.store.DAO.ProductDAO;

@Controller
public class Single_new_Controller {

@GetMapping("/single-new")
public String single_new(Model model){
	return "User/single-news.html";
}

}
