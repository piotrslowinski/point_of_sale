package com.piotrslowinski.impaq.application;

import com.piotrslowinski.impaq.model.Product;
import com.piotrslowinski.impaq.model.Receipt;
import com.piotrslowinski.impaq.model.repositories.ProductRepository;
import com.piotrslowinski.impaq.ui.printer.Printer;
import com.piotrslowinski.impaq.ui.scanner.ProductScanner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PurchaseProcessServiceTest {

    private ProductRepository productRepository;
    private PurchaseProcessService service;
    private ProductScanner mockProductScanner;
    private Printer mockPrinter;

    private Product p1;
    private Product p2;
    private Product p3;
    private Product p4;

    @Before
    public void setUp() {
        p1 = new Product("milkCode", "milk", BigDecimal.valueOf(1.50));
        p2 = new Product("breadCode", "bread", BigDecimal.valueOf(2.00));
        p3 = new Product("waterCode", "water", BigDecimal.valueOf(2.50));
        p4 = new Product("beerCode", "beer", BigDecimal.valueOf(3.00));

        productRepository = Mockito.mock(ProductRepository.class);
        mockProductScanner = Mockito.mock(ProductScanner.class);
        mockPrinter = Mockito.mock(Printer.class);
        service = new PurchaseProcessService(productRepository, mockProductScanner, mockPrinter);

    }


    @Test
    public void shouldDisplaySingleProductAttributes() {
        //given
        Mockito.when(productRepository.get("milkCode")).thenReturn(Optional.of(p1));
        Mockito.when(mockProductScanner.scanProductCode()).thenReturn("milkCode");

        //when
        String message = service.scanProduct(service.getUsersInput());

        //then
        assertEquals(("milk : 1 x 1,50 = 1,50"), message.trim());
    }

    @Test
    public void shouldDisplayErrorMessageWhenCodeIsEmpty() {
        //given
        Mockito.when(mockProductScanner.scanProductCode()).thenReturn("");

        //when
        String message = service.scanProduct(service.getUsersInput());

        //when
        assertEquals("Invalid bar-code", message);
    }

    @Test
    public void shouldDisplayErrorMessageWhenCodeIsWhitespace() {
        //given
        Mockito.when(mockProductScanner.scanProductCode()).thenReturn(" ");

        //when
        String message = service.scanProduct(service.getUsersInput());

        //when
        assertEquals("Invalid bar-code", message);
    }

    @Test
    public void shouldDisplayErrorMessageWhenProductDoesntExist() {
        //given
        Mockito.when(productRepository.get("beerCode")).thenReturn(Optional.empty());
        Mockito.when(mockProductScanner.scanProductCode()).thenReturn("beerCode");

        //when
        String message = service.scanProduct(service.getUsersInput());

        //then
        assertEquals("Product not found", message);
    }

    @Test
    public void shouldReturnScannedProducts() {
        //given
        Mockito.when(productRepository.get("milkCode")).thenReturn(Optional.of(p1));
        Mockito.when(productRepository.get("breadCode")).thenReturn(Optional.of(p2));
        Mockito.when(productRepository.get("beerCode")).thenReturn(Optional.empty());

        //when
        Mockito.when(mockProductScanner.scanProductCode()).thenReturn("milkCode");
        service.scanProduct(service.getUsersInput());

        Mockito.when(mockProductScanner.scanProductCode()).thenReturn("breadCode");
        service.scanProduct(service.getUsersInput());

        Mockito.when(mockProductScanner.scanProductCode()).thenReturn("beerCode");
        service.scanProduct(service.getUsersInput());

        //then
        List<Receipt.ReceiptLine> products = service.getReceipt().getProducts();
        assertEquals(Arrays.asList("milk", "bread"), products.stream().map(p -> p.getName()).collect(Collectors.toList()));
    }

    @Test
    public void shouldReturnSameScannedProductMultiply() {
        //given
        Mockito.when(productRepository.get("milkCode")).thenReturn(Optional.of(p1));

        //when
        Mockito.when(mockProductScanner.scanProductCode()).thenReturn("milkCode");
        service.scanProduct(service.getUsersInput());

        Mockito.when(mockProductScanner.scanProductCode()).thenReturn("milkCode");
        service.scanProduct(service.getUsersInput());

        //then
        List<Receipt.ReceiptLine> products = service.getReceipt().getProducts();
        assertEquals(Arrays.asList("milk", "milk"), products.stream().map(p -> p.getName()).collect(Collectors.toList()));
    }
}
