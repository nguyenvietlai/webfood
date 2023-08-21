package www.fptshopfood.store.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import www.fptshopfood.store.model.Order;
import www.fptshopfood.store.model.Orderdetail;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<Orderdetail , Integer> {
    List<Orderdetail> findByorder(Order order);
}
