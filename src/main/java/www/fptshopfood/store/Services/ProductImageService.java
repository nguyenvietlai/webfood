package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.ProductImagesDAO;
import www.fptshopfood.store.model.Product;
import www.fptshopfood.store.model.Productimage;

import java.util.List;
@Service
public class ProductImageService implements CRUD<Productimage , Integer> {
    @Autowired
    ProductImagesDAO productImagesDAO;
    @Override
    public Productimage create(Productimage productimage) {
        return productImagesDAO.save(productimage);
    }

    @Override
    public Productimage update(Productimage productimage) {
        return productImagesDAO.save(productimage);
    }

    @Override
    public void delete(Integer integer) {
         productImagesDAO.deleteById(integer);
    }

    @Override
    public List<Productimage> findAll() {
        return productImagesDAO.findAll();
    }

    @Override
    public Productimage findById(Integer integer) {
        return productImagesDAO.findById(integer).get();
    }
    public Productimage createProductimage(String FileName , Integer idProduct){
        Productimage p = new Productimage();

        Product product = new Product();
        product.setId(idProduct);
        p.setProduct(product);
        p.setImage(FileName);
        return p;
    }
    public List<Productimage> findByProduct(Integer id){
        Product product = new Product();
        product.setId(id);
        List<Productimage> datas = productImagesDAO.findByproduct(product);
        return datas;
    }
    public Productimage findByImageName(String imageName){

        Productimage datas = productImagesDAO.findByimage(imageName);
        return datas;
    }
}
