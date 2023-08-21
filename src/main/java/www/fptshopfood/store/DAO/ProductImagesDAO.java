package www.fptshopfood.store.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import www.fptshopfood.store.model.Product;
import www.fptshopfood.store.model.Productimage;

import java.util.List;

public interface ProductImagesDAO extends JpaRepository<Productimage , Integer> {
    List<Productimage> findByproduct(Product product);
    Productimage findByimage(String imageName);
}
