package tddmicroexercises.telemetrysystem.services;

import tddmicroexercises.telemetrysystem.interfaces.Client;
import tddmicroexercises.telemetrysystem.interfaces.Connection;
import tddmicroexercises.telemetrysystem.interfaces.DiagnosticControls;

public class TelemetryDiagnosticControls implements DiagnosticControls {
    private final String DiagnosticChannelConnectionString = "*111#";
    private final Client clientService;
    private final Connection connectionService;
    private String diagnosticInfo = "";

    public String getDiagnosticInfo() {
        return diagnosticInfo;
    }

    public TelemetryDiagnosticControls(Client clientService, Connection connectionService) {
        this.clientService = clientService;
        this.connectionService = connectionService;
    }

    @Override
    public void checkTransmission() throws Exception {
        diagnosticInfo = "";

        connectionService.disconnect();

        int retryLeft = 3;
        while (clientService.getOnlineStatus() == false && retryLeft > 0) {
            connectionService.connect(DiagnosticChannelConnectionString);
            retryLeft -= 1;
        }

        if (clientService.getOnlineStatus() == false) {
            throw new Exception("Unable to connect.");
        }

        clientService.sendMessage("AT#UD");
        diagnosticInfo = clientService.receiveMessage();

    }
}
