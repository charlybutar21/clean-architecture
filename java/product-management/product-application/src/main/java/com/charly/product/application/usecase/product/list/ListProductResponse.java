package com.charly.product.application.usecase.product.list;

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
public class ListProductResponse {
    Page<ProductResponse> products;

    @Data
    private static class ProductResponse {
        private String code;
        private String name;
        private String description;
        private int stock;
        private BigDecimal price;
        private CategoryResponse category;
        private BrandResponse brand;

        @Data
        private static class CategoryResponse {
            private Long id;
            private String name;
        }

        @Data
        private static class BrandResponse {
            private Long id;
            private String name;
        }
    }
}
