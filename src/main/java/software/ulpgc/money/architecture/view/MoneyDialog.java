package software.ulpgc.money.architecture.view;

import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.Money;

import java.util.List;

/**
 * The {@code MoneyDialog} interface defines the contract for dialogs
 * that handle the selection and management of monetary values.
 * It provides methods for defining available currencies, retrieving the
 * selected currency, and setting or retrieving the index of the currently
 * selected currency in a list.
 *
 * <p>This interface is typically implemented by classes that need to present
 * a dialog to the user for selecting a currency or managing money amounts,
 * such as a currency converter application or a financial tool.
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public interface MoneyDialog {
    /**
     * Defines the list of currencies available in the dialog.
     *
     * @param currencies a list of {@link Currency} objects to be displayed in the dialog
     * @return the current {@code MoneyDialog} instance
     * @since       1.0
     */
    MoneyDialog define(List<Currency> currencies);
    /**
     * Retrieves the selected {@link Money} object, which includes the selected currency
     * and its associated value.
     *
     * @return the selected {@link Money} object
     * @since       1.0
     */
    Money get();
    /**
     * Sets the selected currency by its index in the list of available currencies.
     *
     * @param index the index of the currency to select
     * @since       1.0
     */
    void set(int index);
    /**
     * Returns the index of the currently selected currency in the list of available currencies.
     *
     * @return the index of the selected currency
     * @since       1.0
     */
    int getSelectedIndex();
}
