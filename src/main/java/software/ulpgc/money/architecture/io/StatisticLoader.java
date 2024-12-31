package software.ulpgc.money.architecture.io;

import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.ExchangeRateTimeSeries;

/**
 * The {@code StatisticLoader} interface defines the contract for classes that are responsible for
 * loading exchange rate statistics between two currencies. It provides a method to retrieve
 * a time series of exchange rates for a given currency pair.
 *
 * <p>This interface is typically implemented by services or data providers that fetch
 * historical exchange rate data from external sources, such as financial APIs or databases.
 * The {@code loadStatistic} method allows for fetching exchange rate data for a specific period
 * (e.g., daily, monthly) between two currencies, represented by the {@link Currency} objects.
 *
 * <p>The returned {@link ExchangeRateTimeSeries} contains the exchange rates over time, which can
 * then be used for further processing, analysis, or visualization.
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public interface StatisticLoader {
    /**
     * Loads the exchange rate statistics for a given currency pair.
     *
     * @param from The source currency (e.g., USD).
     * @param to The target currency (e.g., EUR).
     * @return An {@link ExchangeRateTimeSeries} containing the exchange rate data
     *         for the specified currency pair.
     * @since       1.0
     */
    ExchangeRateTimeSeries loadStatistic(Currency from, Currency to);
}
