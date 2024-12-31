package software.ulpgc.money.frankfurter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.ulpgc.money.architecture.io.ExchangeRateLoader;
import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.ExchangeRate;
import software.ulpgc.money.architecture.io.APIDeserializer;

import java.io.IOException;

/**
 * {@code FrankfurterExchangeRateLoader} is an implementation of the {@link ExchangeRateLoader} interface
 * that retrieves the latest exchange rate between two currencies from the Frankfurter API.
 * This class provides the method {@link #load(Currency, Currency)} to load the exchange rate
 * by making an HTTP request to the Frankfurter API and parsing the response.
 *
 * <p>The {@code load} method constructs a request URL to the Frankfurter API, fetches the exchange rate
 * data in JSON format, and then converts the response into an {@link ExchangeRate} object.
 * In case of failure (e.g., network error or malformed response), the method returns {@code null}.
 *
 * @author      Vít Mikula
 * @version     %I%, %G%
 * @since       1.0
 */
public class FrankfurterExchangeRateLoader implements ExchangeRateLoader {

    /**
     * Loads the latest exchange rate between the given currencies using the Frankfurter API.
     * Constructs the API request URL and deserializes the JSON response into an {@link ExchangeRate} object.
     *
     * @param from The base currency.
     * @param to The target currency.
     * @return An {@link ExchangeRate} object containing the exchange rate, or {@code null} if an error occurs.
     * @since 1.0
     */
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        String url = "https://api.frankfurter.dev/v1/latest?symbols=" + to.code() + "&base=" + from.code();
        try {
            return toExchangeRate(from, to, APIDeserializer.loadJsonWith(url));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Converts the raw JSON response into an {@link ExchangeRate} object.
     *
     * @param from The base currency.
     * @param to The target currency.
     * @param json The JSON response string.
     * @return An {@link ExchangeRate} object containing the rate information.
     * @since 1.0
     */
    private ExchangeRate toExchangeRate(Currency from, Currency to, String json) {
        JsonObject gson = new Gson().fromJson(json, JsonObject.class);
        return new ExchangeRate(from, to, getDateFrom(gson), getRateFrom(gson, to));
    }

    /**
     * Extracts the exchange rate for the target currency from the JSON response.
     *
     * @param gson The parsed JSON object.
     * @param to The target currency.
     * @return The exchange rate for the target currency.
     * @since 1.0
     */
    private double getRateFrom(JsonObject gson, Currency to) {
        return gson.get("rates").getAsJsonObject().get(to.code()).getAsDouble();
    }

    /**
     * Extracts the date from the JSON response.
     *
     * @param gson The parsed JSON object.
     * @return The date of the exchange rate in string format.
     * @since 1.0
     */
    private static String getDateFrom(JsonObject gson) {
        return gson.get("date").toString();
    }
}
