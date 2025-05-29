package org.axolotlik.commands;

import org.axolotlik.entity.TaskManager;
import org.axolotlik.exceptions.InvalidInputException;

import java.util.List;
import java.util.Optional;

public class RemoveCommand implements Command {
    @Override
    public void execute(Optional<List<String>> args) {
        TaskManager taskManager = TaskManager.getInstance();
        if (args.isEmpty()) {
            throw new InvalidInputException("This command requires id argument");
        }
        taskManager.removeTask(taskManager.getTaskById(Integer.parseInt(args.get().get(0))));
        System.out.println("Task successfully removed.");
        taskManager.save();
    }
}
