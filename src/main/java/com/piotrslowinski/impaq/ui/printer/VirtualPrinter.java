package com.piotrslowinski.impaq.ui.printer;

public class VirtualPrinter implements Printer {
    @Override
    public void print(String output) {
        System.out.println(output);
    }
}
