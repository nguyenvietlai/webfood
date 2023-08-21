package www.fptshopfood.store.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import www.fptshopfood.store.Services.AccountService;
import www.fptshopfood.store.Services.AuthorityService;
import www.fptshopfood.store.Services.RoleService;
import www.fptshopfood.store.model.Account;
import www.fptshopfood.store.model.Accountrole;
import www.fptshopfood.store.model.Role;

@Controller
public class RegisterController {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;

    @Autowired
    RoleService roleService;

    @GetMapping("/register")
    public String register(Model model) {
        Account acc = new Account();
        model.addAttribute("use", acc);
        return "User/register.html";
    }
    @PostMapping("/register/add")
    public String submitregister(@ModelAttribute("user") Account user, Model model
            ,@RequestParam("confirmpass") String pass) {
        try {
            if (!user.getPasswords().equals(pass)) {
                model.addAttribute("error", "Xác thực Passworld không đúng");
                return "User/register.html";
            } else if(accountService.findByEmail(user.getEmail()) != null) {
                model.addAttribute("error", "Email này đã tồn tại");
                return "User/register.html";
            }

            else {
                user.setCreatedate(new Date());
                accountService.create(user);
                Accountrole accountrole = new Accountrole();
                List<Role> roleList = roleService.findAll();
                Role role = null;
                if(roleList != null){
                    roleList.forEach(item ->{
                        if(item.getName().equals("USER")){
                            accountrole.setRole(item);
                        }
                    });
                }
                accountrole.setAccount(user);
                authorityService.create(accountrole);
                model.addAttribute("error", "Đăng ký thành công");
            }
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Username này đã tồn tại");
            return "User/register.html";

        }

        return "redirect:/login";


    }
}