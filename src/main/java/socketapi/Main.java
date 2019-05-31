package socketapi;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main
 */
@SpringBootApplication
public class Main {

    static WebsocketClientEndpoint clientEndPoint;
    static WebsocketClientEndpoint clientEndPoint2;

    private static String URL = "ws://185.224.91.138:80";

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        try {
            clientEndPoint = new WebsocketClientEndpoint(
                    new URI(URL));

            clientEndPoint2 = new WebsocketClientEndpoint(
                    new URI(URL));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            String json = "[\"register\", \"slave\", \"robin\"]";
            clientEndPoint.sendMessage(json);

            // send message to websocket
            json = "[\"register\", \"master\", \"robin\"]";
            clientEndPoint2.sendMessage(json);

        } catch (Exception e) {
            System.err.println("kutleef");
        }

    }
}