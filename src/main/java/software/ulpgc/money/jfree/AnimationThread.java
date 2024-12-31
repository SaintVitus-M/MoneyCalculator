package software.ulpgc.money.jfree;

import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import software.ulpgc.money.architecture.model.ExchangeRateTimeSeries;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

/**
 *  The {@code AnimationThread} class is a specialized thread for managing
 *  the animation of a JFreeChart. This thread repeatedly updates the chart
 *  to create a smooth animation effect by periodically refreshing the displayed data.
 *
 *  <p>It extends the {@link Thread} class and provides methods to start, stop,
 *  and control the animation loop. This class ensures efficient handling of
 *  thread lifecycle and chart rendering.</p>
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public class AnimationThread extends Thread {
    private ExchangeRateTimeSeries exchangeRateSeries;
    private final TimeSeries timeSeries;

    /**
     * Constructs an {@code AnimationThread} for animation specified {@link TimeSeries}.
     *
     * @param timeSeries The time series to update (must not be {@code null}).
     * @since 1.0
     */
    public AnimationThread(TimeSeries timeSeries) {
        if (timeSeries == null) {
            throw new IllegalArgumentException("TimeSeries cannot be null");
        }
        this.timeSeries = timeSeries;
    }

    /**
     * Sets new {@code ExchangeRateTimeSeries} with data to update in the chart.
     *
     * @param newRates New time series with data.
     * @since 1.0
     */
    public void setRates(ExchangeRateTimeSeries newRates) {
        this.exchangeRateSeries = newRates;
    }

    /**
     * Starts the animation loop. The thread repeatedly updates the time series at the specified interval
     * until it has no data to update.
     * @since 1.0
     */
    @Override
    public void run() {
        // Sorts time series chronologically.
        TreeMap<String, Double> sortedData = new TreeMap<>(exchangeRateSeries.rates());
        for(Map.Entry<String, Double> entry : sortedData.entrySet()) {
            if(Thread.currentThread().isInterrupted()) {
                break;
            }
            SwingUtilities.invokeLater(() ->
                    this.timeSeries.addOrUpdate(Day.parseDay(entry.getKey()), entry.getValue()));
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
