package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.RoleDAO;
import www.fptshopfood.store.model.Role;

import java.util.List;
@Service
public class RoleService implements CRUD<Role,Integer>{
    @Autowired
    RoleDAO roleDAO;
    @Override
    public Role create(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public void delete(Integer integer) {
        roleDAO.deleteById(integer);
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findById(Integer integer) {
        return roleDAO.findById(integer).get();
    }
}
