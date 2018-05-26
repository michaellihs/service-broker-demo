Service Broker Demo
===================

This project contains a sample implementation of a Service Broker based on the [Spring Cloud Open Service Broker](https://github.com/spring-cloud/spring-cloud-open-service-broker)

It provides the following solutions:

* Dispatching of multiple services (service definitions) by the very same broker

Using the Broker
================

Building the Broker
-------------------

Build the service broker application with

    ./gradlew clean build


Running the Broker
------------------

Run the service broker application with

    ./gradlew clean bootRun


Testing the Broker
------------------

Run the tests with

    ./gradlew clean test -i


Development
===========

Implementing new `ServiceInstanceRequestHandler`
------------------------------------------------

Let's say you want to implement a handler for a new `Mega` service:

1. Create a new class `MegaServiceInstanceRequestHandler` in the `ch.lihsmi.demobroker.service.handler` package.
2. This class needs to implement the `ServiceInstanceRequestHandler` interface and must be annotated with the `@ServiceInstanceRequestHandlerBean` annotation.
3. Make sure that the `getServiceDefinitionId()` method returns the service definition ID which is processed by your new handler (`mega`) as defined in the service plan of the broker.
4. Implement handler logic in `handleRequest()`

Sample code:

````java
package ch.lihsmi.demobroker.service.handler;

import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;
import org.springframework.stereotype.Component;

@Component
@ServiceInstanceRequestHandlerBean
public class MegaServiceInstanceRequestHandler implements ServiceInstanceRequestHandler {

    private final String serviceDefinitionId = "mega";

    @Override
    public CreateServiceInstanceResponse handleRequest(final CreateServiceInstanceRequest request) {
        return CreateServiceInstanceResponse.builder()
                .operation(serviceDefinitionId)
                .build();
    }
    
    // implement further methods of the ServiceInstanceRequestHandler interface as required

    @Override
    public String getServiceDefinitionId() {
        return serviceDefinitionId;
    }

}
````


References
==========

* [Spring Cloud Open Service Broker](https://github.com/spring-cloud/spring-cloud-open-service-broker)
* [Sample Implementation of Service Broker: MongoDB](https://github.com/spring-cloud-samples/cloudfoundry-service-broker)
* [Collection of Sample Service Brokers](https://docs.cloudfoundry.org/services/examples.html)
