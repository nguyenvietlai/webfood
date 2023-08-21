package www.fptshopfood.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www.fptshopfood.store.DAO.ProductDAO;
import www.fptshopfood.store.Services.ProductImageService;
import www.fptshopfood.store.model.Product;
import www.fptshopfood.store.model.Productimage;

import java.util.List;
import java.util.Optional;

@Controller
public class detail_product_Controller {
	@Autowired
	ProductDAO productdao;
	@Autowired
	ProductImageService productImageService;
@GetMapping("/detail_product")
public String detail_product(Model model , @RequestParam("id") Integer id) {
	Optional<Product> product = productdao.findById(id);
	model.addAttribute("product" , product == null ? null:product.get());
	List<Productimage> productimages = productImageService.findByProduct(product.get().getId());
	model.addAttribute("productImages" , productimages);
	return "User/single-product.html";
}
}
