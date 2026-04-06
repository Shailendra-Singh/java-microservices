package me.shail.MyBoutique.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shail.MyBoutique.common.dto.ProductDto;
import me.shail.MyBoutique.model.Product;
import me.shail.MyBoutique.model.ProductStatus;
import me.shail.MyBoutique.repository.CategoryRepository;
import me.shail.MyBoutique.repository.ProductRepository;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDto> findAll() {
        log.debug("Request to get all Products");
        return this.productRepository.findAll().stream().map(ProductService::mapToDto).toList();
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        log.debug("Request to get Product: {}", id);
        return this.productRepository.findById(id).map(ProductService::mapToDto).orElse(null);
    }

    public ProductDto create(ProductDto productDto) {
        log.debug("Request to create Product: {}", productDto);
        return mapToDto(this.productRepository.save(
                new Product(
                        productDto.name(),
                        productDto.description(),
                        productDto.price(),
                        productDto.quantity(),
                        ProductStatus.valueOf(productDto.status()),
                        productDto.salesCounter(),
                        Collections.emptySet(),
                        this.categoryRepository.findById(productDto.category().id()).orElse(null))));
    }

    public void delete(Long id) {
        log.debug("Request to delete Product: {}", id);
        this.productRepository.deleteById(id);
    }

    public static ProductDto mapToDto(Product product) {
        if (product != null) {
            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getStatus().name(),
                    product.getSalesCounter(),
                    product.getReviews()
                            .stream().map(ReviewService::mapToDto).collect(Collectors.toSet()),
                    CategoryService.mapToDto(product.getCategory())

            );
        }

        return null;
    }
}
