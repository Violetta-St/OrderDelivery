package com.example.orderdelivery.Controllers;

import com.example.orderdelivery.Models.Product;
import com.example.orderdelivery.Models.ProductCategory;
import com.example.orderdelivery.Repositories.ProductRepository;
import com.example.orderdelivery.Repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/Product")
@Controller
@SessionAttributes({"products", "productCategories", "account"})
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    @Autowired
    public ProductController(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository){
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }
    @GetMapping
    public String read(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("product",new Product());
        Iterable<ProductCategory> productCategories = productCategoryRepository.findAll();
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Product/Product";}

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute(value = "product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "Product/Product";}
        else{
            productRepository.save(product);
            return "redirect:/Product";}
    }
    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(value = "var") Product product) {
        productRepository.deleteById(product.getId());
        return "redirect:/Product";
    }
    @PostMapping(value = "/update_one")
    public String refactor_first(@ModelAttribute(value = "var") Product product, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("productCategories", productCategoryRepository.findAll());
        return "Product/Product_Ref";
    }
    @PostMapping(value = "/update_two")
    public String update_two(@Valid @ModelAttribute(value = "product") Product product, BindingResult result) {
        if (result.hasErrors()){return "Product/Product_Ref";}
        productRepository.save(product);
        return "redirect:/Product";}
}