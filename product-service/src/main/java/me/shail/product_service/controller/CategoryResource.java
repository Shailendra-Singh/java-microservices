package me.shail.product_service.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.shail.myboutique_commons.utils.Web.API;
import me.shail.product_service.service.CategoryService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import me.shail.myboutique_commons.dto.CategoryDto;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/categories")
public class CategoryResource {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") Long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping
    public CategoryDto create(CategoryDto categoryDto) {
        return this.categoryService.create(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.categoryService.delete(id);
    }
}
