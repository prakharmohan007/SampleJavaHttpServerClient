package org.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;
import org.model.User;
import org.util.Common;
import org.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Handlers {
    private static final Map<Integer, User> cacheData = new HashMap<Integer, User>();

    public static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<h1>Server start success if you see this message</h1>" + "<h1>Port: " + Constants.port + "</h1>";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static class PostUserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            parseRequest(exchange);

            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            User user = Common.objectMapper.readValue(body, User.class);
            System.out.println(user.toString());
            cacheData.put(user.getUserId(), user);

            exchange.sendResponseHeaders(200, 2);
            OutputStream os = exchange.getResponseBody();
            os.write("OK".getBytes());
            os.close();
        }
    }
    public static class GetUserHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            parseRequest(exchange);
            int userId = getUserId(exchange);
            System.out.println(userId);
            User user = cacheData.get(userId);
            String userString = Common.gson.toJson(user);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, userString.length());

            OutputStream os = exchange.getResponseBody();
            os.write(userString.getBytes());
            os.close();
        }

        private int getUserId(HttpExchange exchange) {
            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery();
            String userId = query.split("=")[1];
            return Integer.parseInt(userId);
        }

    }

    static void parseRequest(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();

        System.out.println("__headers __");
        Headers requestHeaders = exchange.getRequestHeaders();
        requestHeaders.entrySet().forEach(System.out::println);

        System.out.println("-- principle --");
        HttpPrincipal principal = exchange.getPrincipal();
        System.out.println(principal);

        System.out.println("-- HTTP method --");
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);

        System.out.println("-- query --");
        String query = requestURI.getQuery();
        System.out.println(query);
    }
}
