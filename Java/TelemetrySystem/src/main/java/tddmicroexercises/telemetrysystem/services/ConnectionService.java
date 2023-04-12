package tddmicroexercises.telemetrysystem.services;

import tddmicroexercises.telemetrysystem.interfaces.Connection;

import java.util.Random;

public class ConnectionService implements Connection {

    private boolean onlineStatus;

    private Random connectionEventsSimulator;

    public ConnectionService() {
        connectionEventsSimulator = new Random(42);
    }

    @Override
    public void connect(String telemetryServerConnectionString) {
        if (telemetryServerConnectionString == null || "".equals(telemetryServerConnectionString)) {
            throw new IllegalArgumentException();
        }
        // simulate the operation on a real modem
        boolean success = connectionEventsSimulator.nextInt(10) <= 8;

        onlineStatus = success;
    }

    @Override
    public void disconnect() {
        onlineStatus = false;
    }
}
