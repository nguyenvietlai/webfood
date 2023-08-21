package www.fptshopfood.store.rest;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import www.fptshopfood.store.Services.FileManagerService;
import www.fptshopfood.store.Services.ProductImageService;
import www.fptshopfood.store.Services.ProductService;
import www.fptshopfood.store.model.Product;
import www.fptshopfood.store.model.Productimage;

import java.util.List;


@CrossOrigin("*")
@RestController
public class FileManagerRestController {
	@Autowired
	FileManagerService fileService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductImageService productImageService;
	
	@GetMapping("/rest/files/{folder}/{file}")
	public byte[] download(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		return fileService.read(folder, file);
	}
	
	@PostMapping("/rest/files/{folder}")
	public List<String> upload(@PathVariable("folder") String folder,
							   @PathParam("files") MultipartFile[] files,
							   @PathParam("id") Integer id) {
		Integer data = id;

		List<String> filenames =  fileService.save(folder, files);
		filenames.forEach(item ->{
			productImageService.create(productImageService.createProductimage(item , data));
		});
		Product product = productService.findById(id);
		if(product != null){
			product.setPicture(filenames.get(0));
			productService.update(product);
		}
		productService.update(product);
		return filenames;
	}
	
	@DeleteMapping("/rest/files/{folder}/{file}")
	public void delete(@PathVariable("folder") String folder, @PathVariable("file") String file) {
		fileService.delete(folder, file);
	}
	
	@GetMapping("/rest/files/{folder}")
	public List<String> list(@PathVariable("folder") String folder) {
		return fileService.list(folder);
	}

}