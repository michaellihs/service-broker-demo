package ch.lihsmi.demobroker.service.handler;

import org.springframework.cloud.servicebroker.model.instance.*;

public interface ServiceInstanceRequestHandler {

    String getServiceDefinitionId();

    default CreateServiceInstanceResponse handleRequest(CreateServiceInstanceRequest request) {
        throw new UnsupportedOperationException("This service instance request handler does not support creating service instances.");
    }

    default GetServiceInstanceResponse handleRequest(GetServiceInstanceRequest request) {
        throw new UnsupportedOperationException("This service instance request handler does not support retrieving service instances. " +
                "The service broker should set 'instances_retrievable:false' in the service catalog, " +
                "or provide an implementation of the fetch instance API.");
    }

    default UpdateServiceInstanceResponse handleRequest(UpdateServiceInstanceRequest request) {
        throw new UnsupportedOperationException("This service instance request handler does not support updating service instances." +
                "The service broker should set 'plan_updateable:false' in the service catalog.");
    }

    default DeleteServiceInstanceResponse handleRequest(DeleteServiceInstanceRequest request) {
        throw new UnsupportedOperationException("This service instance request handler does not support deleting service instances.");
    }

    default GetLastServiceOperationResponse handleRequest(GetLastServiceOperationRequest request) {
        throw new UnsupportedOperationException("This service instance request handler does not support getting the status of " +
                "an asynchronous operation. " +
                "If the service broker returns '202 Accepted' in response to a provision, update, or deprovision" +
                "request, it must also provide an implementation of the get last operation API.");
    }

}
