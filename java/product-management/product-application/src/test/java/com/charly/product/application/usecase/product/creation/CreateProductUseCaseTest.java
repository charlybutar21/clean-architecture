package com.charly.product.application.usecase.product.creation;

import com.charly.product.application.dto.product.CreateProductEntityRequest;
import com.charly.product.application.dto.product.CreateProductResponseEntity;
import com.charly.product.application.gateway.product.BrandGateway;
import com.charly.product.application.gateway.product.CategoryGateway;
import com.charly.product.application.gateway.product.ProductGateway;
import com.charly.product.entity.model.product.Brand;
import com.charly.product.entity.model.product.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class CreateProductUseCaseTest {
    @Mock
    CategoryGateway categoryGateway;
    @Mock
    BrandGateway brandGateway;
    @Mock
    ProductGateway productGateway;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    CreateProductUseCase createProductUseCase;

    private AutoCloseable autoCloseable;

    private CreateProductRequest request;
    private CreateProductEntityRequest entityRequest;
    private CreateProductResponseEntity entityResponse;
    private CreateProductResponse response;
    private Category category;
    private Brand brand;

    @BeforeEach
    void setUp() {
        autoCloseable  = MockitoAnnotations.openMocks(this);
        request = new CreateProductRequest();
        request.setCode("P123");
        request.setName("Product Name");
        request.setDescription("Product Description");
        request.setPrice(BigDecimal.valueOf(1000000));
        request.setStockQuantity(10);
        request.setCategoryId(1L);
        request.setBrandId(1L);

        category = Category.builder()
                .id(1L)
                .name("Electronics")
                .description("Electronic Devices")
                .build();

        brand = Brand.builder()
                .id(1L)
                .name("BrandX")
                .description("BrandX Description")
                .build();

        entityRequest = CreateProductEntityRequest.builder()
                .code("P123")
                .name("Product Name")
                .description("Product Description")
                .price(BigDecimal.valueOf(1000000))
                .stockQuantity(10)
                .categoryId(1L)
                .brandId(2L)
                .build();

        entityResponse = CreateProductResponseEntity.builder()
                .productCode("P001")
                .build();

        response = CreateProductResponse.builder()
                .productCode("P001")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testExecute() {
        when(categoryGateway.findById(request.getCategoryId())).thenReturn(category);
        when(brandGateway.findById(request.getBrandId())).thenReturn(brand);
        when(modelMapper.map(request, CreateProductEntityRequest.class)).thenReturn(entityRequest);
        when(productGateway.save(entityRequest)).thenReturn(entityResponse);
        when(modelMapper.map(entityResponse, CreateProductResponse.class)).thenReturn(response);

        CreateProductOutputBoundary presenter = mock(CreateProductOutputBoundary.class);

        createProductUseCase.execute(request, presenter);

        verify(categoryGateway).findById(request.getCategoryId());
        verify(brandGateway).findById(request.getBrandId());
        verify(productGateway).save(entityRequest);
        verify(modelMapper).map(request, CreateProductEntityRequest.class);
        verify(modelMapper).map(entityResponse, CreateProductResponse.class);
        verify(presenter).present(response);
    }
}

