package API;


import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API_Run_Monitor_Lambda {

    private static final String POSTS_API_URL = "https://api.lambdatest.com/automation/api/v1/sessions?limit=35";
    @Test
    public void main() throws IOException, InterruptedException, URISyntaxException {
        var uri = new URI(POSTS_API_URL);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString("1"))
                .header("Authorization", "YXJ0Y3VuYW1pOkdFZ3c5cGo1MUNyODlHMjVtVHBrZWFpSHVWUlVMbDh4OWdBbkpBY1FDOGkzR0drbXFk")
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response);
    }
}
