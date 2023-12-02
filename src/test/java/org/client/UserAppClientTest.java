package org.client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.model.User;
import org.server.BasicHttpServerExample;

import java.io.IOException;

import static org.junit.Assert.*;

public class UserAppClientTest {
    private final UserAppClient userAppClient;
    private static BasicHttpServerExample basicHttpServerExample;

    public UserAppClientTest() {
        userAppClient = new UserAppClient();
    }

    @BeforeClass
    public static void startServer() throws IOException {
        basicHttpServerExample = new BasicHttpServerExample();
        basicHttpServerExample.startServer();
    }

    @AfterClass
    public static void closeServer() throws IOException {
        basicHttpServerExample.closeServer();
    }

    @Test
    public void postAndGetUsers_happyCase() throws Exception {
        User user1 = new User(1, "Prakhar Mohan", 30, false);
        User user2 = new User(2, "Garvita Tiwari", 29, true);

        userAppClient.samplePostAPIRequest(user1);
        userAppClient.samplePostAPIRequest(user2);

        User responseUser1 = userAppClient.sampleGetAPIRequest(1);
        User responseUser2 = userAppClient.sampleGetAPIRequest(2);

        assertTrue(user1.equals(responseUser1));
        assertTrue(user2.equals(responseUser2));
    }
}
