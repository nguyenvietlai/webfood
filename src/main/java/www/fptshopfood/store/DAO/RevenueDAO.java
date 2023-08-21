package www.fptshopfood.store.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import www.fptshopfood.store.model.revenueDTO;

import java.util.List;

public interface RevenueDAO extends JpaRepository<revenueDTO, Integer> {
    @Query("select new revenueDTO(o.product ,sum(o.price) , sum(o.quantity) ) " +
            " from Orderdetail o where o.order.status = true group by o.product" )
     List<revenueDTO> getAll();
}
