package www.fptshopfood.store.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.fptshopfood.store.DAO.ProductDAO;

import www.fptshopfood.store.Services.ProductService;
import www.fptshopfood.store.model.Product;

import java.util.List;

@RestController
public class ProductRest {
    @Autowired
    ProductService productService;

//    ResponseEntity
    @GetMapping("/rest/products")
    public ResponseEntity<List<Product>> getAll(){

        List<Product> datas =  productService.findAll();
        return ResponseEntity.ok(datas);
    }
    @PostMapping("/rest/products")
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product product1 = productService.create(product);
        if(product1 == null){
            return  ResponseEntity.ofNullable(product1);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/rest/products_import")
    public ResponseEntity<Product> createv2(@RequestBody Product product){
        Product product1 = productService.createv2(product);
        if(product1 == null){
            return  ResponseEntity.ofNullable(product1);
        }
        return ResponseEntity.ok(product);
    }
    @PutMapping("/rest/products/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,@PathVariable("id") Integer id){
        Product product1 = productService.update(product);
        if(product1 == null){
            return ResponseEntity.ofNullable(product1);
        }
        return ResponseEntity.ok(product1);
    }
    @GetMapping("/rest/products/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") Integer id){
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/rest/products/{id}")
    public void delete(@PathVariable("id") Integer id){
        productService.delete(id);
    }
}
