package org.axolotlik.commands;

import org.axolotlik.entity.FileManager;
import org.axolotlik.entity.TaskManager;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ExitCommand implements Command {
    @Override
    public void execute(Optional<List<String>> args) {
        try {
            FileManager.save(TaskManager.getInstance().getTasks());
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e.getMessage()
            + "\nTasks not saved.");
            System.exit(1);
        }
    }
}
