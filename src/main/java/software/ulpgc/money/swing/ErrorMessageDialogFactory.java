package software.ulpgc.money.swing;

import javax.swing.*;
import java.awt.*;

/**
 * A utility class for displaying error message dialogs in a graphical user interface.
 * This class provides a simple method to create and show error dialogs using {@link JOptionPane}.
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0.1
 */
public class ErrorMessageDialogFactory {
    /**
     * Displays an error message dialog with the specified message and title.
     *
     * @param parentComponent the parent component for the dialog; can be {@code null} for a default parent.
     * @param message the message to display in the dialog; can be any {@link Object}, including strings.
     * @param title the title of the dialog window.
     *
     * @throws HeadlessException if the environment does not support a keyboard, display, or mouse.
     * @since       1.0.1
     */
    public static void showErrorMessage(Component parentComponent, Object message, String title) {
        JOptionPane.showMessageDialog(parentComponent,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }
}
