package software.ulpgc.money.architecture.io;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * The {@code APIDeserializer} class provides functionality to load and deserialize JSON data
 * from a remote API endpoint via HTTP GET requests. It offers a method to fetch the raw JSON
 * response from a given URL and returns it as a {@link String}.
 *
 * <p>The class handles HTTP connections and manages errors related to network responses,
 * such as non-200 HTTP status codes.</p>
 *
 * @author      Vít Mikula
 * @version     1.0, 31/12/2024
 * @since       1.0
 */
public class APIDeserializer {

    /**
     * Loads JSON data from the specified URL by making an HTTP GET request.
     *
     * <p>This method opens a connection to the given URL, sends a GET request, and returns
     * the JSON response as a {@link String}. If an error occurs during the connection or
     * data retrieval process, an exception is thrown.</p>
     *
     * @param url The URL of the API endpoint to retrieve the JSON data from.
     * @return A {@link String} containing the JSON response from the API.
     * @throws IOException If an I/O error occurs while reading the response.
     * @throws RuntimeException If the HTTP response code is not 200 (OK), or if other errors occur.
     * @since       1.0
     */
    public static String loadJsonWith(String url) throws IOException {
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
    private static @NotNull BufferedReader getBufferedReader(String url) throws URISyntaxException, IOException {
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
