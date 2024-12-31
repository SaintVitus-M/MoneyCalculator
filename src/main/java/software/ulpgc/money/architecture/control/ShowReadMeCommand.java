package software.ulpgc.money.architecture.control;

import software.ulpgc.money.architecture.view.ContentDisplay;

/**
 * The {@code ShowReadMeCommand} class implements the {@link Command} interface
 * and is responsible for executing the action of displaying the README or
 * informational content in the application.
 *
 * <p>This command is typically triggered by the user interface (e.g., a button click)
 * and interacts with the {@link ContentDisplay} component to show the relevant
 * information to the user.
 *
 * <p>The {@code ShowReadMeCommand} class provides the functionality to display
 * a "Read Me" or informational page, usually containing helpful instructions
 * or details about the application.
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public class ShowReadMeCommand implements Command {
    private final ContentDisplay contentDisplay;

    /**
     * Creates a new {@code ShowReadMeCommand} instance with the specified
     * {@link ContentDisplay} to display the information.
     *
     * @param contentDisplay the {@link ContentDisplay} instance used to show the content
     * @since       1.0
     */
    public ShowReadMeCommand(ContentDisplay contentDisplay) {
        this.contentDisplay = contentDisplay;
    }

    /**
     * Executes the command to show the "Read Me" or informational content by calling
     * the {@link ContentDisplay#showInfo()} method.
     * @since       1.0
     */
    @Override
    public void execute() {
        contentDisplay.showInfo();
    }
}
