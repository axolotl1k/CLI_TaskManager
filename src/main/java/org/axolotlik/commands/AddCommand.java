package org.axolotlik.commands;

import org.axolotlik.entity.Input;
import org.axolotlik.entity.Task;
import org.axolotlik.entity.TaskManager;
import org.axolotlik.exceptions.InvalidInputException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AddCommand implements Command {
    @Override
    public void execute(Optional<List<String>> args) {
        Scanner scanner = Input.getInstance().getScanner();
        if (args.isEmpty()) {
            throw new InvalidInputException("Task name is empty");
        }
        String taskName = args.get().get(0);
        System.out.print(">>Input description: \n>>");
        String description = scanner.nextLine();
        System.out.print(">>Input deadline (YYYY-MM-DD): \n>>");
        String deadline = scanner.nextLine();
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.addTask(new Task(taskName, description, deadline, "in_progress"));
        System.out.println("Task " + taskName + " successfully added.");
        taskManager.save();
    }
}
