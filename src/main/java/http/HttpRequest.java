package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;
    private String url;
    private String version;
    private Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader br) {
        try {
            String line = br.readLine();
            parseRequestLine(line);
            do {
                line = br.readLine();
                parseHeaderLine(line);
            } while (!"".equals(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseRequestLine(String line) {
        if (line == null || line.isEmpty()) return;

        String[] parts = line.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            System.out.println(parts[i]);
        }

        method = parts[0];
        url = parts[1];
        version = parts[2];
    }

    private void parseHeaderLine(String line) {
        if (line == null || line.isEmpty()) return;

        String[] parts = line.split(":\\s+");

        headers.put(parts[0], parts[1]);
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }
}
