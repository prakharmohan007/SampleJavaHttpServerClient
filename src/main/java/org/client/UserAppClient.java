package org.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.model.User;
import org.util.Common;

import java.net.URI;

public class UserAppClient {
    private final HttpClient client = HttpClientBuilder.create().build();

    User sampleGetAPIRequest(int userId) throws Exception {
        HttpGet httpGet = new HttpGet("http://localhost:8600/getUser");
        URI uri = new URIBuilder(httpGet.getURI()).addParameter("userId", String.valueOf(userId))
                .build();
        httpGet.setURI(uri);

        System.out.println("Sending HTTP GET for User: " + userId);
        HttpResponse response = client.execute(httpGet);
        System.out.println("Received HTTP GET for User: " + userId);

        HttpEntity entity = response.getEntity();
        String userString = EntityUtils.toString(entity);
        httpGet.releaseConnection();
        return Common.objectMapper.readValue(userString, User.class);

    }

    void samplePostAPIRequest(User user) throws Exception {
        String userString = Common.gson.toJson(user);

        HttpPost postRequest = new HttpPost("http://localhost:8600/postUser");
        postRequest.setHeader("Content-type", "application/json");
        StringEntity entity = new StringEntity(userString);

        postRequest.setEntity(entity);
        System.out.println("Sending HTTP POST for User: " + user.getUserId());
        HttpResponse response = client.execute(postRequest);
        System.out.println("Received HTTP POST for User: " + user.getUserId());
        postRequest.releaseConnection();

    }
}
