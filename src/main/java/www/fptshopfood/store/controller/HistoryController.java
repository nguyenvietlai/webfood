package www.fptshopfood.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import www.fptshopfood.store.Services.OrderDetailService;
import www.fptshopfood.store.model.Orderdetail;

import java.util.List;

@Controller
public class HistoryController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/order-history")
    public String displayHistory(Model model){
        List<Orderdetail> datas = orderDetailService.findByAccount();
        model.addAttribute("yourhistory" , datas);
        return "User/history.html";
    }

}
