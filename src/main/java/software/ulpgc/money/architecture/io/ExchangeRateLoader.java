package software.ulpgc.money.architecture.io;

import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.ExchangeRate;

/**
 * The {@code ExchangeRateLoader} interface is responsible for loading the exchange rate
 * between two specified currencies. Implementations of this interface are expected to
 * provide a mechanism for retrieving the current exchange rate for a given currency pair.
 *
 * <p>This interface defines a method that accepts two {@link Currency} objects, representing
 * the source and target currencies, and returns an {@link ExchangeRate} object that contains
 * the exchange rate between them.</p>
 *
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
public interface ExchangeRateLoader {
    /**
     * Loads the exchange rate between two currencies.
     *
     * @param from The source currency.
     * @param to The target currency.
     * @return An {@link ExchangeRate} object representing the exchange rate between the given currencies.
     * @since       1.0
     */
    ExchangeRate load(Currency from, Currency to);
}
