package com.nearsoft;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import java.util.Map;


/**
 * Created by xloeza on 10/7/16.
 */
public class GenericApiCall {
    private String baseUrl;
    private String userName;
    private String password;
    public GenericApiCall(String baseUrl, String userName, String password){
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
    }

    public String Request(HttpVerbs method, String endPoint, Map<String, String> headers, Map<String, String> parameters, Map<String, String> queryParameters, String body){
        Client client = Client.create();
        String url = baseUrl + endPoint;


        MultivaluedMap<String, String> params = GetParameters(parameters, queryParameters);

        WebResource webResource = client.resource(url)
                .queryParams(params);;


        ClientResponse response = null;
        switch (method) {
            case GET:
                response = webResource.accept("application/json")
                           .get(ClientResponse.class);
                break;
            case POST:
                response = webResource.accept("application/json").post(ClientResponse.class);
                break;
            case PUT:
                response = webResource.accept("application/json").put(ClientResponse.class);
                break;
            case DELETE:
                response = webResource.accept("application/json").delete(ClientResponse.class);
                break;
            case HEAD:
                response = webResource.accept("application/json").head();
                break;
            case PATCH:
                response = webResource.accept("application/json").method("PATCH", ClientResponse.class);
        }



        if (response!= null & response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String json = response.getEntity(String.class);
        return json;
    }




    private MultivaluedMap<String, String> GetParameters(Map<String, String> parameters, Map<String, String> queryParameters) {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        if(parameters != null && !parameters.isEmpty()) {
            for ( Map.Entry<String, String> parameter :parameters.entrySet()){
                  params.add(parameter.getKey(), parameter.getValue());
            }
        }
        if(queryParameters != null && !queryParameters.isEmpty())
        {
            for (Map.Entry<String, String> qParam: queryParameters.entrySet()){
                params.add(qParam.getKey(), qParam.getValue());
            }
        }
        return params;
    }


    public enum HttpVerbs{
        GET,
        POST,
        PUT,
        PATCH,
        DELETE,
        HEAD
    }

}
