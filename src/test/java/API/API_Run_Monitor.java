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

    private static final String POSTS_API_URL = "https://api.getpostman.com/monitors/1eca6c65-dc7b-4fb0-85d7-5cfe074a5889/run";
    //12959542-21f19bdf-b8d1-48b6-afcb-78ad416e4603
    //1eca6c65-dc7b-4fb0-85d7-5cfe074a5889
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
