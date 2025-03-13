package com.charly.product.application.usecase.product.list;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ListProductRequest {
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
