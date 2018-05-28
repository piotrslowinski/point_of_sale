package com.piotrslowinski.impaq.application;

import com.piotrslowinski.impaq.model.Product;
import com.piotrslowinski.impaq.model.Receipt;
import com.piotrslowinski.impaq.model.repositories.ProductRepository;
import com.piotrslowinski.impaq.ui.printer.Printer;
import com.piotrslowinski.impaq.ui.scanner.ProductScanner;

import java.util.Optional;

public class PurchaseProcessService {

    private ProductRepository productRepository;
    private Receipt receipt;
    private ProductScanner productScanner;
    private Printer printer;

    public PurchaseProcessService(ProductRepository productRepository, ProductScanner productScanner, Printer printer) {
        this.productRepository = productRepository;
        this.productScanner = productScanner;
        this.printer = printer;
        this.receipt = new Receipt();
    }


    public String scanProduct(String input) {
        Optional<Product> productOptional = productRepository.get(input);
        if (input.equals("exit"))
            return prepareReceipt(receipt);
        else if (isInputEmpty(input))
            return "Invalid bar code";
        else if (!isProductPresent(productOptional))
            return "Product not found";
        else {
            Product product = productOptional.get();
            addReceiptLine(product);
            return getSingleProductAttributes(product);
        }
    }

    private void addReceiptLine(Product product) {
        receipt.addReceiptLine(product.getName(), product.getPrice());
    }

    private String getSingleProductAttributes(Product product) {
        return String.format("%s : 1 x %.2f = %.2f  \n", product.getName(), product.getPrice(), product.getPrice());
    }

    private boolean isProductPresent(Optional<Product> productOptional) {
        return productOptional.isPresent();
    }

    private boolean isInputEmpty(String input) {
        return (input.equals("") || input.equals(" "));
    }

    private String prepareReceipt(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        for (Receipt.ReceiptLine line : receipt.getProducts()){
            sb.append(line.getName());
            sb.append(" ");
            sb.append(line.getUnitPrice());
            sb.append("\n");
        }
        sb.append("Total price: ");
        sb.append(receipt.calculateTotalPrice());
        sb.append(" zl");
        return sb.toString();
    }

    public String getUsersInput() {
        return productScanner.scanProductCode();
    }
}
