package org.server;

import com.sun.net.httpserver.HttpServer;
import org.util.Constants;

import java.io.Console;
import java.io.IOException;
import java.net.InetSocketAddress;

public class BasicHttpServerExample {
    private final HttpServer server;

    public BasicHttpServerExample() throws IOException {
        server = HttpServer.create(new InetSocketAddress(Constants.port), 0);
    }

    public void startServer() throws IOException {
        server.createContext("/", new Handlers.RootHandler());
        server.createContext("/getUser", new Handlers.GetUserHandler());
        server.createContext("/postUser", new Handlers.PostUserHandler());
        server.start();
    }

    public void closeServer() throws IOException {
        server.stop(1);
    }

}