package www.fptshopfood.store.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import www.fptshopfood.store.model.Order;

public interface CartDAO extends JpaRepository<Order, Integer> {

}
