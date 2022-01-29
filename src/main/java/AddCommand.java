/**
 * File contains the implementation of AddCommand class.
 * @author Saravanan Anuja Harish
 */

public class AddCommand extends Command {

    // space
    private static final String SPACE = " ";

    // starting index of a list or a char in string.
    private static final int START_INDEX = 0;

    // used to differentiate between constructors
    private static final int DUMMY_VARIABLE = 1;

    // Stores the command TODO.
    private static final String TODO = "TODO";

    // Stores the command DEADLINE.
    private static final String DEADLINE = "DEADLINE";

    // Stores the command EVENT.
    private static final String EVENT = "EVENT";

    /**
     * Constructor for AddCommand.
     * returns an instance of addCommand.
     */
    AddCommand() {
        super();
    }

    /**
     * adds the task to taskList.
     * @param taskList the list of user tasks.
     * @param text the string of the task to be added.
     * @throws MissingTimeArgumentException if the user missed time argument out.
     * @throw WrongDateArgumentException if the user inputs an invalid date.
     * @throw WrongTimeArgumentException if the user inputs an invalid time.
     */
    static void add(TaskList taskList, String text) {

        String[] split_text = text.split(SPACE, 2);
        String type = split_text[START_INDEX].toUpperCase();
        String message = split_text[1];

        Task task;

        switch (type) {
        case TODO:
            task = new Todo(message);
            break;
        case DEADLINE:
            Deadline.correctArgument(message);
            task = new Deadline(message);
            break;
        case EVENT:
            Event.correctArgument(message);
            task = new Event(message);
            break;
        default:
            task = new Task(text);
            break;
        }

        taskList.add(task);
        Ui.printAdded(task.getTask());
        Ui.printNumOfTasks(taskList.numOfTasks());
    }

    /**
     * adds the previous tasks to the list.
     * @param taskList the list of user tasks.
     * @param storage the string of the task to be added.
     */
    static void add(TaskList taskList, Storage storage) {
        for (String task: storage.loadPreviousTasks()) {
            if (Todo.isTodo(task)) {
                taskList.add(new Todo(task, DUMMY_VARIABLE));
            } else if (Deadline.isDeadline(task)) {
                taskList.add(new Deadline(task, DUMMY_VARIABLE));
            } else if (Event.isEvent(task)) {
                taskList.add(new Event(task, DUMMY_VARIABLE));
            } else {
                taskList.add(new Task(task, DUMMY_VARIABLE));
            }
        }
    }
}