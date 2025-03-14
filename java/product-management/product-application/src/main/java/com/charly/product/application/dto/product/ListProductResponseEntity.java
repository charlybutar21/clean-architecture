package com.charly.product.application.dto.product;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Data
@Builder
public class ListProductResponseEntity {
    Page<ProductResponseEntity> products;

    @Data
    @Builder
    public static class ProductResponseEntity {
        private String code;
        private String name;
        private String description;
        private int stock;
        private BigDecimal price;
        private CategoryResponseEntity category;
        private BrandResponseEntity brand;

        @Data
        @Builder
        public static class CategoryResponseEntity {
            private Long id;
            private String name;
        }

        @Data
        @Builder
        public static class BrandResponseEntity {
            private Long id;
            private String name;
        }
    }
}

