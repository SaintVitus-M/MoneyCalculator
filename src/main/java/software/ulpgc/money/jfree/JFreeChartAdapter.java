package software.ulpgc.money.jfree;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;
import software.ulpgc.money.architecture.model.TimeSeriesChart;

import java.awt.*;

/**
 * The {@code JFreeChartAdapter} class provides a static method to adapt a {@link TimeSeriesChart}
 * and {@link TimeSeriesCollection} into a {@link JFreeChart} object. This class simplifies the
 * process of converting custom chart objects into JFreeChart's standard representation.
 *
 * <p>This adapter class is particularly useful when integrating custom time series charts with
 * JFreeChart, allowing the conversion of domain-specific chart objects to the JFreeChart framework.
 *
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
public class JFreeChartAdapter {
    /**
     * Adapts a {@link TimeSeriesChart} and a {@link TimeSeriesCollection} into a {@link JFreeChart}
     * object.
     *
     * <p>This method creates a {@link JFreeChart} object using the provided chart title, axis
     * labels, and time series data. It also sets the background color of the chart.</p>
     *
     * @param chart the {@link TimeSeriesChart} object containing the chart configuration
     * @param series the {@link TimeSeriesCollection} containing the time series data for the chart
     * @return a {@link JFreeChart} object representing the adapted chart
     * @since 1.0
     */
    public static JFreeChart adapt(TimeSeriesChart chart, TimeSeriesCollection series) {
        JFreeChart timeSeriesChart = ChartFactory.createTimeSeriesChart(
                chart.title(),
                chart.xAxisLabel(),
                chart.yAxisLabel(),
                series,
                false,
                false,
                false
        );
        timeSeriesChart.setBackgroundPaint(Color.LIGHT_GRAY);
        return timeSeriesChart;
    }
}
