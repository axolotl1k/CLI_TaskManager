package org.axolotlik;

import org.axolotlik.entity.Input;
import org.axolotlik.entity.InputInterpreter;
import org.axolotlik.exceptions.InvalidInputException;
import org.axolotlik.exceptions.TaskNotFoundException;

import java.util.Scanner;

public class StartApp {
    public static void main(String[] args) {
        Scanner scanner = Input.getInstance().getScanner();
        InputInterpreter interpreter = new InputInterpreter();
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                interpreter.executeCommand(input);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage()
                + "\nPlease enter a valid command.");
            } catch (TaskNotFoundException e) {
                System.out.println(e.getMessage()
                + "\nPlease enter an existing task. (To see all tasks you can use this command \"find all\")");
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage() + "This command requires integer argument");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
