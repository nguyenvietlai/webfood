package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.OrdersesionDAO;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Orderssession;
import www.fptshopfood.store.model.Product;

import java.util.List;

@Service
public class OrdersessionService implements CRUD<Orderssession  ,Integer>{
    @Autowired
    OrdersesionDAO ordersesionDAO;

    @Autowired
    ProductService productService;

    @Autowired
    AccountService accountService;
    @Override
    public Orderssession create(Orderssession orderssession) {
        Product product = productService.findById(orderssession.getProduct().getId());
        if(product != null){
            orderssession.setProduct(product);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            Account account = accountService.findByUsername(auth.getName());
            if(account != null){
                orderssession.setAccount(account);
            }
            Orderssession orderssession1 =  ordersesionDAO.findByAccountAndProduct(account,product);
            if(orderssession1 != null){
                orderssession1.setQuantity(orderssession1.getQuantity()+1);
            }
            return ordersesionDAO.save(orderssession);
        }
        return null;

    }

    @Override
    public Orderssession update(Orderssession orderssession) {
        Product product = productService.findById(orderssession.getProduct().getId());
        if(product != null){
            orderssession.setProduct(product);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Orderssession orderssession1 = null;
        if(auth != null){
            Account account = accountService.findByUsername(auth.getName());
            if(account != null){
                orderssession.setAccount(account);
            }
            orderssession1 =  ordersesionDAO.findByAccountAndProduct(account,product);
            if(orderssession1 != null){
                orderssession1.setQuantity(orderssession.getQuantity());
            }
            return ordersesionDAO.save(orderssession);
        }

        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Orderssession> findAll() {
        return ordersesionDAO.findAll();
    }

    @Override
    public Orderssession findById(Integer integer) {
        return null;
    }
    public Orderssession findByOne(Orderssession orderssession) {
        Product product = productService.findById(orderssession.getProduct().getId());
        if(product != null){
            orderssession.setProduct(product);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Orderssession orderssession1 = null;
        if(auth != null){
            Account account = accountService.findByUsername(auth.getName());
            if(account != null){
                orderssession.setAccount(account);
            }
            orderssession1 =  ordersesionDAO.findByAccountAndProduct(account,product);
            if(orderssession1 != null){
                orderssession1.setQuantity(orderssession.getQuantity());
            }
            return orderssession1;
        }

        return null;
    }
}