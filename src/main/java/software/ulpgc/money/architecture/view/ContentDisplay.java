package software.ulpgc.money.architecture.view;

import software.ulpgc.money.architecture.model.ExchangeRateTimeSeries;
import software.ulpgc.money.architecture.model.TimeSeriesChart;

/**
 * The {@code ContentDisplay} interface defines the contract for classes that are responsible
 * for displaying content related to exchange rate charts and other information.
 * Implementations of this interface should provide methods to display a chart of exchange rates
 * and general information.
 *
 * <p>This interface is typically used in applications where visual representation of data
 * (such as charts) and additional informational content (such as textual details) are required.
 *
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
public interface ContentDisplay {
    /**
     * Displays a time series chart representing exchange rate data.
     *
     * @param chart The chart to display, typically a visual representation of exchange rates over time.
     * @param series The exchange rate time series data that should be visualized on the chart.
     * @since       1.0
     */
    void showChart(TimeSeriesChart chart, ExchangeRateTimeSeries series);
    /**
     * Displays general information related to the exchange rate data or application.
     * This method may present textual information, instructions, or other relevant details.
     * @since       1.0
     */
    void showInfo();
}
