package de.qwatrum.messagemate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GPTClient {

    private static final String API_KEY = ""; // Enter here your private OpenAI API Key.
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";


    private final OkHttpClient httpClient = new OkHttpClient();

    public String getResponse(String message) throws IOException, JSONException {
        JSONObject userMessageObject = new JSONObject();
        JSONArray messages = new JSONArray();
        userMessageObject.put("role", "user");
        userMessageObject.put("content", message);

        messages.put(userMessageObject);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-4o-mini");
        requestBody.put("messages", messages);

        RequestBody body = RequestBody.create(
                requestBody.toString(),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected error: " + response);
            }

            assert response.body() != null;
            String responseBody = response.body().string();
            JSONObject responseJson = new JSONObject(responseBody);

            return responseJson
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
