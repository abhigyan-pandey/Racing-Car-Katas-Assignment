package tddmicroexercises.telemetrysystem.services;

import tddmicroexercises.telemetrysystem.interfaces.Client;

import java.util.Random;

public class ClientService implements Client {

    public static final String DIAGNOSTIC_MESSAGE = "AT#UD";

    private boolean onlineStatus;
    private String diagnosticMessageResult = "";

    private Random connectionEventsSimulator;

    public ClientService() {
        this.connectionEventsSimulator = new Random(42);
    }

    @Override
    public boolean getOnlineStatus() {
        return this.onlineStatus;
    }

    @Override
    public void sendMessage(String message) {
        if (message == null || "".equals(message)) {
            throw new IllegalArgumentException();
        }

        if (message == DIAGNOSTIC_MESSAGE) {
            // simulate a status report
            this.diagnosticMessageResult =
                    "LAST TX rate................ 100 MBPS\r\n"
                            + "HIGHEST TX rate............. 100 MBPS\r\n"
                            + "LAST RX rate................ 100 MBPS\r\n"
                            + "HIGHEST RX rate............. 100 MBPS\r\n"
                            + "BIT RATE.................... 100000000\r\n"
                            + "WORD LEN.................... 16\r\n"
                            + "WORD/FRAME.................. 511\r\n"
                            + "BITS/FRAME.................. 8192\r\n"
                            + "MODULATION TYPE............. PCM/FM\r\n"
                            + "TX Digital Los.............. 0.75\r\n"
                            + "RX Digital Los.............. 0.10\r\n"
                            + "BEP Test.................... -5\r\n"
                            + "Local Rtrn Count............ 00\r\n"
                            + "Remote Rtrn Count........... 00";

            return;
        }
        // here should go the real Send operation (not needed for this exercise)
    }

    @Override
    public String receiveMessage() {
        String message;

        if (this.diagnosticMessageResult == null || "".equals(this.diagnosticMessageResult)) {
            // simulate a received message (just for illustration - not needed for this exercise)
            message = "";
            int messageLength = connectionEventsSimulator.nextInt(50) + 60;
            for (int i = messageLength; i >= 0; --i) {
                message += (char) connectionEventsSimulator.nextInt(40) + 86;
            }

        } else {
            message = this.diagnosticMessageResult;
            diagnosticMessageResult = "";
        }

        return message;
    }
}
