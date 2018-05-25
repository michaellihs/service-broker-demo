package ch.lihsmi.demobroker;

import ch.lihsmi.demobroker.service.DemoServiceInstanceService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.servicebroker.model.instance.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceInstanceServiceTests {

    @Rule   // see https://stackoverflow.com/a/2935935/1549950
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private DemoServiceInstanceService instanceService;

	@Test
	public void dispatchingFooServiceRequestReturnsExpectedResponse() {
	    createService("foo-service");
	}

	@Test
    public void dispatchingBarServiceRequestReturnsExpectedResponse() {
	    createService("bar-service");
    }

    @Test
    public void dispatchingProServiceRequestReturnsExpectedResponse() {
	    createService("pro-service");
    }

    @Test
    public void unsupportedDeleteServiceInstanceOperationThrowsExpectedException() {
        DeleteServiceInstanceRequest request = DeleteServiceInstanceRequest.builder()
                .serviceDefinitionId("foo-service")
                .build();

        exception.expect(UnsupportedOperationException.class);
        instanceService.deleteServiceInstance(request);
    }

    @Test
    public void unsupportedUpdateServiceInstanceOperationThrowsExpectedException() {
        UpdateServiceInstanceRequest request = UpdateServiceInstanceRequest.builder()
                .serviceDefinitionId("foo-service")
                .build();

        exception.expect(UnsupportedOperationException.class);
        instanceService.updateServiceInstance(request);
    }

    @Test
    public void unsupportedGetLastServiceOperationThrowsExpectedException() {
        GetLastServiceOperationRequest request = GetLastServiceOperationRequest.builder()
                .serviceDefinitionId("foo-service")
                .build();

        exception.expect(UnsupportedOperationException.class);
        instanceService.getLastOperation(request);
    }

    private void createService(String serviceDefinitionId) {
        CreateServiceInstanceRequest request = CreateServiceInstanceRequest.builder()
                .serviceDefinitionId(serviceDefinitionId)
                .build();

        CreateServiceInstanceResponse response = instanceService.createServiceInstance(request);
        assertEquals(serviceDefinitionId, response.getOperation());
    }

}
