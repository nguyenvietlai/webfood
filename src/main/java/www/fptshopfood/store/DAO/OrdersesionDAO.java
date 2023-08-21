package www.fptshopfood.store.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Orderssession;
import www.fptshopfood.store.model.Product;

public interface OrdersesionDAO extends JpaRepository<Orderssession, Integer> {
    Orderssession findByAccountAndProduct(Account acc , Product pro);
}