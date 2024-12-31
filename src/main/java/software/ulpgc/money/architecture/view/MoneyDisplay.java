package software.ulpgc.money.architecture.view;

import software.ulpgc.money.architecture.model.Money;

/**
 * The {@code MoneyDisplay} interface defines a contract for displaying
 * currency conversion information between two {@link Money} objects.
 * Implementing classes are expected to provide a way to visually present
 * or output the exchange details from one currency to another.
 *
 * <p>The primary method in this interface, {@link #show(Money, Money)},
 * accepts two {@code Money} objects representing the source and target
 * currencies and provides a mechanism to display or present the conversion
 * information to the user.
 *
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
@FunctionalInterface
public interface MoneyDisplay {
    /**
     * Displays the currency conversion information between two {@link Money} objects.
     *
     * @param from the {@code Money} object representing the source currency
     * @param to the {@code Money} object representing the target currency
     * @since       1.0
     */
    void show(Money from, Money to);
}
