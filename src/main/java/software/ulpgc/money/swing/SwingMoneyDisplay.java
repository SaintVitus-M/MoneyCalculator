package software.ulpgc.money.swing;

import software.ulpgc.money.architecture.model.Money;
import software.ulpgc.money.architecture.view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The {@code SwingMoneyDisplay} class is a Swing-based implementation of the
 * {@link software.ulpgc.money.architecture.view.MoneyDisplay} interface.
 * It provides a graphical user interface (GUI) component to display currency conversion
 * results, including the source currency, target currency, and the timestamp of the last update.
 *
 * <p>This class is built using Swing components and styled to align with the application's theme.
 * It includes the following elements:
 * <ul>
 *   <li>A label to display the source currency and amount.</li>
 *   <li>A label to display the converted target currency and amount.</li>
 *   <li>A label to display the timestamp of the last data update.</li>
 *   <li>An icon (ULPGC logo) displayed at the bottom of the panel.</li>
 * </ul>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Dynamic display of currency conversion results via the {@link #show(Money, Money)} method.</li>
 *   <li>Automatic determination and display of the last update timestamp based on business logic.</li>
 *   <li>Custom styling for fonts, colors, and layout to maintain a consistent application theme.</li>
 * </ul>
 *
 * <h2>Thread Safety:</h2>
 * <p>This class is designed to run on the Event Dispatch Thread (EDT) as it updates Swing components.
 * Ensure that all calls to its methods are performed on the EDT to avoid concurrency issues.</p>
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public class SwingMoneyDisplay extends JPanel implements MoneyDisplay {
    private JLabel fromMoneyLabel;
    private JLabel resultMoneyLabel;
    private JLabel timestampLabel;
    private static final Icon ulpgcIcon = new ImageIcon("src/main/resources/ulpgc.png");

    /**
     * Constructs a new {@code SwingMoneyDisplay} instance with a predefined layout and styles.
     * This constructor initializes the panel, sets up its components, and applies custom styles.
     * @since       1.0
     */
    public SwingMoneyDisplay() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(SwingMainFrame.BODY_COLOR);
        add(createLabelPane(), BorderLayout.CENTER);
        add(new JLabel(ulpgcIcon), BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Creates a panel containing the labels for displaying the money conversion results and timestamp.
     *
     * @return a {@code Component} containing the labels for the display.
     * @since       1.0
     */
    private Component createLabelPane() {
        JPanel labelPane = new JPanel(new GridLayout(3, 1));
        labelPane.setBackground(SwingMainFrame.BODY_COLOR);
        this.fromMoneyLabel = new JLabel();
        this.resultMoneyLabel = new JLabel();
        this.timestampLabel = new JLabel();
        fromMoneyLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        fromMoneyLabel.setForeground(SwingMainFrame.BODY_FONT_COLOR);
        resultMoneyLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        resultMoneyLabel.setForeground(SwingMainFrame.BODY_FONT_COLOR);
        timestampLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        timestampLabel.setForeground(SwingMainFrame.BODY_FONT_COLOR);
        labelPane.add(fromMoneyLabel);
        labelPane.add(resultMoneyLabel);
        labelPane.add(timestampLabel);
        return labelPane;
    }

    /**
     * Updates the display to show the provided money conversion details.
     *
     * @param from the source {@code Money} object, representing the original amount and currency.
     * @param to   the target {@code Money} object, representing the converted amount and currency.
     * @since       1.0
     */

    @Override
    public void show(Money from, Money to) {
        fromMoneyLabel.setText(from.amount() + " " + from.currency().code() + " =");
        resultMoneyLabel.setText(to.amount() + " " + to.currency().code());
        setLastUpdateWithCondition();
    }

    /**
     * Sets the last update timestamp based on the current time.
     * If the current time is before 16:00, the timestamp is set to the previous day's date.
     * Otherwise, it is set to the current date.
     * @since       1.0
     */
    private void setLastUpdateWithCondition() {
        LocalDate currentDate = LocalDate.now();
        if (LocalTime.now().isBefore(LocalTime.of(16, 0))) {
            LocalDate previousDate = currentDate.minusDays(1);
            setLastUpdate(previousDate);
        } else {
            setLastUpdate(currentDate);
        }
    }

    /**
     * Updates the timestamp label to display the last update date and a fixed time of 16:00.
     *
     * @param date the {@code LocalDate} representing the date of the last update.
     * @since       1.0
     */
    private void setLastUpdate(LocalDate date) {
        timestampLabel.setText("Last update: " + date + ", " + LocalTime.of(16, 0));
    }

}
