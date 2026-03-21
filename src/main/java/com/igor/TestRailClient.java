package com.igor;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class TestRailClient {

    public void enviarResultado(int caseId, int statusId) {
        try {
            String baseUrl = "https://igorrodrigo.testrail.io";
            String username = "igor.rodriigo@yahoo.com";
            String apiKey = "qIPD0r50bCvyFQVD5LRq-SBlwDawIbta56ygEmPIE";

            URL url = new URL(baseUrl + "/index.php?/api/v2/add_result_for_case/14/" + caseId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String auth = username + ":" + apiKey;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + encodedAuth);
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setDoOutput(true);

            String json = "{ \"status_id\": " + statusId + " }";

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("Resposta TestRail: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}