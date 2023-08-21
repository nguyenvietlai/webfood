package www.fptshopfood.store.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import www.fptshopfood.store.model.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
