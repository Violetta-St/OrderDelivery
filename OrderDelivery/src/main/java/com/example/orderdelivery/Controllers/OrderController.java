package com.example.orderdelivery.Controllers;

import com.example.orderdelivery.Models.Orders;
import com.example.orderdelivery.Repositories.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Order")
@SessionAttributes({"orders", "account"})
@Controller
public class OrderController {
    private final OrderRepository repo;

    @Autowired
    public OrderController(OrderRepository repo) {this.repo = repo;}


    //Метод чтения данных с таблицы и отображения страницы добавления
    @GetMapping
    public String read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Orders order= new Orders();
        model.addAttribute("order",order);
        Iterable<Orders> orders = repo.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Order/Order";}


    //Метод сохранения новой записи в БД
    @PostMapping(value = "/create")
    public String create(@jakarta.validation.Valid @ModelAttribute(value = "order") Orders order, BindingResult result) {
        if (result.hasErrors()){return "Order/Order";}
        else{repo.save(order);return "redirect:/Order";}}

    //Метод удаления записи из БД
    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(value = "var") Orders order) {
        repo.deleteById(order.getId()); return "redirect:/Order";}

    //Метод отображения страницы редактирования
    @PostMapping(value = "/update_one")
    public String update_one(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute(value = "order") Orders order, Model model) {
        model.addAttribute("order",order);
        Iterable<Orders> orders = repo.findAll();
        model.addAttribute("orders",orders);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "Order/Order_Ref";}

    //Метод сохранения изменений записи в БД
    @PostMapping(value = "/update_two")
    public String update_two(@Valid @ModelAttribute(value = "order") Orders order, BindingResult result) {
        if (result.hasErrors()){return "Order/Order_Ref";}
        repo.save(order);
        return "redirect:/Order";}
}