package ca.jrvs.apps.twitter;

import sun.net.www.http.HttpClient;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;


public class TwitterApiTest {
    public static String CONSUMER_KEY = System.getenv("consumerKey");
    public static String CONSUMER_SECRET = System.getenv("consumerSecret");
    public static String ACCESS_TOKEN = System.getenv("accessToken");
    public static String TOKEN_SECRET = System.getenv("tokenSecret");

    public static void main(String[] args) throws Exception{

        //setup oauth
       OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
       consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

       // create an HTTP GET Request
        String status = "today us a good day";
        PercentEscaper percentEscaper = new PercentEscaper(safeChars: "", plusForSpace: false);
        HttpPost request = new HttpPost(url: "https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(status));

        // sign the request
        consumer.sign(request);

        System.out.println("Http Request Headers");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        // send the request
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}