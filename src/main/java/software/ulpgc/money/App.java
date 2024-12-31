package software.ulpgc.money;

import org.jetbrains.annotations.NotNull;
import software.ulpgc.money.architecture.control.Command;
import software.ulpgc.money.architecture.control.ExchangeMoneyCommand;
import software.ulpgc.money.architecture.control.ShowReadMeCommand;
import software.ulpgc.money.architecture.control.SwapCurrenciesCommand;
import software.ulpgc.money.architecture.io.TimeSeriesChartLoader;
import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.frankfurter.FrankfurterCurrencyLoader;
import software.ulpgc.money.frankfurter.FrankfurterExchangeRateLoader;
import software.ulpgc.money.swing.SwingMainFrame;

import java.util.List;

/**
 * The {@code App} class serves as the entry point for the application.
 * It initializes the main graphical user interface (GUI) using {@link SwingMainFrame},
 * loads supported currencies, and sets up commands for the application's core features:
 * <ul>
 *   <li>Exchanging money between different currencies.</li>
 *   <li>Swapping the selected currencies in the user interface.</li>
 *   <li>Displaying informational content, such as a "Read Me" section.</li>
 * </ul>
 *
 * <p>The class follows a command-based design pattern to allow decoupling of user actions
 * (such as button presses) from the logic that performs these actions. Each command is
 * associated with a specific feature of the application.</p>
 *
 * @see SwingMainFrame
 * @see Command
 * @see Currency
 * @see FrankfurterCurrencyLoader
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
public class App {
    public static void main() {
        SwingMainFrame main = new SwingMainFrame();
        List<Currency> currencies = new FrankfurterCurrencyLoader().load();
        main.putCommand("exchange money", initExchangeCommand(main, currencies));
        main.putCommand("swap", initSwapCommand(main));
        main.putCommand("show info", initShowInfoCommand(main));
        main.getCommand("show info").execute();
        main.setVisible(true);

    }

    /**
     * Initializes the "show info" command.
     * <p>
     * This command is responsible for displaying informational content in the
     * application's content display area.
     *
     * @param main the main application frame used to access the content display.
     * @return an instance of {@link ShowReadMeCommand} for showing informational content.
     * @since       1.0
     */
    private static Command initShowInfoCommand(SwingMainFrame main) {
        return new ShowReadMeCommand(main.contentDisplay());
    }

    /**
     * Initializes the "swap" command.
     * <p>
     * This command allows the user to swap the selected source and target currencies
     * in the application's interface.
     *
     * @param main the main application frame used to access dialogs and controls.
     * @return an instance of {@link SwapCurrenciesCommand} for swapping currencies.
     * @since       1.0
     */
    private static Command initSwapCommand(SwingMainFrame main) {
        return new SwapCurrenciesCommand(
                main,
                main.moneyDialog(),
                main.currencyDialog()
        );
    }

    /**
     * Initializes the "exchange money" command.
     * <p>
     * This command performs the currency conversion by taking the user-inputted
     * amount, source currency, and target currency, then displaying the result
     * and historical exchange rate data.
     *
     * @param main       the main application frame used to access dialogs and displays.
     * @param currencies the list of available currencies to define dialogs.
     * @return an instance of {@link ExchangeMoneyCommand} for handling currency exchange.
     * @since       1.0
     */
    private static @NotNull Command initExchangeCommand(SwingMainFrame main, List<Currency> currencies) {
        return new ExchangeMoneyCommand(
                main.moneyDialog().define(currencies),
                main.currencyDialog().define(currencies),
                new FrankfurterExchangeRateLoader(),
                main.moneyDisplay(),
                main.contentDisplay(),
                new TimeSeriesChartLoader()
        );
    }
}