package www.fptshopfood.store.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Accountrole;

import java.util.List;


public interface AuthorityDAO extends JpaRepository<Accountrole, Integer> {
    List<Accountrole> findByaccount(Account account);
}