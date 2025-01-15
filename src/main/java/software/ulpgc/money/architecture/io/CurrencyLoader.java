package software.ulpgc.money.architecture.io;

import software.ulpgc.money.architecture.model.Currency;

import java.util.List;

/**
 * The {@code CurrencyLoader} interface defines the contract for loading a list of
 * {@link Currency} objects. Implementations of this interface are responsible for
 * fetching or loading currency data from a data source, such as an external API,
 * a database, or a local file.
 *
 * <p>The {@code load()} method retrieves a list of currencies, which is used
 * by other components of the application for operations such as currency conversion,
 * display, or analysis.
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public interface CurrencyLoader {
    /**
     * Loads a list of {@link Currency} objects.
     *
     * <p>This method retrieves currency data from a data source. The data source
     * may vary depending on the implementation, such as an API call or a local file
     * read. Implementations of this method should ensure that the list is populated
     * with valid {@code Currency} objects.
     *
     * @return a list of {@link Currency} objects, may be empty if no data is found
     * @since       1.0
     */
    List<Currency> load();
}
