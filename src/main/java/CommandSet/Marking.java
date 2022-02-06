package commandset;

import exceptions.FaultyTaskNumberException;
import helper.TaskList;
import helper.Ui;

/**
 * <h1>Marking</h1>
 * <p>
 * Marking class implements the methods are mark tasks as done or
 * unmark them.
 * </p>
 * @author Saravanan Anuja Harish
 */
public class Marking extends Command {

    /**
     * Constructor for Marking.
     * returns an instance of Marking.
     */
    Marking() {
        super();
    }

    /**
     * marks a task as done.
     *
     * @param message the user command.
     * @param taskList the list of user tasks.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    public static void markTask(String message, TaskList taskList) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if (num <= taskList.numOfTasks() && num > 0) {
            taskList.get(num - 1).markDone();
            Ui.printTaskCompleted(taskList.get(num - 1));
        } else {
            throw new FaultyTaskNumberException(num);
        }
    }

    /**
     * marks a task as not done.
     *
     * @param message the user command.
     * @param taskList the list of user tasks.
     * @throws FaultyTaskNumberException if the user inputs an invalid task number.
     */
    public static void unmarkTask(String message, TaskList taskList) {
        String[] arr = message.split(" ");
        int num = Integer.valueOf(arr[1]);
        if (num > 0 && num <= taskList.numOfTasks()) {
            taskList.get(num - 1).unMarkDone();
            Ui.printUnmarked(taskList.get(num - 1));
        } else {
            throw new FaultyTaskNumberException(num);
        }
    }

}
