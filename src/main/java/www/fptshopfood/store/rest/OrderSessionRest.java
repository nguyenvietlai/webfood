package www.fptshopfood.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.fptshopfood.store.Services.OrdersessionService;
import www.fptshopfood.store.model.Orderssession;

import java.util.List;

@RestController
public class OrderSessionRest {
    @Autowired
    OrdersessionService ordersessionService;

    @GetMapping("/rest/ordersession")
    public List<Orderssession> findAlla(){
        return  ordersessionService.findAll();
    }
    @PostMapping("/rest/ordersession")
    public Orderssession create( @RequestBody Orderssession orderssession){
        return  ordersessionService.create(orderssession);
    }
    @PostMapping("/rest/ordersession/{id}")
    public Orderssession update(@PathVariable("id") Integer idProduct,@RequestBody Orderssession orderssession){
        return  ordersessionService.update(orderssession);
    }



}