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

    private static String URL = "ws://145.24.222.24:8080";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("This application requires you to give one paramater.");
            System.out.println("Please use it like this:");
            System.err.println("java -jar noob-api-0.1.0.jar BANKCODE");
            System.exit(0);
        }

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
                    return;
                }
            });

            // send message to websocket
            String json = "[\"register\", \"slave\", \"" + args[0] + "\"]";
            clientEndPoint.sendMessage(json);

            // send message to websocket
            json = "[\"register\", \"master\", \"" + args[0] + "\"]";
            clientEndPoint2.sendMessage(json);

        } catch (Exception e) {
            System.err.println("kutleef");
        }

    }
}