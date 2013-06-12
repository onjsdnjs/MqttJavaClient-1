import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: johannes
 * Date: 5/29/13
 * Time: 7:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class TadoMqttJavaClient {
    public static void main( String[] args )
    {
        System.out.println("TadoMqttJavaClient started!");


        System.out.println("Testing sending one message!");
        try {
            MqttClient client = new MqttClient("tcp://localhost:1883", "SampleClient");
            client.connect();
            MqttMessage message = new MqttMessage("Hello world".getBytes());
            message.setQos(0);
            client.getTopic("device/event").publish(message);
            client.disconnect();
        }
        catch (Exception ex) {
            System.err.println("There was an error!");
            System.err.println(ex);
        }


        System.out.println("Done testing sending one message!");


        System.out.println("Testing listening to messages!");
        try {
            MqttClient client = new MqttClient("tcp://localhost:1883", "SampleClient");
            client.connect();
            String topicName = "device/command";
            int qos = 0;
            System.out.println("Subscribing to topic \""+topicName+"\" qos "+qos);
            TadoMqttCallback tadoMqttCallback = new TadoMqttCallback();
            client.setCallback(tadoMqttCallback);
            client.subscribe(topicName, 0);
            // Subscribe to the requested topic
            // The QoS specified is the maximum level that messages will be sent to the client at.
            // For instance if QoS 1 is specified, any messages originally published at QoS 2 will
            // be downgraded to 1 when delivering to the client but messages published at 1 and 0
            // will be received at the same level they were published at.

            // Continue waiting for messages until the Enter is pressed
            System.out.println("Press <Enter> to exit");
            try {
                System.in.read();
            } catch (IOException e) {
                //If we can't read we'll just exit
            }

            // Disconnect the client from the server
            client.disconnect();

        }
        catch (Exception ex) {
            System.err.println(ex);
        }







//        MqttClient client = null;
//        try {
//            // Create a client to communicate with a broker at the specified address
//            client = new MqttClient("tcp://localhost:1883", "SampleClient");
//            // Connect to the broker
//            client.connect();
//        } catch (MqttException ex) {
//            System.err.println("Could not connect");
//        }
//
//        if ((client != null) && client.isConnected()) {
//            MqttTopic topic = client.getTopic("tado/deviceevent");
//            MqttDeliveryToken token = null;
//            // Create message and set quality of service to deliver the message once
//            MqttMessage message = new MqttMessage(("Hello MQTT world").getBytes());
//            message.setQos(2);
//
//            try {
//                // Give the message to the client for publishing.  For QoS 2,
//                // this will involve multiple network calls, which will happen
//                // asynchronously after this method has returned.
//                token = topic.publish(message);
//            }
//            catch (MqttException ex) {
//                // Client has not accepted the message due to a failure
//                // Depending on the exception's reason code, we could always retry
//                System.err.println("Failed to send message");
//            }
//
//            if (token != null) {
//                boolean keepTrying = true;
//                do {
//                    try {
//                        // Wait for the message delivery to complete
//                        token.waitForCompletion();
//                        System.out.println("Message delivery complete");
//                    }
//                    catch (MqttException deliveryException) {
//                        int reasonCode = deliveryException.getReasonCode();
//                        // TODO: Retry the message, or decide to stop trying
//                        System.err.println("Message delivery failed");
//                        if (client.isConnected() == false) {
//                            try {
//                                // Re-connect to the server
//                                client.connect();
//                            }
//                            catch (MqttException connectException) {
//                                // Can't reconnect, so give up.  If and when the
//                                // client does reconnect, the message delivery
//                                // will automatically continue
//                                keepTrying = false;
//                            }
//                        }
//                    }
//                } while (!token.isComplete() && keepTrying);
//            }
//        }



        System.out.println("TadoMqttJavaClient finished!");
    }

}
