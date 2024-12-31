package software.ulpgc.money.architecture.model;

import java.util.Map;


/**
 * Represents a sequence of data items in the form (period, rate value) where 'period'
 * is some instance of {@code LocalDate}.
 *
 *
 * @param from {@code Currency} A.
 * @param to {@code Currency} B.
 * @param rates {@code Map} Sequence of exchange rate values.
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public record ExchangeRateTimeSeries(Currency from, Currency to, Map<String, Double> rates) {
}
