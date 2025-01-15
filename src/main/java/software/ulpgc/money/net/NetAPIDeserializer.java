package software.ulpgc.money.net;

import software.ulpgc.money.architecture.io.APIService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * A utility class that implements the {@link APIService} interface to fetch and deserialize JSON data from a given URL.
 * This class uses the {@link HttpURLConnection} to establish an HTTP connection and retrieve JSON responses.
 *
 * <p>The primary method, {@code loadJsonWith(String url)}, accepts a URL as input and returns the JSON response as a string.
 * If the request fails or encounters an error, it throws a {@link RuntimeException}.
 *
 * <p>Note: This class handles GET requests only and expects the response to be in JSON format.
 * It throws a {@link RuntimeException} if the HTTP response code is not 200 or if an exception occurs during processing.
 *
 * @author      Vít Mikula
 * @version     1.0.1, 15/01/2025
 * @since       1.0.1
 */
public class NetAPIDeserializer implements APIService {

    /**
     * Fetches the JSON response from the given URL and returns it as a string.
     *
     * @param url the URL to fetch the JSON from.
     * @return a string containing the JSON response from the provided URL.
     * @throws RuntimeException if an exception occurs during the connection or if the response code is not 200.
     */
    @Override
    public String loadJsonWith(String url) {
        try {
            BufferedReader reader = getBufferedReader(url);
            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
            reader.close();
            return jsonResponse.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens an HTTP connection to the specified URL and returns a {@link BufferedReader}
     * to read the response's input stream.
     *
     * <p>This method is responsible for setting up the connection, sending the GET request,
     * and handling any issues related to the HTTP response, such as non-200 HTTP status codes.</p>
     *
     * @param url The URL of the API endpoint.
     * @return A {@link BufferedReader} to read the input stream of the HTTP response.
     * @throws URISyntaxException If the provided URL is not valid.
     * @throws IOException If an I/O error occurs while opening the connection or reading the response.
     * @throws RuntimeException If the HTTP response code is not 200 (OK).
     * @since       1.0
     */
    private static BufferedReader getBufferedReader(String url) throws URISyntaxException, IOException {
        URI uri = new URI(url);
        URL u = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int response = connection.getResponseCode();
        if(response != 200){
            throw new RuntimeException("Error: HTTP " + response);
        }
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }
}
