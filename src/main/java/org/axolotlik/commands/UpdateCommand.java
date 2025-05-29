package org.axolotlik.commands;

import org.axolotlik.entity.Task;
import org.axolotlik.entity.TaskManager;
import org.axolotlik.exceptions.InvalidInputException;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class UpdateCommand implements Command {
    @Override
    public void execute(Optional<List<String>> args) {
        TaskManager taskManager = TaskManager.getInstance();
        if (args.isEmpty() || args.get().size() < 3) {
            throw new InvalidInputException("This command requires at least three argument");
        }
        Iterator<String> iterator = args.get().iterator();
        Task updatingTask = taskManager.getTaskById(Integer.parseInt(iterator.next()));
        while (iterator.hasNext()) {
            switch (iterator.next().toLowerCase()) {
                case "name":
                    if (!iterator.hasNext()) {
                        throw new InvalidInputException("This command requires name argument");
                    }
                    updatingTask.setName(iterator.next());
                    break;
                case "status":
                    if (!iterator.hasNext()) {
                        throw new InvalidInputException("This command requires status name argument");
                    }
                    updatingTask.setStatus(iterator.next().toLowerCase());
                    break;
                case "deadline":
                    if (!iterator.hasNext()) {
                        throw new InvalidInputException("This command requires deadline date argument");
                    }
                    updatingTask.setDeadline(iterator.next());
                    break;
                default:
                    throw new InvalidInputException("Unknown command");
            }
        }
        System.out.println("Task " + updatingTask.getName() + " successfully updated.");
        taskManager.save();
    }
}
