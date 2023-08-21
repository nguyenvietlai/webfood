package www.fptshopfood.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import www.fptshopfood.store.DAO.RevenueDAO;
import www.fptshopfood.store.Services.OrderService;
import www.fptshopfood.store.model.Order;
import www.fptshopfood.store.model.revenueDTO;

import java.util.List;

@Controller
public class adminController {
    @Autowired
    OrderService orderService;

    @Autowired
    RevenueDAO revenueDAO;
    @GetMapping("/admin/products")
    public String admin_product(){
        return "Admin/form-product.html";
    }
    @GetMapping("/admin/authorities")
    public String admin_authoritie(){
        return "Admin/table-bootstrap-basic.html";
    }
    @GetMapping("/admin/bill-manager")
    public String admin_bills(){
        return "Admin/bills.html";
    }

    @GetMapping("/admin/bill-manager/{id}")
    public String admin_bills_confirm(@PathVariable("id") Integer id){
        if(id != null && id >= 0){
            Order order = orderService.findById(id);
            if(order != null){
                order.setStatus(Boolean.TRUE);
                orderService.update(order);
            }
        }
        return "Admin/bills.html";
    }

    @GetMapping("/admin/revenue-manager")
    public String admin_revenue(Model model){
        List<revenueDTO> data =  revenueDAO.getAll();
        model.addAttribute("statiscal" , data);
        return "Admin/thongke.html";
    }

}
