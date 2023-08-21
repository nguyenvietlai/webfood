package www.fptshopfood.store.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import www.fptshopfood.store.DAO.AccountDAO;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Accountrole;
import www.fptshopfood.store.model.Role;

import java.util.Date;
import java.util.List;
@Service
public class AccountService implements CRUD<Account, Integer>{
    @Autowired
    PasswordEncoder pe;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    AuthorityService authorityService;

    @Override
    public Account create(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public Account findById(Integer integer) {
        return accountDAO.findById(integer).get();
    }

    public Account findByUsername(String username){
        return accountDAO.findByusername(username);
    }

    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2){
        OAuth2AuthenticationToken oauth2c = oauth2;
        // String fullname = oauth2.getPrincipal().getAttribute("name");
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = Long.toHexString(System.currentTimeMillis());
        Account account = accountDAO.findByemail(email.trim());
        if(account == null){
            account = new Account();
            account.setFullname(oauth2.getPrincipal().getName());
            account.setCreatedate(new Date());
            account.setEmail(email);
            account.setUsername(email);

            accountDAO.save(account);
            Role role = new Role();
            role.setId(2);
            role.setName("USER");
            Accountrole accountrole = new Accountrole();
            accountrole.setAccount(account);
            accountrole.setRole(role);
            authorityService.create(accountrole);
        }
        UserDetails user = User.withUsername(email)
                .password(pe.encode(password)).roles("USER").build();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public Account findByEmail(String email){
        return accountDAO.findByemail(email);
    }


}
