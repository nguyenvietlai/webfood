package www.fptshopfood.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import www.fptshopfood.store.Services.AccountService;
import www.fptshopfood.store.Services.OrderService;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Order;
import www.fptshopfood.store.model.Product;

import java.util.List;

@RestController
public class OrderRest {
    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;
    @GetMapping("/rest/order")
    public ResponseEntity<List<Order>> getAll(){
        List<Order> datas =  orderService.findAll();
        return ResponseEntity.ok(datas);
    }
    @PostMapping("/rest/order")
    public ResponseEntity<Order> create(@RequestBody Order order){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            Account account = accountService.findByUsername(auth.getName());
            if (account != null) {
                order.setAccount(account);
            }
        }

        Order order1 = orderService.create(order);
        if(order1 == null){
            return  ResponseEntity.ofNullable(order1);
        }
        return ResponseEntity.ok(order1);
    }
}