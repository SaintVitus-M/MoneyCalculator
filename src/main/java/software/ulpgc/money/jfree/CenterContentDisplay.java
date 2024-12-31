package software.ulpgc.money.jfree;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import software.ulpgc.money.architecture.model.ExchangeRateTimeSeries;
import software.ulpgc.money.architecture.model.TimeSeriesChart;
import software.ulpgc.money.architecture.view.ContentDisplay;
import software.ulpgc.money.swing.SwingMainFrame;
import software.ulpgc.money.swing.SwingReadMeDialog;

import javax.swing.*;
import java.awt.*;

/**
 * {@code CenterContentDisplay} is a JPanel that implements the {@link ContentDisplay} interface.
 * It is responsible for displaying content, such as charts and informational panels,
 * in the central area of the user interface. This class supports rendering an animated chart
 * based on exchange rate data and also shows informational dialogs when required.
 *
 * <p>This class uses a {@link TimeSeriesChart} for rendering time-series data and an
 * {@link AnimationThread} to animate exchange rate data over time. It is designed to be part of
 * a larger Swing-based user interface, where it manages content updates in response to user actions.
 *
 * @author      Vit Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public class CenterContentDisplay extends JPanel implements ContentDisplay {
    private TimeSeries series;
    private AnimationThread animationThread;

    /**
     * Constructs a new {@code CenterContentDisplay} panel with a BorderLayout and a default background color.
     */
    public CenterContentDisplay() {
        setLayout(new BorderLayout());
        setBackground(SwingMainFrame.BODY_COLOR);
    }

    /**
     * Displays the specified chart and animates the associated exchange rate time series.
     * This method removes any existing content, adds the chart, and starts the animation thread.
     *
     * @param chart The chart to be displayed.
     * @param exchangeRateSeries The exchange rate time series to be animated.
     * @since 1.0
     */
    @Override
    public void showChart(TimeSeriesChart chart, ExchangeRateTimeSeries exchangeRateSeries) {
        removeAll();
        add(createChartPanel(chart));
        revalidate();
        doAnimation(exchangeRateSeries);
    }

    /**
     * Displays an informational dialog, removing any existing content in the panel.
     * This method is used to show additional content like a "ReadMe" dialog.
     * @since 1.0
     */
    @Override
    public void showInfo() {
        removeAll();
        add(new SwingReadMeDialog());
        revalidate();
    }

    /**
     * Starts the animation process for displaying the exchange rate time series.
     * If an animation is already running, it will be interrupted before starting the new animation.
     *
     * @param exchangeRateSeries The exchange rate time series to be animated.
     * @since 1.0
     */
    private void doAnimation(ExchangeRateTimeSeries exchangeRateSeries) {
        if (animationThread.isAlive()) {
            animationThread.interrupt();
        }
        series.clear();
        animationThread.setRates(exchangeRateSeries);
        animationThread.start();
    }

    /**
     * Creates a {@link Component} that contains the chart panel with the provided time series data.
     * Initializes the time series and animation thread required for the chart display.
     *
     * @param chart The chart to be displayed.
     * @return The component containing the chart panel.
     * @since 1.0
     */
    private Component createChartPanel(TimeSeriesChart chart) {
        this.series = new TimeSeries("Data");
        this.animationThread = new AnimationThread(series);
        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        return new ChartPanel(adapt(chart, dataset));
    }

    /**
     * Adapts the given {@link TimeSeriesChart} to create a {@link JFreeChart}.
     * This method uses an adapter to convert the chart and dataset into a JFreeChart object.
     *
     * @param chart The chart to be adapted.
     * @param series The time series dataset to be used in the chart.
     * @return The adapted JFreeChart.
     * @since 1.0
     */
    private JFreeChart adapt(TimeSeriesChart chart, TimeSeriesCollection series) {return JFreeChartAdapter.adapt(chart, series);}
}
