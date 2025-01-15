package software.ulpgc.money.swing;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * The {@code SwingReadMeDialog} class is a custom extension of {@link JEditorPane} designed
 * to display a static HTML readme file in a Swing-based Java application.
 *
 * <p>This class initializes itself as a non-editable HTML viewer and attempts to load an HTML
 * file from the project's resources directory. If the file cannot be loaded, it displays an
 * error message in HTML format.</p>
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public class SwingReadMeDialog extends JEditorPane {
    /**
     * Constructs a new {@code SwingReadMeDialog} instance.
     *
     * <p>The constructor initializes the editor pane by setting its content type to HTML,
     * making it non-editable, and attempting to load the HTML file.</p>
     */
    public SwingReadMeDialog() {
        initEditorPane();
    }

    /**
     * Initializes the editor pane with default settings and loads the HTML file.
     *
     * <p>This method sets the editor pane's content type to "text/html", disables editing,
     * and attempts to load an HTML file located in the project's {@code src/main/resources/} directory.
     * If the file cannot be loaded, it displays an error message instead.</p>
     *
     * <h3>Implementation Details:</h3>
     * <ul>
     *   <li>The HTML file is expected to be located at {@code src/main/resources/index.html}.</li>
     *   <li>If the file cannot be loaded (e.g., due to missing file or invalid path), the pane
     *       displays an error message with a heading.</li>
     * </ul>
     *
     * @see JEditorPane#setContentType(String)
     * @see JEditorPane#setEditable(boolean)
     * @see JEditorPane#setPage(java.net.URL)
     * @since       1.0
     */
    private void initEditorPane() {
        setContentType("text/html");
        setEditable(false);
        try {
            File htmlFile = new File("src/main/resources/index.html");
            setPage(htmlFile.toURI().toURL());
        } catch (IOException e) {
            setText("<html><body><h1>Error while loading HTML file</h1></body></html>");
        }
    }
}
