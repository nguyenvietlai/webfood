package www.fptshopfood.store.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import www.fptshopfood.store.Services.AccountService;
import www.fptshopfood.store.Services.AuthorityService;
import www.fptshopfood.store.Services.RoleService;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Accountrole;


@RestController
public class AuthorityRestController {
	@Autowired
	AccountService accountService;
	@Autowired
	RoleService roleService;
	@Autowired
	AuthorityService authorityService;

	@GetMapping("/rest/authorities")
	public Map<String, Object> getAuthorities(){
		Map<String, Object> data = new HashMap<>();
		data.put("authorities", authorityService.findAll());
		data.put("roles", roleService.findAll());
		data.put("accounts", accountService.findAll());
		return data;
	}
	@PostMapping("/rest/authorities")
	public Accountrole create(@RequestBody Accountrole accountrole){
		return authorityService.create(accountrole);
	}
	@DeleteMapping("/rest/authorities/{id}")
	public void delete(@PathVariable("id") Integer id){
		authorityService.delete(id);
	}
}