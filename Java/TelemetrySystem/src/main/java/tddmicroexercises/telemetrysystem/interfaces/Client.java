package tddmicroexercises.telemetrysystem.interfaces;

public interface Client {
    boolean getOnlineStatus();

    void sendMessage(String message);

    String receiveMessage();
}
