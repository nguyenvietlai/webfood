package www.fptshopfood.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import www.fptshopfood.store.Services.ProductImageService;
import www.fptshopfood.store.model.Productimage;

import java.util.List;

@RestController
public class ProductImagesRest {
    @Autowired
    ProductImageService productImageService;
    @GetMapping("/rest/productimages/{Product_Id}")
    public ResponseEntity<List<Productimage>> getImagesOfProduct(@PathVariable("Product_Id") Integer id){
        List<Productimage> datas = productImageService.findByProduct(id);
        if(datas != null && datas.size() != 0){
            return ResponseEntity.ok(datas);
        }
        return ResponseEntity.ofNullable(datas);
    }

}
