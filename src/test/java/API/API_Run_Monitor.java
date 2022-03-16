package API;



import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class API_Run_Monitor {

    private static final String POSTS_API_URL = "https://api.getpostman.com/monitors/16433126-1eca3748-5751-4760-aa90-b673726127f6/run";
    @Test
    public void main() throws IOException, InterruptedException, URISyntaxException {
        var uri = new URI(POSTS_API_URL);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString("1"))
                .header("X-Api-Key", "PMAK-62305190c4397c209ef13c98-ef5a754f9d0e597cac81d77a41b1944bd8")
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response);
    }
}
