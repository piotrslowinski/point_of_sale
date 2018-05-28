package com.piotrslowinski.impaq.ui.screen;


import java.util.Scanner;

public class MainScreen implements Screen {

    private Scanner scanner;
    private Screen scannerScreen;

    public MainScreen(Scanner scanner, Screen scannerScreen) {
        this.scanner = scanner;
        this.scannerScreen = scannerScreen;
    }

    @Override
    public void show() {
        System.out.println("Choose action");
        System.out.println("1 - Scan poducts:");
        System.out.println("2 - Exit");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1:
                scannerScreen.show();
                break;
            case 2:
                break;
            default:
                System.out.println("Wrong input!!");
                System.out.println();
                show();
        }
    }
}
