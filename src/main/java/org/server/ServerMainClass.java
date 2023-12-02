package org.server;

import java.io.IOException;

public class ServerMainClass {

    public static void main(String[] args) throws IOException {
        BasicHttpServerExample basicHttpServerExample = new BasicHttpServerExample();
        basicHttpServerExample.startServer();
    }
}
