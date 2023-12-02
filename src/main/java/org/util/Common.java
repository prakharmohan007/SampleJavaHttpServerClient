package org.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Common {
    public static final Gson gson = new GsonBuilder().create();
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
