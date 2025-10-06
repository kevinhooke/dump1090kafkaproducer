package kh.dump1090.messageforwarder.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kh.dump1090.SBSMessage;
import kh.dump1090.messageforwarder.MessageForwarder;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class RestClientForwarder implements MessageForwarder {

    @Value("${restclient.uribase}")
    String uriBase;

    public void forwardMessage(SBSMessage msg) throws IOException {
        System.out.println("RESTForwarder forwarding message, uribase: " + uriBase);

        System.out.println("About to post body:");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(msg);
        System.out.println("JSON payload: " + json);

        HttpClient client = HttpClientBuilder.create()
                .disableAutomaticRetries()
                .build();

        RestClient restClient = RestClient.builder()
                .requestFactory(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory(client)))
                .build();

        ResponseEntity<Void> response = restClient.post()
                .uri(uriBase + "/api/sbsmessage/")
                .contentType(APPLICATION_JSON)
                .body(msg)
                .retrieve()
                .onStatus(httpStatusCode ->
                        httpStatusCode.value()== 400, (req, res) -> {
            System.out.printf(new String(res.getBody().readAllBytes()));
        })
                .toBodilessEntity();
        HttpStatusCode code = response.getStatusCode();

        System.out.println("POST response: " + response.getStatusCode().toString());
    }
}
