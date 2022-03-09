package connect;

import com.google.gson.Gson;
import json.NameInfo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Response {
    public int responseCode;
    private final String stringUrl;

    public Response(String stringUrl) {
        this.responseCode = -1;
        this.stringUrl = stringUrl;
    }

    public NameInfo getInfo() {
        try {
            URL url = new URL(stringUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            responseCode = conn.getResponseCode();

            StringBuilder jsonString = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                jsonString.append(scanner.nextLine());
            }

            scanner.close();

            Gson g = new Gson();
            return g.fromJson(String.valueOf(jsonString), NameInfo.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
