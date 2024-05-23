package com.example.orderdelivery.Controllers;

import com.example.orderdelivery.Models.ProductCategory;
import com.example.orderdelivery.Repositories.ProductCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ProductCategory")
@SessionAttributes({"productCategories", "account"})
@Controller
public class ProductCategoryController {
    private final ProductCategoryRepository repo;

    @Autowired
    public ProductCategoryController(ProductCategoryRepository repo) {this.repo = repo;}


    //Метод чтения данных с таблицы и отображения страницы добавления
    @GetMapping
    public String read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        ProductCategory productCategory = new ProductCategory();
        model.addAttribute("productCategory",productCategory);
        Iterable<ProductCategory> productCategories = repo.findAll();
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "ProductCategory/ProductCategory";}


    //Метод сохранения новой записи в БД
    @PostMapping(value = "/create")
    public String create(@jakarta.validation.Valid @ModelAttribute(value = "productCategory") ProductCategory productCategory, BindingResult result) {
        if (result.hasErrors()){return "ProductCategory/ProductCategory";}
        else{repo.save(productCategory);return "redirect:/ProductCategory";}}

    //Метод удаления записи из БД
    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(value = "var") ProductCategory productCategory) {
        repo.deleteById(productCategory.getId()); return "redirect:/ProductCategory";}

    //Метод отображения страницы редактирования
    @PostMapping(value = "/update_one")
    public String update_one(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "productCategory") ProductCategory productCategory, Model model) {
        model.addAttribute("productCategory",productCategory);
        Iterable<ProductCategory> productCategories = repo.findAll();
        model.addAttribute("productCategories", productCategories);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "ProductCategory/ProductCategory_Ref";}

    //Метод сохранения изменений записи в БД
    @PostMapping(value = "/update_two")
    public String update_two(@Valid @ModelAttribute(value = "productCagetory") ProductCategory productCategory, BindingResult result) {
        if (result.hasErrors()){return "ProductCategory/ProductCategory_Ref";}
        repo.save(productCategory);
        return "redirect:/ProductCategory";}}
