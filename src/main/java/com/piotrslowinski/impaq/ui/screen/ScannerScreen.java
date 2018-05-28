package com.piotrslowinski.impaq.ui.screen;

import com.piotrslowinski.impaq.application.PurchaseProcessService;

import java.util.Scanner;

public class ScannerScreen implements Screen {


    private Scanner scanner;
    private PurchaseProcessService service;


    public ScannerScreen(Scanner scanner, PurchaseProcessService service) {
        this.scanner = scanner;
        this.service = service;
    }


    public void show() {
        String input;
        do {
            input = service.getUsersInput();
            String message = service.scanProduct(input);
            if (!input.equals("exit")) {
                displayMessage(message);
            }
        } while (!input.equals("exit"));

        service.printReceipt();
    }

    private void displayMessage(String message) {
        System.out.println(message);
    }
}
