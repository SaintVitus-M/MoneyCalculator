package software.ulpgc.money.architecture.io;

/**
 * This interface defines the contract for a service that loads JSON data from a given URL.
 * Implementations of this interface should provide the logic to fetch the content of the URL
 * and return it as a JSON string.
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0.1
 */
public interface APIService {
    /**
     * Loads JSON data from the specified URL.
     *
     * <p>
     * This method should handle the process of sending an HTTP request to the provided URL,
     * receiving the response, and returning the response content as a JSON string.
     * </p>
     *
     * @param url the URL from which to fetch the JSON data
     * @return a JSON string representing the data retrieved from the specified URL
     * @since       1.0.1
     */
    String loadJsonWith(String url);
}
