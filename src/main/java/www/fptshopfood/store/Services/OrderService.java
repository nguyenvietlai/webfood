package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.OrderDAO;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Order;

import java.util.Date;
import java.util.List;

@Service
public class OrderService implements CRUD<Order,Integer>{
    @Autowired
    OrderDAO orderDAO;

    @Override
    public Order create(Order order) {
        order.setCreatedate(new Date());
        order.setStatus(false);
        Order order1 = orderDAO.save(order);
        return order1;
    }

    @Override
    public Order update(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public Order findById(Integer integer) {
        return orderDAO.findById(integer).get();
    }
    public List<Order> findByAccount(Account acc){
       List<Order> datas = orderDAO.findByaccount(acc);
       return datas;
    }
}