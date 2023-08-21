package www.fptshopfood.store.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import www.fptshopfood.store.DAO.AccountDAO;

import www.fptshopfood.store.Services.AuthorityService;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Accountrole;

import java.util.ArrayList;

import java.util.List;

@Component
public class ShopFoodAuthentication implements AuthenticationProvider {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    AuthorityService authorityService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Account account = accountDAO.findByusername(username);
        if(account != null && account.getId() > 0 && password.equals(account.getPasswords())){
            return new UsernamePasswordAuthenticationToken(account.getUsername() , password , getGrantedAuthorities(account));
        }else{
            throw new BadCredentialsException("invalid Credential");
        }
    }

    private List< GrantedAuthority> getGrantedAuthorities(Account account) {
        List< GrantedAuthority> grantedAuthorities = new ArrayList<>();

        List<Accountrole> accountroles = authorityService.findAllByAccount(account);

        if(accountroles != null && accountroles.size() > 0){
            accountroles.forEach(item ->{
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+item.getRole().getName()));
            });
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
