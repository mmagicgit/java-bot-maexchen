package main;

import udphelper.MessageListener;
import udphelper.MessageSender;
import udphelper.UdpCommunicator;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String serverHost = "172.17.48.123";
		int serverPort = 9000;
		String clientName = "TheShadow";

		UdpCommunicator communicator = new UdpCommunicator(serverHost, serverPort);
		MessageListener bot = createBot(clientName, communicator.getMessageSender());
        addShutdownHook(communicator);
		communicator.addMessageListener(bot);
		communicator.listenForMessages();
	}

    private static void addShutdownHook(final UdpCommunicator communicator) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                communicator.stop();
            }
		});
    }

	private static MessageListener createBot(String clientName, MessageSender messageSender) {
		return new SimpleBot(clientName, messageSender);
	}

}
