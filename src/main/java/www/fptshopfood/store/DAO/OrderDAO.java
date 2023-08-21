package www.fptshopfood.store.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Order;

import java.util.List;

public interface OrderDAO  extends JpaRepository<Order, Integer> {
    List<Order> findByaccount(Account acc);
}