package software.ulpgc.money.swing;

import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.view.CurrencyDialog;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The {@code SwingCurrencyDialog} class is a Swing-based implementation of the
 * {@link software.ulpgc.money.architecture.view.CurrencyDialog} interface.
 * It provides a graphical component for selecting a currency from a list of options using a
 * {@link JComboBox}.
 *
 * <p>This class extends {@link JPanel} and leverages Swing components to create a
 * user-friendly currency selection dialog, which can be easily integrated into
 * Swing applications. The dialog supports defining a list of currencies, retrieving
 * the selected currency, and managing the selected index programmatically.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Displays a dropdown menu for selecting a currency.</li>
 *   <li>Allows customization of the list of available currencies.</li>
 *   <li>Supports getting and setting the selected currency programmatically.</li>
 * </ul>
 *
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
public class SwingCurrencyDialog extends JPanel implements software.ulpgc.money.architecture.view.CurrencyDialog {
    /**The dropdown menu for selecting a currency.*/
    private JComboBox<Currency> currencyComboBox;

    /**
     * Constructs a {@code SwingCurrencyDialog} instance with a default layout and background color.
     * The dialog uses a {@link FlowLayout} and inherits the header color from {@code SwingMainFrame}.
     */
    public SwingCurrencyDialog() {
        setLayout(new FlowLayout());
        setBackground(SwingMainFrame.HEADER_COLOR);
    }

    /**
     * Defines the list of currencies to display in the dropdown menu.
     *
     * @param currencies the list of {@link Currency} objects to be shown in the dropdown menu.
     * @return this {@code CurrencyDialog} instance for method chaining.
     * @since       1.0
     */
    @Override
    public CurrencyDialog define(List<Currency> currencies) {
        add(createCurrencyComboBox(currencies));
        return this;
    }

    /**
     * Creates and initializes the currency dropdown menu.
     *
     * @param currencies the list of {@link Currency} objects to populate the dropdown.
     * @return the {@link Component} representing the dropdown menu.
     * @since       1.0
     */
    private Component createCurrencyComboBox(List<Currency> currencies) {
        JComboBox<Currency> selector = new JComboBox<>();
        for (Currency currency : currencies) selector.addItem(currency);
        this.currencyComboBox = selector;
        return selector;
    }

    /**
     * Retrieves the currently selected currency from the dropdown menu.
     *
     * @return the selected {@link Currency}.
     * @since       1.0
     */
    @Override
    public Currency get() {
        return currencyComboBox.getItemAt(currencyComboBox.getSelectedIndex());
    }

    /**
     * Sets the selected index of the dropdown menu.
     *
     * @param index the index of the currency to select.
     * @since       1.0
     */
    @Override
    public void set(int index) {
        currencyComboBox.setSelectedIndex(index);
    }

    /**
     * Retrieves the index of the currently selected item in the dropdown menu.
     *
     * @return the selected index.
     * @since       1.0
     */
    @Override
    public int getSelectedIndex() {
        return currencyComboBox.getSelectedIndex();
    }
}
