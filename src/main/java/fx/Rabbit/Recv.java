package fx.Rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

public class Recv
{

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) throws Exception
    {
        //add comment

        //test

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.queueDeclare(QUEUE_NAME,false, false, false, null);

        System.out.println(" [*] Waiting for message. To exit press CTRL+C");

        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received: " + message );
        };

        channel.basicConsume( QUEUE_NAME, true, callback, consumerTag->{});


    }
}
