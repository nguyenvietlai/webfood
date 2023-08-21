package www.fptshopfood.store.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import www.fptshopfood.store.model.Account;

import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{
    Account findByusername(String username);

    Account findByemail(String email);

}
