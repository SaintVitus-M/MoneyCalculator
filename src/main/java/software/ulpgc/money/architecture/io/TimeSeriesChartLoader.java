package software.ulpgc.money.architecture.io;

import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.TimeSeriesChart;

/**
 * Loads an exchange rates {@code TimeSeriesChart} from selected currencies.
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public class TimeSeriesChartLoader implements ChartLoader {

    /**
     * Creates a new Time Series Chart of rates from currency A to currency B.
     *
     * @param from {@code Currency} A.
     * @param to {@code Currency} B.
     * @return {@code TimeSeriesChart} New Time Series Chart with title and axis labels.
     * @since       1.0
     */
    @Override
    public TimeSeriesChart load(Currency from, Currency to) {
        return new TimeSeriesChart(
                from.code() + "/" + to.code(),
                "Date",
                "Rate"
        );
    }
}
