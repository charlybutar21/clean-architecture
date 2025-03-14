package com.charly.product.application.usecase.product.list;

import com.charly.product.application.dto.product.ListProductEntityRequest;
import com.charly.product.application.dto.product.ListProductResponseEntity;
import com.charly.product.application.dto.product.ListProductResponseEntity.ProductResponseEntity;
import com.charly.product.application.dto.product.ListProductResponseEntity.ProductResponseEntity.BrandResponseEntity;
import com.charly.product.application.dto.product.ListProductResponseEntity.ProductResponseEntity.CategoryResponseEntity;
import com.charly.product.application.gateway.product.ProductGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;

class ListProductUseCaseTest {
    @Mock
    ProductGateway productGateway;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    ListProductUseCase listProductUseCase;

    private AutoCloseable autoCloseable;

    private ListProductRequest request;
    private ListProductEntityRequest entityRequest;
    private ListProductResponseEntity entityResponse;
    private ListProductResponse response;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        request = new ListProductRequest();

        entityRequest = ListProductEntityRequest.builder().build();

        entityResponse = ListProductResponseEntity.builder()
                .products(new PageImpl<>(
                        Collections.singletonList(
                                ProductResponseEntity.builder()
                                        .code("P001")
                                        .name("Laptop XYZ")
                                        .description("High-performance laptop")
                                        .stock(50)
                                        .price(BigDecimal.valueOf(15000000))
                                        .category(CategoryResponseEntity.builder()
                                                .id(1L)
                                                .name("Electronics")
                                                .build())
                                        .brand(BrandResponseEntity.builder()
                                                .id(10L)
                                                .name("BrandX")
                                                .build())
                                        .build()
                        ),
                        Pageable.unpaged(),
                        1
                ))
                .build();

        response = new ListProductResponse();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testExecute() {
        when(modelMapper.map(request, ListProductEntityRequest.class)).thenReturn(entityRequest);
        when(productGateway.search(entityRequest)).thenReturn(entityResponse);
        when(modelMapper.map(entityResponse, ListProductResponse.class)).thenReturn(response);

        ListProductOutputBoundary presenter = mock(ListProductOutputBoundary.class);

        listProductUseCase.execute(request, presenter);

        verify(modelMapper).map(request, ListProductEntityRequest.class);
        verify(productGateway).search(entityRequest);
        verify(modelMapper).map(entityResponse, ListProductResponse.class);
        verify(presenter).present(response);
    }
}
