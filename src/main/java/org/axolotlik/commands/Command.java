package org.axolotlik.commands;

import java.util.List;
import java.util.Optional;

public interface Command {
    void execute(Optional<List<String>> args);
}
