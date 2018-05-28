package com.piotrslowinski.impaq.ui.scanner;


import java.util.Scanner;

public class VirtualProductScanner implements ProductScanner {

    private Scanner scanner;

    public VirtualProductScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String scanProductCode() {
        System.out.println("Scan product(type it's code or exit): ");
        return scanner.nextLine();
    }
}
