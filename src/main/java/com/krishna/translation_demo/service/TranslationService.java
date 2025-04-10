package com.krishna.translation_demo.service;


import com.krishna.translation_demo.configuration.AzureProperties;
import com.krishna.translation_demo.dto.Translate;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TranslationService {

    @Autowired
    public AzureProperties azureProperties;

    @Value("${azure.url}")
    private String url;

    @Value("${azure.key}")
    private String key;

    @Value("${azure.location}")
    private String location;

    OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public String translate(Translate translate) throws IOException, JSONException {

        Request request = requestBuilder(translate);
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            System.out.println(" Exception while translating ");
            e.getStackTrace();
        }
        return null;

    }

    @NotNull
    public Request requestBuilder(Translate obj) throws JSONException {
        String from = obj.from;
        String to = obj.to;
        ArrayList<String> textListTOTranslate = obj.text;
        String urlWithParams = url + "&from=" + from + "&to=" + to;
        //Create an array to hold JSON objects for translation
        List<JSONObject> jsonList = textListTOTranslate.stream().map(this::createJSON).toList();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonList.toString());

        //RequestBody reqBody = RequestBody.Companion.create(textToTranslate, JSON);
        return new Request.Builder()
                .url(urlWithParams)
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();
    }

    private JSONObject createJSON(String textToTranslate) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Text", textToTranslate);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;

    }
}
