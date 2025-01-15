package software.ulpgc.money.frankfurter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.money.architecture.io.APIService;
import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.io.CurrencyLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@code FrankfurterCurrencyLoader} is an implementation of the {@link CurrencyLoader} interface.
 * This class is responsible for loading a list of {@link Currency} objects by fetching
 * currency data from the Frankfurter API.
 *
 * <p>The {@link #load()} method sends a request to the Frankfurter API, retrieves a JSON
 * response containing currency symbols, and converts it into a list of {@link Currency} objects.
 * If an error occurs during the API request or data conversion, an empty list is returned.
 *
 * <p>Internally, the class uses the Gson library to parse the JSON response and map it
 * to a list of {@link Currency} instances, where each symbol is associated with its corresponding
 * name or value.
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0
 */
public class FrankfurterCurrencyLoader implements CurrencyLoader {
    private final APIService apiDeserializer;

    public FrankfurterCurrencyLoader(APIService apiDeserializer) {
        this.apiDeserializer = apiDeserializer;
    }

    /**
     * Loads the list of currencies from the Frankfurter API.
     *
     * <p>This method retrieves the currency data from the Frankfurter API, deserializes the JSON response,
     * and returns a list of {@link Currency} objects. If an error occurs (e.g., API failure or JSON parsing),
     * an empty list is returned.</p>
     *
     * @return A list of {@link Currency} objects representing the available currencies, or an empty list in case of an error.
     * @since       1.0
     */
    @Override
    public List<Currency> load() {
        String url = "https://api.frankfurter.dev/v1/currencies";
        return toList(apiDeserializer.loadJsonWith(url));
    }

    /**
     * Converts a JSON string into a list of {@link Currency} objects.
     *
     * <p>This method takes the raw JSON string, parses it using the Gson library, and creates a list of
     * {@link Currency} objects based on the symbols and their corresponding values found in the JSON response.</p>
     *
     * @param json The raw JSON string representing currency symbols and values.
     * @return A list of {@link Currency} objects parsed from the JSON string.
     * @since       1.0
     */
    private List<Currency> toList(String json) {
        List<Currency> list = new ArrayList<>();
        Map<String, JsonElement> symbols = new Gson().fromJson(json, JsonObject.class).getAsJsonObject().asMap();
        for (String symbol : symbols.keySet())
            list.add(new Currency(symbol, symbols.get(symbol).getAsString()));
        return list;
    }
}
