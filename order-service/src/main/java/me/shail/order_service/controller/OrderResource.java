package me.shail.order_service.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.shail.myboutique_commons.utils.Web.API;

import java.util.List;

import lombok.RequiredArgsConstructor;
import me.shail.myboutique_commons.dto.OrderDto;
import me.shail.order_service.service.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/orders")
public class OrderResource {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> findAll() {
        return this.orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable Long id) {
        return this.orderService.findById(id);
    }

    // @GetMapping("/customer/{id}")
    // public List<OrderDto> findAllByUser(@PathVariable Long id) {
    // return this.orderService.findAllByUser(id);
    // }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.orderService.delete(id);
    }
}
