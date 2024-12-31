package software.ulpgc.money.architecture.model;

/**
 * Represents the value of the exchange rate from currency A to currency B with a timestamp.
 *
 * @param from {@code Currency} A.
 * @param to {@code Currency} B.
 * @param date Timestamp of the exchange rate.
 * @param rate Value of the exchange rate.
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public record ExchangeRate(Currency from, Currency to, String date, double rate) {
}
