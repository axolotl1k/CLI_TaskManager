package org.axolotlik.commands;

import org.axolotlik.entity.Task;
import org.axolotlik.entity.TaskManager;
import org.axolotlik.exceptions.InvalidInputException;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class FindCommand implements Command {
    @Override
    public void execute(Optional<List<String>> args) {
        TaskManager taskManager = TaskManager.getInstance();
        if (args.isEmpty()) {
            throw new InvalidInputException("This command requires at least one argument");
        }
        Iterator<String> iterator = args.get().iterator();
        List<Task> tasks;
        switch (iterator.next().toLowerCase()) {
            case "all":
                tasks = taskManager.getTasks();
                if (tasks.isEmpty()) {
                    System.out.println("No tasks found");
                }
                tasks.forEach(System.out::println);
                break;
            case "status":
                if (!iterator.hasNext()) {
                    throw new InvalidInputException("This command requires status name argument");
                    }
                    tasks = taskManager.getTasks()
                            .stream()
                            .filter(task ->
                                    task.getStatus()
                                            .toString()
                                            .equalsIgnoreCase(iterator.next()))
                            .toList();
                if (tasks.isEmpty()) {
                    System.out.println("No tasks found");
                }
                tasks.forEach(System.out::println);
                break;
            case "id":
                if (!iterator.hasNext()) {
                    throw new InvalidInputException("This command requires id name argument");
                }
                System.out.println(taskManager.getTaskById(Integer.parseInt(iterator.next())));
                break;
            default:
                System.out.println("Unknown command");
        }
    }
}
