package com.charly.product.adapter.gateway.product;

import com.charly.product.adapter.repository.product.ProductRepository;
import com.charly.product.application.dto.product.CreateProductEntityRequest;
import com.charly.product.application.dto.product.ListProductEntityRequest;
import com.charly.product.application.dto.product.CreateProductResponseEntity;
import com.charly.product.application.dto.product.ListProductResponseEntity;
import com.charly.product.application.dto.product.ListProductResponseEntity.ProductResponseEntity;
import com.charly.product.application.gateway.product.ProductGateway;
import com.charly.product.entity.model.product.Product;
import com.charly.product.entity.model.product.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class ProductMySQLGateway implements ProductGateway {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public CreateProductResponseEntity save(CreateProductEntityRequest entityRequest) {
        Product product = Product.builder()
                .name(entityRequest.getName())
                .code(entityRequest.getCode())
                .description(entityRequest.getDescription())
                .price(entityRequest.getPrice())
                .status(ProductStatus.ACTIVE)
                .stockQuantity(entityRequest.getStockQuantity())
                .category(entityRequest.getCategory())
                .brand(entityRequest.getBrand())
                .build();

        productRepository.save(product);

        return CreateProductResponseEntity.builder()
                .productCode(product.getCode())
                .build();
    }

    @Override
    public ListProductResponseEntity search(ListProductEntityRequest entityRequest) {
        Specification<Product> spec = buildSpecification(entityRequest);
        Pageable pageable = buildPageable(entityRequest);

        Page<Product> products = productRepository.findAll(spec, pageable);

        return ListProductResponseEntity.builder()
                .products(products.map(product -> modelMapper.map(product, ProductResponseEntity.class)))
                .build();
    }

    private Specification<Product> buildSpecification(ListProductEntityRequest entityRequest) {
        Specification<Product> spec = Specification.where(null);

        if (!CollectionUtils.isEmpty(entityRequest.getStatuses())) {
            spec = spec.and((root, query, cb) -> root.get("status").in(entityRequest.getStatuses()));
        }

        if (StringUtils.hasText(entityRequest.getKeyword())) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + entityRequest.getKeyword().toLowerCase() + "%")
            );
        }
        if (!CollectionUtils.isEmpty(entityRequest.getCategoryIds())) {
            spec = spec.and((root, query, cb) ->
                    root.get("category").get("id").in(entityRequest.getCategoryIds())
            );
        }
        if (!CollectionUtils.isEmpty(entityRequest.getBrandIds())) {
            spec = spec.and((root, query, cb) ->
                    root.get("brand").get("id").in(entityRequest.getBrandIds())
            );
        }
        if (entityRequest.getMinPrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("price"), entityRequest.getMinPrice())
            );
        }
        if (entityRequest.getMaxPrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), entityRequest.getMaxPrice())
            );
        }
        return spec;

    }

    private Pageable buildPageable(ListProductEntityRequest entityRequest) {
        if (entityRequest.getPageNumber() == null || entityRequest.getPageSize() == null) {
            return Pageable.unpaged();
        }

        if (StringUtils.hasText(entityRequest.getSortBy()) && StringUtils.hasText(entityRequest.getDirection())) {
            Sort sort = Sort.by(entityRequest.getDirection().equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, entityRequest.getSortBy());
            return PageRequest.of(entityRequest.getPageNumber(), entityRequest.getPageSize(), sort);
        }

        return PageRequest.of(entityRequest.getPageNumber(), entityRequest.getPageSize());
    }
}
