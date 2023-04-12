package tddmicroexercises.telemetrysystem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tddmicroexercises.telemetrysystem.services.ClientService;
import tddmicroexercises.telemetrysystem.services.ConnectionService;
import tddmicroexercises.telemetrysystem.services.TelemetryDiagnosticControls;

public class TelemetryDiagnosticControlsTest {

    ConnectionService connection = Mockito.mock(ConnectionService.class);
    ClientService clientService = Mockito.mock(ClientService.class);
    TelemetryDiagnosticControls telemetryDiagnosticControls = new TelemetryDiagnosticControls(clientService, connection);

    @Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response() throws Exception {
        Mockito.doNothing().when(connection).disconnect();
        Mockito.when(clientService.getOnlineStatus()).thenReturn(true);
        Mockito.doNothing().when(connection).connect("*111#");
        telemetryDiagnosticControls.checkTransmission();
        Mockito.verify(connection, Mockito.times(1)).disconnect();
    }

}
