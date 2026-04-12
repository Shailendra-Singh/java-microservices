package me.shail.order_service.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.shail.myboutique_commons.utils.Web.API;

import java.util.List;

import lombok.RequiredArgsConstructor;
import me.shail.myboutique_commons.dto.OrderItemDto;
import me.shail.order_service.service.OrderItemService;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/order-items")
public class OrderItemResource {
    private final OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDto> findAll() {
        return this.orderItemService.findAll();
    }

    @GetMapping("/{id}")
    public OrderItemDto findById(@PathVariable("id") Long id) {
        return this.orderItemService.findById(id);
    }

    @PostMapping
    public OrderItemDto create(@RequestBody OrderItemDto orderItemDto) {
        return this.orderItemService.create(orderItemDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.orderItemService.delete(id);
    }
}
