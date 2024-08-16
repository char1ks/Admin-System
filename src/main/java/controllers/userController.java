package controllers;

import DAO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import Model.user;

import javax.validation.Valid;
import Model.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class userController {
    private userDAO userOperations;
    private purchaseDAO purchaseOperations;
    @Autowired
    public void setUserOperations(userDAO userOperations) {
        this.userOperations = userOperations;
    }
    @Autowired
    public void setPurchaseOperations(purchaseDAO purchaseOperations) {
        this.purchaseOperations = purchaseOperations;
    }

    //ELEMENTS
    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("AllUsers",userOperations.getAllUsers());
        return "/user/mainPage";
    }
    @GetMapping("/userOrders")
    public String getAllUsersWithOrder(Model model){
        model.addAttribute("userOrders",userOperations.getUsersWithOrders());
        return "/user/usersWithOrders";
    }
    @GetMapping("/{id}")
    public String concretUser(@PathVariable("id")int id,
                              Model model) {
        model.addAttribute("concretUser",userOperations.getConcret(id));
        List<purchase> list=purchaseOperations.getAllPurchase(id);
        model.addAttribute("allPurchases",list);
        return "/user/concretPage";
    }
    //ADD
    @GetMapping("/new")
    public String newUser(@ModelAttribute("newUser") user user){
        return "/user/newUser";
    }
    @PostMapping
    public String addNewUserInDB(@ModelAttribute("newUser") @Valid user user,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/user/newUser";
        int id =userOperations.addNewUser(user);
        return "redirect:/chooseProducts/"+id;
    }
    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id")int id,
                           Model model){
        user user=userOperations.getConcret(id);
        model.addAttribute("concretUser",user);
        return "user/editPage";
    }
    @PatchMapping("/{id}")
    public String editInDB(@PathVariable("id")int id,
                           @ModelAttribute("concretUser") @Valid user user,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "editPage";
        userOperations.editInDB(id,user);
        return "redirect:/user";
    }
    @DeleteMapping("/{id}")
    public String deleteInDB(@PathVariable("id")int id){
        userOperations.deleteInDB(id);
        return "redirect:/user";
    }
}
