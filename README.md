Service Broker Demo
===================

This project contains a sample implementation of a Service Broker based on the [Spring Cloud Open Service Broker](https://github.com/spring-cloud/spring-cloud-open-service-broker)

It provides the following solutions:

* Dispatching of multiple services (service definitions) by the very same broker


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

References
==========

* [Spring Cloud Open Service Broker](https://github.com/spring-cloud/spring-cloud-open-service-broker)
* [Sample Implementation of Service Broker: MongoDB](https://github.com/spring-cloud-samples/cloudfoundry-service-broker)
* [Collection of Sample Service Brokers](https://docs.cloudfoundry.org/services/examples.html)
