package www.fptshopfood.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.fptshopfood.store.Services.CategoryService;
import www.fptshopfood.store.model.Category;
import www.fptshopfood.store.model.Product;

import java.util.List;

@RestController
public class CategoryRest {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/rest/categories")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> datas =  categoryService.findAll();
        return ResponseEntity.ok(datas);
    }
    @PostMapping("/rest/categories")
    public ResponseEntity<Category> create(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.create(category));
    }
    @PutMapping("/rest/categories/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.update(category));
    }
    @DeleteMapping("/rest/categories/{id}")
    public void delete(@PathVariable("id") Integer id){
        if(id > 0){
            categoryService.delete(id);
        }
    }
}
