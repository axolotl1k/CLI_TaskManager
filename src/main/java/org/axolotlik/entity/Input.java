package org.axolotlik.entity;

import java.util.Scanner;

public class Input {
    private static Input instance;
    private Scanner scanner;
    private Input() {
        scanner = new Scanner(System.in);
    }
    public static Input getInstance() {
        if (instance == null) {
            instance = new Input();
        }
        return instance;
    }
    public Scanner getScanner() {
        return scanner;
    }
}
