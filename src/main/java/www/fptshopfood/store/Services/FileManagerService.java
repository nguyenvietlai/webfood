package www.fptshopfood.store.Services;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import www.fptshopfood.store.model.Product;
import www.fptshopfood.store.model.Productimage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileManagerService {
	@Autowired
	ServletContext app;
	@Value("${app.image_folder}")
	String imageFolder;

	@Autowired
	ProductImageService productImageService;
	
	private Path getPath(String folder, String filename) {
//		File dir = Paths.get(app.getRealPath("/files/"), folder).toFile();
		File dir = Paths.get(imageFolder, folder).toFile();
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return Paths.get(dir.getAbsolutePath(), filename);
	}

	public byte[] read(String folder, String filename) {
		Path path = this.getPath(folder, filename);
		try {
			return Files.readAllBytes(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> save(String folder, MultipartFile[] files) {
		List<String> filenames = new ArrayList<String>();
		for(MultipartFile file: files) {
			String name = System.currentTimeMillis() + file.getOriginalFilename();
			String filename = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
			Path path = this.getPath(folder, filename);
			try {
				file.transferTo(path);

				filenames.add(filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return filenames;
	}
	
	public void delete(String folder, String filename) {
		Path path = this.getPath(folder, filename);
		path.toFile().delete();
		Productimage productimage = productImageService.findByImageName(filename);
		if(productimage != null){
			productImageService.delete(productimage.getId());
		}
	}
	
	public List<String> list(String folder) {
		List<String> filenames = new ArrayList<String>();
		File dir = Paths.get(app.getRealPath("/files/"), folder).toFile();
		if(dir.exists()) {
			File[] files = dir.listFiles();
			for(File file: files) {
				filenames.add(file.getName());
			}
		}
		return filenames;
	}

}
