package software.ulpgc.money.architecture.view;

import software.ulpgc.money.architecture.model.Currency;

import java.util.List;

/**
 * The {@code CurrencyDialog} interface defines the contract for dialogs that allow
 * the user to select and manage currencies in a graphical user interface (GUI).
 * Implementations of this interface are expected to provide functionality for
 * defining the available currencies, selecting a specific currency, and retrieving
 * or setting the selected currency index.
 *
 * <p>This interface is designed for use in applications where users can interactively
 * select from a list of currencies, and the selected currency can be used for further
 * calculations or actions (such as currency conversion).
 *
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
public interface CurrencyDialog {
    /**
     * Initializes the dialog with a list of available currencies.
     *
     * @param currencies the list of {@link Currency} objects to be displayed in the dialog
     * @return the current instance of the dialog (to allow for method chaining)
     * @since       1.0
     */
    CurrencyDialog define(List<Currency> currencies);
    /**
     * Retrieves the currently selected {@link Currency} object.
     *
     * @return the selected {@link Currency} object
     * @since       1.0
     */
    Currency get();
    /**
     * Sets the selected currency based on the provided index.
     *
     * @param index the index of the currency to select
     * @since       1.0
     */
    void set(int index);
    /**
     * Returns the index of the currently selected currency.
     *
     * @return the index of the selected currency
     * @since       1.0
     */
    int getSelectedIndex();
}
