package com.nearsoft;

import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by xloeza on 10/8/16.
 */
public class ApiClient {
    public JavaInfo GetJavaInfo(String api_key) throws InstantiationException, IllegalAccessException {
        String baseUrl="https://api.nasa.gov";
        String endPoint="/planetary/apod";
        GenericApiCall.HttpVerbs method = GenericApiCall.HttpVerbs.GET;
        GenericApiCall apiCall = new GenericApiCall(baseUrl, "", "");
        String body = "";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> parameters = new HashMap<>();
        Map<String, String> queryParameters = new HashMap<>();

        parameters.put("api_key", api_key);

        String json = apiCall.Request(method, endPoint, headers, parameters, queryParameters, body);
        JSONObject jsonObject = new JSONObject(json);
        JavaInfo res  = ApiInstance.map(jsonObject, JavaInfo.class);
        return res;
    }
}
