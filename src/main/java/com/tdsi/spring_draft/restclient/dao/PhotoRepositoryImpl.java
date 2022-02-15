package com.tdsi.spring_draft.restclient.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdsi.spring_draft.restclient.services.dto.PhotoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

//https://mkyong.com/java/java-11-httpclient-examples/
//https://mkyong.com/java/jackson-2-convert-java-object-to-from-json/
@Component
public class PhotoRepositoryImpl implements PhotoRepository {
    private Logger logger = LoggerFactory.getLogger(PhotoRepositoryImpl.class.getName());

    @Value("${restclient.link.jsonplaceholder}")
    private String jsonPlaceHolderApi;
    private static String json = "https://jsonplaceholder.typicode.com/photos";


    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();


    final
    ObjectMapper objectMapper;

    public PhotoRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private HttpRequest getRequest(String param) {
        if (!param.isEmpty()) {
            json += "/" + param;
        }
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(json))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
    }

    private CompletableFuture<HttpResponse<String>> getResponse(HttpRequest request) {
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    @Override
    public List<PhotoDTO> findAll() throws InterruptedException, ExecutionException, TimeoutException, JsonProcessingException {
        String result = getResponse(getRequest("")).thenApply(HttpResponse::body).get(1, TimeUnit.MINUTES);
        PhotoDTO[] list = objectMapper.readValue(result, new TypeReference<>() {
        });
        logger.info("List Photos : {} ", list);
        return Arrays.asList(list);
    }

    @Override
    public Optional<PhotoDTO> findById(long id) throws JsonProcessingException, InterruptedException, ExecutionException, TimeoutException {

        String result = getResponse(getRequest(String.valueOf(id))).thenApply(HttpResponse::body).get(2, TimeUnit.SECONDS);
        //PhotoDTO photoDTO = objectMapper.readValue(result, PhotoDTO.class);


        Jsonb jsonb = JsonbBuilder.create();
        PhotoDTO photo = jsonb.fromJson(result,PhotoDTO.class);
        logger.info("Photos : {} ", photo);
        return Optional.ofNullable(photo);
    }

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException, ExecutionException {

        PhotoDTO dto=new PhotoDTO();


    }

}
