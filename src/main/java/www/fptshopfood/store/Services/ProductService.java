package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.ProductDAO;
import www.fptshopfood.store.model.Category;
import www.fptshopfood.store.model.Product;
import www.fptshopfood.store.model.Productimage;

import java.util.Date;
import java.util.List;

@Service
public class ProductService implements CRUD<Product,Integer> {
    @Autowired
    ProductDAO productdao;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    CategoryService categoryService;

    @Override
    public Product create(Product product) {
        if(product.getPicture() == null || product.getPicture().trim() == ""){
            return null;
        }
        Category category = categoryService.findById(product.getCategory().getId());
        if(category != null){
            product.setCategory(category);
        }
        product.setCreatedate(new Date());
        productdao.save(product);

        return product;
    }

    public Product createv2(Product product) {

        Category category = categoryService.findById(product.getCategory().getId());
        if(category != null){
            product.setCategory(category);
        }
        product.setCreatedate(new Date());
        productdao.save(product);

        return product;
    }

    @Override
    public Product update(Product product) {
        if(product.getPicture() == null || product.getPicture().trim() ==""){
            List<Productimage> productimages = productImageService.findByProduct(product.getId());
            if(productimages == null || productimages.size() == 0){
                return null;
            }else{
                product.setPicture(productimages.get(0).getImage());
            }
        }

        Category category = categoryService.findById(product.getCategory().getId());
        if(category != null){
            product.setCategory(category);
        }
        productdao.save(product);
        return product;
    }

    @Override
    public void delete(Integer id) {
        Product product = productdao.findById(id).get();
        if(product != null){
            product.setAvailable(false);
            productdao.save(product);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> datas = productdao.findByavailable(true);
        return datas;
    }
    public List<Product> findAll(Pageable page){
        List<Product> datas = productdao.findByavailable(true,page);
        return datas;
    }

    @Override
    public Product findById(Integer integer) {
        Product product = productdao.findById(integer).get();
        return product;
    }
}
