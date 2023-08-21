package www.fptshopfood.store.DAO;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import www.fptshopfood.store.model.Product;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    List<Product> findByavailable(Boolean boo, Pageable page);
    List<Product> findByavailable(Boolean boo);
}
