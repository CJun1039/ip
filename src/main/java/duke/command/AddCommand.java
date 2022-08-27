package duke.command;

import duke.*;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.util.Arrays;

/**
 * Represents the command for the addition of new tasks to Duke's TaskList.
 */

public class AddCommand implements Command{
    String[] inputs;
    String commandType;

    /**
     * Constructs an AddCommand.
     *
     * @param inputs An array of String input obtained from parsing the user input.
     */
    public AddCommand(String[] inputs) {
        this.inputs = inputs;
        commandType = inputs[0].toUpperCase();
    }

    /**
     * Executes the add command and prints the results of this add command using Duke's Ui.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has
     * @param ui The Ui created when starting Duke
     * @param storage The Storage created when starting Duke
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        switch(commandType) {
            case "TODO":
                task = new ToDo(Parser.getDescription(inputs, null), false);
                break;
            case "DEADLINE":
                task = new Deadline(Parser.getDescription(inputs, "/by"),
                        false,
                        Parser.getTime(inputs, "/by"));
                break;
            case "EVENT":
                task = new Deadline(Parser.getDescription(inputs, "/at"),
                        false,
                        Parser.getTime(inputs, "/at"));
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        Ui.dukePrint(tasks.add(task));
        storage.addTaskToStorage(task);
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
