package www.fptshopfood.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import www.fptshopfood.store.Services.OrderDetailService;
import www.fptshopfood.store.model.Orderdetail;

import java.util.List;

@RestController
public class OrderDetailRest {
    @Autowired
    OrderDetailService orderDetailService;
    @PostMapping("/rest/orderdetails")
    public Orderdetail create(@RequestBody Orderdetail orderdetail){
       return orderDetailService.create(orderdetail);
    }

    @GetMapping("/rest/orderdetails")
    public List<Orderdetail> getAll(){
        return orderDetailService.findAll();
    }

}
