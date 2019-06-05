package socketapi;

import java.net.URI;
import javax.websocket.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main
 */
@SpringBootApplication
@ClientEndpoint
public class Main {

    private static Object waitLock = new Object();

    private static String URL = "ws://185.224.91.138:80";

    public static Session slave;
    public static Session master;

    @OnMessage
    public void onMessage(String message, Session slave) throws InterruptedException {
        System.out.println("Received msg: " + message);
        Main.slave.getAsyncRemote().sendText("[\"True\"]");
        Thread.sleep(500);
    }

    private static void waitForTerminationSignal() {
        synchronized (waitLock) {
            try {
                waitLock.wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("This application requires you to give one paramater.");
            System.out.println("Please use it like this:");
            System.err.println("java -jar noob-api-0.1.0.jar BANKCODE");
            System.exit(0);
        }

        SpringApplication.run(Main.class, args);

        WebSocketContainer container = null;

        try {
            container = ContainerProvider.getWebSocketContainer();
            slave = container.connectToServer(Main.class, URI.create(URL));
            slave.getAsyncRemote().sendText("[\"register\", \"slave\", \"" + args[0] + "\"]");
            Thread.sleep(500);
            master = container.connectToServer(Main.class, URI.create(URL));
            master.getAsyncRemote().sendText("[\"register\", \"master\", \"" + args[0] + "\"]");
            waitForTerminationSignal();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (slave != null) {
                try {
                    slave.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (master != null) {
                try {
                    master.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}