package www.fptshopfood.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import www.fptshopfood.store.DAO.ProductDAO;
import www.fptshopfood.store.Services.CategoryService;
import www.fptshopfood.store.Services.ProductService;
import www.fptshopfood.store.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	@GetMapping("/product")
	public String product(Model model , @RequestParam("p")Optional<Integer> pageid) {
		Pageable page  = PageRequest.of(pageid.orElse(0) < 0 ? 0:pageid.orElse(0),6);

		List<Product> products = productService.findAll(page);

		model.addAttribute("products" , products);

		model.addAttribute("p" , pageid.orElse(0) < 0 ? 0:pageid.orElse(0));
		return "User/shop.html";
	}

	@ModelAttribute("cates")
	public List<String> getCates(){
		List<String> datas = new ArrayList<>() ;
		categoryService.findAll().forEach(item->{
			datas.add(item.getName());
		});
		return datas;
	}
	@ModelAttribute("pageNumber")
	public Integer[] getPageNumber(){
		List<Product> data = productService.findAll();
		int num = 0;
		if(data.size() <= 6){
			num = data != null ? data.size() /6 :0;
		}else{
			num = data != null ? data.size() /6 + 1:0;
		}

		return new Integer[num];
	}


}
