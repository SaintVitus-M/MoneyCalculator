package software.ulpgc.money.architecture.model;

/**
 * Represents an amount of a currency.
 *
 *
 * @param amount {@code Double} Quantity of currency.
 * @param currency {@code Currency} Referred currency.
 *
 * @author      Vit Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public record Money(double amount, Currency currency) {
    /**
     * Returns a string representation of this object, which includes the amount and currency.
     *
     * <p>The string is formatted as:
     * <pre>{@code
     * amount currency
     * }</pre>
     * For example, if the amount is 100.0 and the currency is "USD", the result will be:
     * <pre>{@code
     * 100.0 USD
     * }</pre>
     *
     * <p>This method overrides the {@link Object#toString()} method.
     *
     * @return a string representation of this object in the format "amount currency".
     * @since       1.0
     */
    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
