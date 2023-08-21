package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.CategoryDAO;
import www.fptshopfood.store.model.Category;

import java.util.List;
@Service
public class CategoryService implements CRUD<Category , Integer>{
    @Autowired
    CategoryDAO categoryDAO;
    @Override
    public Category create(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public Category update(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public void delete(Integer integer) {
        categoryDAO.deleteById(integer);
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(Integer integer) {
        return categoryDAO.findById(integer).get();
    }
}
