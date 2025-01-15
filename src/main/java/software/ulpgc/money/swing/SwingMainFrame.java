package software.ulpgc.money.swing;

import software.ulpgc.money.architecture.control.Command;
import software.ulpgc.money.architecture.view.ContentDisplay;
import software.ulpgc.money.architecture.view.CurrencyDialog;
import software.ulpgc.money.architecture.view.MoneyDialog;
import software.ulpgc.money.architecture.view.MoneyDisplay;
import software.ulpgc.money.jfree.CenterContentDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code SwingMainFrame} class represents the main window of the Money Calculator
 * application. It extends {@link JFrame} and provides a user interface for interacting
 * with the currency exchange functionality. The frame contains various components,
 * including buttons, dialogs, and displays for calculating and displaying money
 * exchange rates and histograms.
 *
 * <p>The class manages different UI elements such as:
 * <ul>
 *   <li>Tool panel with buttons for calculating exchange rates and swapping currencies.</li>
 *   <li>Bottom panel displaying author information.</li>
 *   <li>Money display and content display panels for showing exchange rates and results.</li>
 *   <li>Dialogs for entering money and selecting currencies.</li>
 * </ul>
 *
 * <p>Additionally, this class supports commands for different actions (e.g., calculating exchange
 * rates, swapping currencies, and displaying information), which are stored in a command map
 * and triggered by the corresponding buttons.
 *
 * <h2>Constructor:</h2>
 * <p>The constructor initializes the main frame, sets its layout, and adds the various panels.
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public class SwingMainFrame extends JFrame {
    // Map for storing commands actions by their names.
    private final Map<String, Command> commands;
    // UI components
    private MoneyDisplay moneyDisplay;
    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;
    private ContentDisplay contentDisplay;
    // Color constants for styling.
    public static final Color HEADER_COLOR = new Color(0xF79C0E);
    public static final Color BODY_COLOR = new Color(27, 25, 25,255);
    public static final Color ULPGC_BLUE = new Color(13, 104, 163,255);
    public static final Color BODY_FONT_COLOR = new Color(172, 165, 165,255);

    /**
     * Constructs a new {@code SwingMainFrame}. Initializes the frame, sets its properties,
     * and adds the necessary panels to the layout.
     *
     * @throws HeadlessException if the environment does not support a display
     * @since       1.0
     */
    public SwingMainFrame() throws HeadlessException {
        this.commands = new HashMap<>();
        setTitle("Money Calculator App");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(createIconImage());
        add(createToolPane(), BorderLayout.NORTH);
        add(createGraphPane(), BorderLayout.CENTER);
        add(createMoneyDisplayPane(), BorderLayout.EAST);
        add(createBottomPane(), BorderLayout.SOUTH);
    }

    /**
     * Creates the image for the App's main icon.
     *
     * @return A {@code Image} from the main icon.
     */
    private Image createIconImage() {
        ImageIcon mainIcon = new ImageIcon("src/main/resources/main.png");
        return mainIcon.getImage();
    }


    /**
     * Adds a new command to the command map.
     *
     * @param name the name of the command
     * @param command the command to be executed
     * @since       1.0
     */
    public void putCommand(String name, Command command) {commands.put(name, command);}

    /**
     * Retrieves a command by its name.
     *
     * @param name the name of the command
     * @return the command associated with the specified name, or {@code null} if not found
     * @since       1.0
     */
    public Command getCommand(String name) {return commands.get(name);}

    /**
     * Returns the money display component.
     *
     * @return the money display component
     * @since       1.0
     */
    public MoneyDisplay moneyDisplay() {return moneyDisplay;}

    /**
     * Returns the money dialog component.
     *
     * @return the money dialog component
     * @since       1.0
     */
    public MoneyDialog moneyDialog() {return moneyDialog;}

    /**
     * Returns the currency dialog component.
     *
     * @return the currency dialog component
     * @since       1.0
     */
    public CurrencyDialog currencyDialog() {return currencyDialog;}

    /**
     * Returns the content display component.
     *
     * @return the content display component
     * @since       1.0
     */
    public ContentDisplay contentDisplay() {return contentDisplay;}

    /**
     * Creates the tool panel, which contains buttons for calculating exchange rates,
     * swapping currencies, and a button for showing information.
     *
     * @return the tool panel
     * @since       1.0
     */
    private Component createToolPane() {
        // Creates new JPanel with border and background color.
        JPanel toolPane = new JPanel(new FlowLayout());
        toolPane.setBorder(BorderFactory.createLineBorder(ULPGC_BLUE));
        toolPane.setBackground(HEADER_COLOR);

        // Creates the 'Calculate' button with Command Action Listener.
        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(_ -> commands.get("exchange money").execute());

        // Creates the swap currencies icon with Command Action Listener.
        ImageIcon swapIcon = new ImageIcon("src/main/resources/swap.png", "Swap");
        JButton swapButton = new JButton(swapIcon);
        swapButton.addActionListener(_ -> commands.get("swap").execute());

        // Creates a 'To' label for target currency.
        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.BLACK);

        // Creates an independent JPanel for the info button.
        JPanel readMePane = new JPanel();
        ImageIcon infoIcon = new ImageIcon("src/main/resources/info.png", "Info");
        JButton infoButton = new JButton(infoIcon);
        infoButton.addActionListener(_ -> commands.get("show info").execute());
        readMePane.add(infoButton);
        readMePane.setBackground(HEADER_COLOR);
        readMePane.setBorder(BorderFactory.createEmptyBorder(0, 55, 0, 0));

        // Add all the components to the tool pane.
        toolPane.add(createMoneyDialog());
        toolPane.add(swapButton);
        toolPane.add(toLabel);
        toolPane.add(createCurrencyDialog());
        toolPane.add(calculateButton);
        toolPane.add(readMePane);
        return toolPane;
    }

    /**
     * Creates the bottom panel of the frame, which contains author information.
     *
     * @return the bottom panel
     * @since       1.0
     */
    private JPanel createBottomPane() {
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(HEADER_COLOR);
        ImageIcon logo = new ImageIcon("src/main/resources/logo_ulpgc.png");
        JLabel author = new JLabel("CURSO 2024-2025 | ULPGC | IS2 | G42 | MONEY CALCULATOR | VIT MIKULA | 2024", logo, JLabel.CENTER);
        author.setFont(new Font("Verdana", Font.BOLD, 11));
        author.setForeground(ULPGC_BLUE);
        bottomPanel.add(author);
        return bottomPanel;
    }

    /**
     * Creates the money display pane, which shows the money exchange rates.
     *
     * @return the money display pane
     * @since       1.0
     */
    private Component createMoneyDisplayPane() {
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        this.moneyDisplay = display;
        return display;
    }

    /**
     * Creates the graph pane, which shows the graphical content of the application.
     *
     * @return the graph pane
     * @since       1.0
     */
    private Component createGraphPane() {
        CenterContentDisplay display = new CenterContentDisplay();
        contentDisplay = display;
        return display;
    }

    /**
     * Creates the currency dialog component for selecting currencies.
     *
     * @return the currency dialog component
     * @since       1.0
     */
    private Component createCurrencyDialog() {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        this.currencyDialog = dialog;
        return dialog;
    }

    /**
     * Creates the money dialog component for entering amounts of money.
     *
     * @return the money dialog component
     * @since       1.0
     */
    private Component createMoneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog();
        this.moneyDialog = dialog;
        return dialog;
    }
}
