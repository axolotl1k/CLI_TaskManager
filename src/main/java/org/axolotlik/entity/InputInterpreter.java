package org.axolotlik.entity;

import org.axolotlik.commands.*;
import org.axolotlik.exceptions.InvalidInputException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InputInterpreter {
    private final Map<String, Command> commands;

    public InputInterpreter() {
        this.commands = Map.of(
                "add", new AddCommand(),
                "find", new FindCommand(),
                "update", new UpdateCommand(),
                "remove", new RemoveCommand(),
                "exit", new ExitCommand()
        );
    }

    public void executeCommand(String input) {
        List<String> stringCommands = Arrays.stream(input.split(" ")).toList();
        if (stringCommands.isEmpty()) {
            System.out.println("No command entered");
            return;
        }
        Command command = commands.get(stringCommands.get(0).toLowerCase());
        if (command == null) {
            throw new InvalidInputException("Unknown command");
        }
        List<String> args = stringCommands.size() > 1
                ? stringCommands.subList(1, stringCommands.size())
                : List.of();

        command.execute(args.isEmpty() ? Optional.empty() : Optional.of(args));

    }
}
