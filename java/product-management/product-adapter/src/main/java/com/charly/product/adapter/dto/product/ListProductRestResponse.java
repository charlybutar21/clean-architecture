package com.charly.product.adapter.dto.product;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Data
public class ListProductRestResponse {
    Page<ProductRestResponse> products;

    @Data
    private static class ProductRestResponse {
        private String code;
        private String name;
        private String description;
        private int stock;
        private BigDecimal price;
        private CategoryRestResponse category;
        private BrandRestResponse brand;

        @Data
        private static class CategoryRestResponse {
            private Long id;
            private String name;
        }

        @Data
        private static class BrandRestResponse {
            private Long id;
            private String name;
        }
    }
}
