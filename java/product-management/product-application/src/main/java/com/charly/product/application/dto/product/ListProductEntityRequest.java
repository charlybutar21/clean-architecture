package com.charly.product.application.dto.product;

import com.charly.product.entity.model.product.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListProductEntityRequest {
    private List<ProductStatus> statuses;
    private String keyword;
    private List<Long> categoryIds;
    private List<Long> brandIds;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy;
    private String direction;
}
