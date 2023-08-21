package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.OrderDetailDAO;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Order;
import www.fptshopfood.store.model.Orderdetail;
import www.fptshopfood.store.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService implements CRUD<Orderdetail , Integer> {
    @Autowired
    OrderDetailDAO orderDetailDAO;

    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Override
    public Orderdetail create(Orderdetail orderdetail) {
        Product product = productService.findById(orderdetail.getProduct().getId());
        if(product != null){
            orderdetail.setProduct(product);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null) {
            Account account = accountService.findByUsername(auth.getName());
            if(account != null){
                orderdetail.setQuantity(orderdetail.getQuantity());
                orderdetail.setPrice(orderdetail.getPrice());
                orderdetail.setOrder(orderdetail.getOrder());
                return orderDetailDAO.save(orderdetail);
            }
        }
        return  null;

    }

    @Override
    public Orderdetail update(Orderdetail orderdetail) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Orderdetail> findAll() {
        return orderDetailDAO.findAll();
    }
    public List<Orderdetail> findByAccount(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Orderdetail> datas = new ArrayList<>();
        if(auth != null){
            Account account = accountService.findByUsername(auth.getName());
            if(account != null){
                List<Order> order = orderService.findByAccount(account);
                if(order != null && order.size() > 0){
                    for (int i = 0; i < order.size(); i++) {
                        List<Orderdetail> orderdetails = orderDetailDAO.findByorder(order.get(i));
                        addTodatas(datas , orderdetails);
                    }
                }
            }
        }
        return datas;
    }
    public List<Orderdetail> findByAccountV2(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Orderdetail> datas = new ArrayList<>();
        if(auth != null){
            Account account = accountService.findByUsername("tuananh");
            if(account != null){
                List<Order> order = orderService.findByAccount(account);
                if(order != null && order.size() > 0){
                    for (int i = 0; i < order.size(); i++) {
                        List<Orderdetail> orderdetails = orderDetailDAO.findByorder(order.get(i));
                        addTodatas(datas , orderdetails);
                    }
                }
            }
        }
        return datas;
    }

    private void addTodatas(List<Orderdetail> datas, List<Orderdetail> orderdetails) {
        for (int i = 0; i < orderdetails.size(); i++) {
            datas.add(orderdetails.get(i));
        }
    }

    @Override
    public Orderdetail findById(Integer integer) {
        return null;
    }
}
