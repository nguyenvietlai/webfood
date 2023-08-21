package www.fptshopfood.store.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import www.fptshopfood.store.model.Role;


public interface RoleDAO extends JpaRepository<Role, Integer> { }
