package software.ulpgc.money.swing;

import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.Money;
import software.ulpgc.money.architecture.view.CurrencyDialog;
import software.ulpgc.money.architecture.view.MoneyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * {@code SwingMoneyDialog} is a GUI component that implements the {@link MoneyDialog} interface.
 * It provides an interface for the user to input an amount of money and select a source currency
 * from a given list of currencies. This dialog is a part of the Swing-based user interface
 * for currency exchange operations.
 *
 * <p>The dialog includes:
 * <ul>
 *   <li>A text field to input the amount of money.</li>
 *   <li>A dropdown (via {@link SwingCurrencyDialog}) for selecting the "from" currency.</li>
 * </ul>
 *
 * <p>This class is responsible for:
 * <ul>
 *   <li>Displaying the necessary UI components for the user to input data.</li>
 *   <li>Providing methods to retrieve the entered amount and selected currency.</li>
 *   <li>Converting the amount entered into a valid {@link Money} object.</li>
 * </ul>
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private JTextField amountField;
    private CurrencyDialog fromCurrencyDialog;

    /**
     * Constructs a new {@code SwingMoneyDialog} panel with a flow layout and sets the background color.
     */
    public SwingMoneyDialog() {
        this.setLayout(new FlowLayout());
        setBackground(SwingMainFrame.HEADER_COLOR);
    }

    /**
     * Defines and initializes the components of the dialog, including:
     * <ul>
     *   <li>A label and text field for the amount of money.</li>
     *   <li>A label and dialog for selecting the "from" currency.</li>
     * </ul>
     *
     * @param currencies A list of {@link Currency} objects that the user can select from.
     * @return The current {@code SwingMoneyDialog} instance.
     * @since 1.0
     */
    @Override
    public MoneyDialog define(List<Currency> currencies) {
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.BLACK);
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.BLACK);
        add(amountLabel);
        add(createAmountField());
        add(fromLabel);
        add(createFromCurrencyDialog(currencies));
        return this;
    }

    /**
     * Creates and initializes the currency selection dialog with the provided list of currencies.
     *
     * @param currencies A list of {@link Currency} objects.
     * @return The {@link SwingCurrencyDialog} for currency selection.
     * @since 1.0
     */
    private Component createFromCurrencyDialog(List<Currency> currencies) {
        SwingCurrencyDialog dialog = new SwingCurrencyDialog();
        dialog.define(currencies);
        this.fromCurrencyDialog = dialog;
        return dialog;
    }

    /**
     * Creates and initializes the text field for entering the amount of money.
     *
     * @return A {@link JTextField} for entering the amount.
     * @since 1.0
     */
    private Component createAmountField() {
        JTextField textField = new JTextField();
        textField.setColumns(5);
        this.amountField = textField;
        return textField;
    }

    /**
     * Sets the selected index in the {@link SwingCurrencyDialog}.
     *
     * @param index The index to set in the currency dialog.
     * @since 1.0
     */
    @Override
    public void set(int index) {
        fromCurrencyDialog.set(index);
    }

    /**
     * Retrieves the {@link Money} object representing the amount entered and the selected source currency.
     * The amount is parsed from the text field and the selected currency is retrieved from the
     * {@link CurrencyDialog}.
     *
     * @return A {@link Money} object containing the amount and the selected currency.
     * @since 1.0
     */
    @Override
    public Money get() {
        return new Money(toDouble(amountField.getText()), fromCurrencyDialog.get());
    }

    /**
     * Converts the text input from the amount field to a double value.
     * If the input cannot be parsed as a valid number, it returns 0.
     *
     * @param text The string to be converted to a double.
     * @return The double value of the text input, or 0 if parsing fails.
     * @since 1.0
     */
    private double toDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Retrieves the index of the currently selected currency in the currency dialog.
     *
     * @return The index of the selected currency.
     * @since 1.0
     */
    @Override
    public int getSelectedIndex() {
        return fromCurrencyDialog.getSelectedIndex();
    }
}
