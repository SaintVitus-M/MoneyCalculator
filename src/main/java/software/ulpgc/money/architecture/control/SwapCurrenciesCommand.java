package software.ulpgc.money.architecture.control;

import software.ulpgc.money.architecture.view.CurrencyDialog;
import software.ulpgc.money.architecture.view.MoneyDialog;
import software.ulpgc.money.swing.SwingMainFrame;

/**
 * The {@code SwapCurrenciesCommand} class implements the {@link Command} interface
 * and is responsible for swapping the selected currencies in the application's
 * user interface and then executing the exchange money command.
 *
 * <p>This command is typically used when the user selects the option to swap
 * the two currencies involved in a currency exchange. It updates the selected
 * currencies in both the {@link MoneyDialog} and {@link CurrencyDialog}, and then
 * triggers the "exchange money" command to perform the currency conversion calculation.
 *
 * <p>The command operates on the following components:
 * <ul>
 *   <li><b>{@link SwingMainFrame}:</b> The main application window.</li>
 *   <li><b>{@link MoneyDialog}:</b> Dialog that displays the money amount and allows the user to choose source currency.</li>
 *   <li><b>{@link CurrencyDialog}:</b> Dialog that allows the user to choose the target currency for exchange.</li>
 * </ul>
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public class SwapCurrenciesCommand implements Command{
    private final SwingMainFrame main;
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;

    /**
     * Constructs a new {@code SwapCurrenciesCommand} instance.
     *
     * @param main The main application frame.
     * @param moneyDialog The dialog for managing money and amount.
     * @param currencyDialog The dialog for managing currency selection.
     * @since       1.0
     */
    public SwapCurrenciesCommand(SwingMainFrame main, MoneyDialog moneyDialog, CurrencyDialog currencyDialog) {
        this.main = main;
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
    }

    /**
     * Executes the command to swap the selected currencies between the
     * {@link MoneyDialog} and {@link CurrencyDialog}, and triggers the
     * "exchange money" command to perform the currency exchange operation.
     * @since       1.0
     */
    @Override
    public void execute() {
        int i = moneyDialog.getSelectedIndex();
        moneyDialog.set(currencyDialog.getSelectedIndex());
        currencyDialog.set(i);
        main.getCommand("exchange money").execute();
    }
}
