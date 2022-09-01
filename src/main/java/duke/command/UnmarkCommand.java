package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents the command for un-marking a task in Duke's TaskList.
 */
public class UnmarkCommand implements Command{

    private final int toUnmark;

    /**
     * Constructs an UnmarkCommand.
     *
     * @param toUnmark Index of the task to be unmarked in Duke's TaskList.
     */
    public UnmarkCommand(int toUnmark) {
        this.toUnmark = toUnmark;
    }

    /**
     * Executes the un-mark command by un-marking the task and printing the un-mark message.
     * Refreshes the storage to write the new changes.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has.
     * @param ui Ui created when starting Duke.
     * @param storage Storage created when starting Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.unmark(toUnmark));
        storage.refresh(tasks);
    }

    /**
     * Returns whether this command is an exit command.
     *
     * @return False since this is not an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
