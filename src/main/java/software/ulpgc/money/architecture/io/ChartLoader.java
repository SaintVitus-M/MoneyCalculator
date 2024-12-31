package software.ulpgc.money.architecture.io;

import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.TimeSeriesChart;

/**
 * The {@code ChartLoader} interface defines the contract for loading a time series chart
 * that represents the exchange rate between two currencies over a period of time.
 * Implementations of this interface are responsible for retrieving the necessary data
 * and rendering it into a time series chart.
 *
 * <p>This interface is typically used in applications that need to visualize the
 * historical exchange rate data between two currencies, such as currency converter
 * applications or financial data analytics platforms.
 *
 * <p>The method {@link #load(Currency, Currency)} should return a time series chart
 * that displays the exchange rate from one currency to another over time.
 *
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
public interface ChartLoader {
    /**
     * Loads a time series chart representing the exchange rate between two currencies.
     *
     * @param from The source currency.
     * @param to The target currency.
     * @return A {@link TimeSeriesChart} that displays the exchange rate over time.
     * @since       1.0
     */
    TimeSeriesChart load(Currency from, Currency to);
}
