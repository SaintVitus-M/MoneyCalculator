package software.ulpgc.money.frankfurter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import software.ulpgc.money.architecture.io.APIService;
import software.ulpgc.money.architecture.io.StatisticLoader;
import software.ulpgc.money.architecture.model.Currency;
import software.ulpgc.money.architecture.model.ExchangeRateTimeSeries;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code FrankfurterTimeSeriesLoader} class implements the {@link StatisticLoader} interface
 * and is responsible for loading exchange rate time series data from the Frankfurter API.
 * It fetches exchange rate data for a specific pair of currencies over the last year.
 *
 * <p>This class provides functionality to:
 * <ul>
 *   <li>Load exchange rate time series data for a given currency pair.</li>
 *   <li>Deserialize the returned JSON data into a structured {@link ExchangeRateTimeSeries} object.</li>
 * </ul>
 *
 * <p>The class retrieves data from the Frankfurter API, which provides historical exchange rate data
 * for a base currency against a set of other currencies. The time series is created by extracting
 * exchange rates for each day of the previous year.
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public class FrankfurterTimeSeriesLoader implements StatisticLoader {
    private final APIService apiDeserializer;

    public FrankfurterTimeSeriesLoader(APIService apiDeserializer) {
        this.apiDeserializer = apiDeserializer;
    }

    /**
     * Loads the exchange rate time series data for the specified currency pair
     * from the Frankfurter API for the previous year.
     *
     * @param from The base currency from which to convert.
     * @param to The target currency to which the base currency is converted.
     * @return An {@link ExchangeRateTimeSeries} containing the exchange rates for each day
     *         in the past year.
     * @since       1.0
     */
    @Override
    public ExchangeRateTimeSeries loadStatistic(Currency from, Currency to) {
        String startDay = LocalDate.ofYearDay(LocalDate.now().getYear() - 1, LocalDate.now().getDayOfMonth()).toString();
        String url = "https://api.frankfurter.dev/v1/" + startDay + "..?symbols=" + to.code() + "&base=" + from.code();
        return toTimeSeries(from, to, apiDeserializer.loadJsonWith(url));
    }

    /**
     * Converts the raw JSON data into an {@link ExchangeRateTimeSeries} object.
     *
     * @param base The base currency.
     * @param other The target currency.
     * @param json The raw JSON string containing the exchange rates.
     * @return An {@link ExchangeRateTimeSeries} object containing the exchange rates
     *         for each day over the previous year.
     * @since       1.0
     */
    private ExchangeRateTimeSeries toTimeSeries(Currency base, Currency other, String json) {
        JsonObject gson = new Gson().fromJson(json, JsonObject.class);
        Map<String, Double> rates = new HashMap<>();
        gson = gson.getAsJsonObject("rates");
        for (Map.Entry<String, JsonElement> entry : gson.entrySet()) {
            String day = entry.getKey();
            JsonObject value = entry.getValue().getAsJsonObject();
            double rateValue = value.get(other.code()).getAsDouble();
            rates.put(day, rateValue);
        }
        return new ExchangeRateTimeSeries(base, other, rates);
    }
}
