package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.AuthorityDAO;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Accountrole;

import java.util.List;
@Service
public class AuthorityService implements CRUD<Accountrole,Integer> {
    @Autowired
    AuthorityDAO authorityDAO;
    @Override
    public Accountrole create(Accountrole accountrole) {
        return authorityDAO.save(accountrole);
    }

    @Override
    public Accountrole update(Accountrole accountrole) {
        return authorityDAO.save(accountrole);
    }

    @Override
    public void delete(Integer integer) {
         authorityDAO.deleteById(integer);
    }

    @Override
    public List<Accountrole> findAll() {
        return authorityDAO.findAll();
    }

    @Override
    public Accountrole findById(Integer integer) {
        return authorityDAO.findById(integer).get();
    }

    public List<Accountrole> findAllByAccount(Account account){
        return authorityDAO.findByaccount(account);
    }
}
