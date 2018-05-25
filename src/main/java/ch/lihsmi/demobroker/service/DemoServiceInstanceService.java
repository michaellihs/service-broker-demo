package ch.lihsmi.demobroker.service;

import ch.lihsmi.demobroker.service.handler.ServiceInstanceRequestHandler;
import ch.lihsmi.demobroker.service.handler.ServiceInstanceRequestHandlerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.instance.*;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DemoServiceInstanceService implements ServiceInstanceService {

    private final Map<String, ServiceInstanceRequestHandler> serviceInstanceRequestHandlers;

    @Autowired
    public DemoServiceInstanceService(@ServiceInstanceRequestHandlerBean List<ServiceInstanceRequestHandler> serviceInstanceRequestHandlers) {
        this.serviceInstanceRequestHandlers = new HashMap<>();
        initServiceInstanceRequestHandlers(serviceInstanceRequestHandlers);
    }

    @Override
    public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
        return getServiceInstanceRequestHandler(request.getServiceDefinitionId()).handleRequest(request);
    }

    /**
     * we cannot implement this like we do here, since GetServiceInstanceRequest does not provide a getServiceDefinitionId() method
     * so we have to keep the relation of serviceInstanceId --> serviceDefinitionId in the state of the brokerd
    @Override
    public GetServiceInstanceResponse getServiceInstance(GetServiceInstanceRequest request) {
        return getServiceInstanceRequestHandler(request.getServiceDefinitionId()).handleRequest(request);
    }
    */

    @Override
    public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
        return getServiceInstanceRequestHandler(request.getServiceDefinitionId()).handleRequest(request);
    }

    @Override
    public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
        return getServiceInstanceRequestHandler(request.getServiceDefinitionId()).handleRequest(request);
    }

    @Override
    public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
        return getServiceInstanceRequestHandler(request.getServiceDefinitionId()).handleRequest(request);
    }

    private void initServiceInstanceRequestHandlers(@ServiceInstanceRequestHandlerBean List<ServiceInstanceRequestHandler> serviceInstanceRequestHandlers) {
        for (ServiceInstanceRequestHandler handler : serviceInstanceRequestHandlers) {
            this.serviceInstanceRequestHandlers.put(handler.getServiceDefinitionId(), handler);
        }
    }

    private ServiceInstanceRequestHandler getServiceInstanceRequestHandler(String serviceDefinitionId) {
        if (serviceInstanceRequestHandlers.containsKey(serviceDefinitionId)) {
            return serviceInstanceRequestHandlers.get(serviceDefinitionId);
        } else {
            throw new IllegalArgumentException("No service definition could be found for " + serviceDefinitionId);
        }
    }

}
