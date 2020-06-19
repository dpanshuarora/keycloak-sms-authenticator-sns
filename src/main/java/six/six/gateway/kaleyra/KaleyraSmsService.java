package six.six.gateway.kaleyra;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import six.six.gateway.SMSService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class KaleyraSmsService implements SMSService {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private String baseUri = "";

    private String apiKey = "";

    private String sender = "";

    public boolean send(String phoneNumber, String message, String login, String pw) {
        HttpGet request = new HttpGet(String.format(baseUri, apiKey,
                    URLEncoder.encode(message, StandardCharsets.UTF_8),
                    phoneNumber, sender));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
