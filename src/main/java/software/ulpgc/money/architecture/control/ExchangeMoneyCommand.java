package software.ulpgc.money.architecture.control;

import software.ulpgc.money.architecture.io.ChartLoader;
import software.ulpgc.money.architecture.io.ExchangeRateLoader;
import software.ulpgc.money.architecture.io.StatisticLoader;
import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.ExchangeRate;
import software.ulpgc.money.architecture.model.Money;
import software.ulpgc.money.architecture.view.ContentDisplay;
import software.ulpgc.money.architecture.view.CurrencyDialog;
import software.ulpgc.money.architecture.view.MoneyDialog;
import software.ulpgc.money.architecture.view.MoneyDisplay;
import software.ulpgc.money.swing.ErrorMessageDialogFactory;

/**
 * The {@code ExchangeMoneyCommand} class implements the {@link Command} interface
 * and is responsible for performing currency exchange rate calculations and
 * displaying an animated histogram of exchange rates over a year.
 * This command is triggered when the "Calculate" button is pressed in the application.
 *
 * <p>The primary responsibilities of this class are:
 * <ul>
 *   <li>Calculating the exchange amount based on the selected currencies and input amount.</li>
 *   <li>Fetching historical exchange rate data for the past year.</li>
 *   <li>Displaying an animated histogram that visualizes the historical rates.</li>
 * </ul>
 *
 * <p>This class relies on external APIs to fetch real-time and historical
 * exchange rates. The histogram is rendered using an integrated charting library.
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public class ExchangeMoneyCommand implements Command {
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final ExchangeRateLoader exchangeRateLoader;
    private final MoneyDisplay moneyDisplay;
    private final ContentDisplay contentDisplay;
    private final ChartLoader chartLoader;
    private final StatisticLoader statisticLoader;

    /**
     * Constructs an {@code ExchangeMoneyCommand} instance, initializing it with the necessary components
     * for handling user input, fetching exchange rate data, and displaying results.
     *
     * @param moneyDialog           the dialog to retrieve the amount and source money to be converted
     * @param currencyDialog        the dialog to retrieve the target currency
     * @param exchangeRateLoader    the loader to fetch the current exchange rates for conversion
     * @param moneyDisplay          the component responsible for displaying the converted money amount
     * @param contentDisplay        the component responsible for displaying exchange rate time series in a histogram
     * @param chartLoader           the loader to generate the historical exchange rate chart
     * @param statisticLoader       the loader to generate the exchange rate time series.
     */
    public ExchangeMoneyCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, ExchangeRateLoader exchangeRateLoader, MoneyDisplay moneyDisplay, ContentDisplay contentDisplay, ChartLoader chartLoader, StatisticLoader statisticLoader) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.exchangeRateLoader = exchangeRateLoader;
        this.moneyDisplay = moneyDisplay;
        this.contentDisplay = contentDisplay;
        this.chartLoader = chartLoader;
        this.statisticLoader = statisticLoader;
    }

    /**
     * Executes the currency exchange process. This method is triggered as part of the
     * Command pattern implementation and is responsible for performing the following:
     * <ul>
     *   <li>Retrieving the user input for the source money and target currency.</li>
     *   <li>Validating the input data to ensure it is complete and valid.</li>
     *   <li>Calculating the converted amount based on the current exchange rate.</li>
     *   <li>Loading and displaying a chart with historical exchange rate data.</li>
     *   <li>Displaying the result of the exchange calculation in the user interface.</li>
     * </ul>
     *
     * <p>If the input data is invalid (e.g., the source and target currencies are the same,
     * or the input amount is negative), an error message is displayed to the user in a dialog.</p>
     *
     * @since 1.0
     */
    @Override
    public void execute() {
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();

        if (!money.currency().equals(currency) && money.amount() >= 0) {
            ExchangeRate exchangeRate = exchangeRateLoader.load(money.currency(), currency);
            Money result = new Money( money.amount()*exchangeRate.rate(), currency);
            contentDisplay.showChart(chartLoader.load(money.currency(), currency), statisticLoader.loadStatistic(money.currency(), currency));
            moneyDisplay.show(money, result);
        } else {
            ErrorMessageDialogFactory.showErrorMessage(null,
                    "Please, insert valid data",
                    "Error");
        }
    }
}
