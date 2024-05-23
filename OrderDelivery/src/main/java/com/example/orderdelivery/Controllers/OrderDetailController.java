package com.example.orderdelivery.Controllers;

import com.example.orderdelivery.Models.Orders;
import com.example.orderdelivery.Models.OrderDetail;
import com.example.orderdelivery.Models.Orders;
import com.example.orderdelivery.Models.Product;
import com.example.orderdelivery.Models.ProductCategory;
import com.example.orderdelivery.Repositories.OrderRepository;
import com.example.orderdelivery.Repositories.OrderDetailRepository;
import com.example.orderdelivery.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/OrderDetail")
@Controller
@SessionAttributes({"orderDetails", "productList", "ordersList", "account"})
public class OrderDetailController {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    @Autowired
    public OrderDetailController(OrderDetailRepository orderDetailRepository, ProductRepository productRepository, OrderRepository orderRepository){
        this.orderDetailRepository = orderDetailRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }
    @GetMapping
    public String read(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("orderDetail",new OrderDetail());
        Iterable<Orders> orders = orderRepository.findAll();
        Iterable<Product> products = productRepository.findAll();
        Iterable<OrderDetail> orderDetails = orderDetailRepository.findAll();
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("ordersList", orders);
        model.addAttribute("productList", products);
        model.addAttribute("account", userDetails==null ? "Вход" : userDetails.getUsername()+": Выход");
        return "OrderDetail/OrderDetail";}

    @PostMapping(value = "/create")
    public String create(@Valid @ModelAttribute(value = "orderDetail") OrderDetail orderDetail, BindingResult result) {
        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "OrderDetail/OrderDetail";}
        else{
            Product product = productRepository.findById(orderDetail.getProduct().getId()).get();
            orderDetail.setPrice(product.getIn_order() * product.getUnit_price());
            orderDetailRepository.save(orderDetail);
            return "redirect:/OrderDetail";}
    }
    @PostMapping(value = "/delete")
    public String delete(@ModelAttribute(value = "var") OrderDetail orderDetail) {
        orderDetailRepository.deleteById(orderDetail.getId());
        return "redirect:/OrderDetail";
    }
    @PostMapping(value = "/update_one")
    public String update_one(@ModelAttribute(value = "var") OrderDetail orderDetail, Model model) {
        model.addAttribute("orderDetail", orderDetail);
        model.addAttribute("orderDetails", orderDetailRepository.findAll());
        model.addAttribute("productList", productRepository.findAll());
        model.addAttribute("ordersList", orderRepository.findAll());
        return "OrderDetail/OrderDetail_Ref";
    }
    @PostMapping(value = "/update_two")
    public String update_two(@Valid @ModelAttribute(value = "orderDetail") OrderDetail orderDetail, BindingResult result) {
        if (result.hasErrors()){return "OrderDetail/OrderDetail_Ref";}
        Product product = productRepository.findById(orderDetail.getProduct().getId()).get();
        orderDetail.setPrice(product.getIn_order() * product.getUnit_price());
        orderDetailRepository.save(orderDetail);
        return "redirect:/OrderDetail";}
}
