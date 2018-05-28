package com.piotrslowinski.impaq.application;

import com.piotrslowinski.impaq.infrastructure.InMemoryProductRepository;
import com.piotrslowinski.impaq.model.repositories.ProductRepository;
import com.piotrslowinski.impaq.ui.printer.Printer;
import com.piotrslowinski.impaq.ui.printer.VirtualPrinter;
import com.piotrslowinski.impaq.ui.scanner.ProductScanner;
import com.piotrslowinski.impaq.ui.scanner.VirtualProductScanner;
import com.piotrslowinski.impaq.ui.screen.MainScreen;
import com.piotrslowinski.impaq.ui.screen.ScannerScreen;
import com.piotrslowinski.impaq.ui.screen.Screen;

import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new VirtualPrinter();
        ProductRepository productRepository = new InMemoryProductRepository();
        ProductScanner productScanner = new VirtualProductScanner(scanner);
        PurchaseProcessService service = new PurchaseProcessService(productRepository, productScanner, printer);
        Screen scannerScreen = new ScannerScreen(scanner, service);
        Screen mainScreen = new MainScreen(scanner, scannerScreen);
        mainScreen.show();
    }
}
