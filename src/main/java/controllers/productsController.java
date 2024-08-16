package controllers;

import DAO.productDAO;
import DAO.purchaseDAO;
import Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/chooseProducts")
public class productsController {
    private productDAO productOperations;
    private purchaseDAO purchaseOperations;
    @Autowired
    public void setProductOperations(productDAO productOperations) {
        this.productOperations = productOperations;
    }
    @Autowired
    public void setPurchaseOperations(purchaseDAO purchaseOperations) {
        this.purchaseOperations = purchaseOperations;
    }

    @GetMapping("/{id}")
    public String chooseProducts(@PathVariable("id") int id, Model model) {
        List<product>list= productOperations.getAllProducts();
        model.addAttribute("AllAvaliableProducts",list);
        model.addAttribute("userId", id); // Добавляем id в модель
        return "/products/mainPage";
    }
    @GetMapping("/{id}/{productId}")
    public String addProductTo(@PathVariable("id")int id,
                               @PathVariable("productId")int productId){
        purchaseOperations.addPurchase(id,productId);
        return "redirect:/chooseProducts/"+id;
    }
    @GetMapping("/exit")
    public String exit(){
        return "redirect:/user";
    }
    //ADD
    @GetMapping("/new/{id}")
    public String addProduct(@ModelAttribute("newProduct") product product, @PathVariable("id")int id,
                             Model model){
        model.addAttribute("id",id);
        return "/products/newProduct";
    }
    @PostMapping("/{id}")
    public String newProductInDB(@ModelAttribute("newProduct") @Valid product product,
                                 BindingResult bindingResult,@PathVariable("id")int id,
                                 Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("id",id);
            return "/products/newProduct";
        }
        productOperations.addNewProduct(product);
        return "redirect:/chooseProducts/"+id;
    }
}
