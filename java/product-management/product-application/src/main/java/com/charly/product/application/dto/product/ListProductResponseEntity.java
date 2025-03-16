package com.charly.product.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListProductResponseEntity {
    Page<ProductResponseEntity> products;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
        @NoArgsConstructor
        @AllArgsConstructor
        public static class CategoryResponseEntity {
            private Long id;
            private String name;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class BrandResponseEntity {
            private Long id;
            private String name;
        }
    }
}

