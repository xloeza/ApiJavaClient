package com.nearsoft;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import org.json.*;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException {
        ApiClient client = new ApiClient();

        JavaInfo info = client.GetJavaInfo("lVJ7JgO9wJOaViUAHiuZWwmb61ebXq8RD2pxgL46");

        System.out.println(info.explanation);


        /*
        try {

            Client client = Client.create();


            MultivaluedMap<String, String> params = new MultivaluedMapImpl();
            params.add("api_key", "lVJ7JgO9wJOaViUAHiuZWwmb61ebXq8RD2pxgL46");
            WebResource webResource = client
                    .resource("https://api.nasa.gov/planetary/apod")
                    .queryParams(params);

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            JSONObject obj = new JSONObject(output);
            JavaInfo info  = ApiInstance.map(obj, JavaInfo.class);


            System.out.println(info.explanation);

            System.in.read();

        } catch (Exception e) {

            e.printStackTrace();
        }*/
    }
}
