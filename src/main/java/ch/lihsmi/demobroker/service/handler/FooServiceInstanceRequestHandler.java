package ch.lihsmi.demobroker.service.handler;

import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;
import org.springframework.stereotype.Component;

@Component
@ServiceInstanceRequestHandlerBean
public class FooServiceInstanceRequestHandler implements ServiceInstanceRequestHandler {

    private final String serviceDefinitionId = "foo-service";

    @Override
    public CreateServiceInstanceResponse handleRequest(final CreateServiceInstanceRequest request) {
        return CreateServiceInstanceResponse.builder()
                .operation(serviceDefinitionId)
                .build();
    }

    @Override
    public String getServiceDefinitionId() {
        return serviceDefinitionId;
    }

}
