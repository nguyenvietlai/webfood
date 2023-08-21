package www.fptshopfood.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import www.fptshopfood.store.DAO.RevenueDAO;
import www.fptshopfood.store.model.revenueDTO;

import java.util.List;

@RestController
public class StatiscalRest {
    @Autowired
    RevenueDAO revenueDAO;
    @GetMapping("/rest/statiscal-revenue")
    public List<revenueDTO> get(){
        List<revenueDTO> data = null;
        try {
           data = revenueDAO.getAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return data;
    }

}
