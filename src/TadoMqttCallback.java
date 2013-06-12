import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: johannes
 * Date: 5/29/13
 * Time: 10:55 AM
 * To change this template use File | Settings | File Templates.
 */

public class TadoMqttCallback implements MqttCallback {


    @Override
    public void connectionLost(Throwable throwable) {
        // Called when the connection to the server has been lost.
        // An application may choose to implement reconnection
        // logic at this point. This sample simply exits.
        System.out.println("Connection lost!");
        System.exit(1);
    }

    @Override
    public void messageArrived(MqttTopic mqttTopic, MqttMessage mqttMessage) throws Exception {
        // Called when a message arrives from the server that matches any
        // subscription made by the client
        String time = new Timestamp(System.currentTimeMillis()).toString();
        System.out.println("Time:\t" +time +
                "  Topic:\t" + mqttTopic +
                "  Message:\t" + new String(mqttMessage.getPayload()) +
                "  QoS:\t" + mqttMessage.getQos());
    }

    @Override
    public void deliveryComplete(MqttDeliveryToken mqttDeliveryToken) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
