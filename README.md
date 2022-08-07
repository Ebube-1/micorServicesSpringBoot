# MicroServicesSpringBoot
Microservices application built with Spring boot, Cloud Services and Resilience4j.

### Services

The functional services are composed into three, which includes Accounts Microservice, Loans Microservice and Cards Microservice.

|                   |    Api Gateway     |                  |
|   -------------   |    -------------   |    -----------   |
| Accounts Service  |  Cards Service     |  Loans Service   |
| Accounts Database |  Cards Database    |  Loans Database  |

## Accounts

Contains APIs for registering a customer, creating an account, getting account details, fallback method for getting account details,
getting customer details which includes different accounts, loans on the diffrent accounts and cards. This microservice contains its own database.

|  Method  |         Path                |        Description                                  |
|  ------  |       -------------         |      ---------------
|   Post   |   /api/users/register       |   Create new account                                |
|   Get    |   /api/users/confirm        |   Confirm token                                     |
|   Get    |   /api/users/resendToken    |   Resend expired token                              |
|   Post   |   /myAccount                |   Get account details                               |
|   Post   |   /myCustomerDetails        |   Get customer accounts, loans and cards            | 
|   Post   |   /myCustomerDetailsFallBack| Fallback for customer accounts, loans and cards     |

## Cards

Contains infornmation about customer cards for different accounts, this microservice also contains its own database. This microservice uses Postgres for data storage.

|  Method  |         Path           |        Description          |
|  ------  |       -------------    |      ---------------
|   Post   |        /myCards        |     Get card details        |

## Loans

Contains infornmation about customer loan gotten for different accounts, this microservice also contains its own database. This microservice uses Postgres for data storage.

|  Method  |         Path         |        Description          |
|  ------  |     -------------    |      ---------------
|   Post   |       /myLoans       |     Get loan details        |

## Infrastructure

Spring Cloud is a really good web framework that we can use for building a microservice infrastructure since it provides broad supporting tools such as Load Balancer, Service registry, Monitoring, and Configuration.

## Config

[Spring Cloud Config](https://cloud.spring.io/spring-cloud-config/reference/html/) is horizontally scalable centralized configuration service for distributed systems. It uses a pluggable repository layer that currently supports local storage, Git, and Subversion.
The configuration for the project can be found [here](https://github.com/Ebube-1/microservicesConfig)

## Api Gateway
[Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) was used to implement Api gateway in this project. The three core services exposes APIs to clients but the number of services can exponentially increase which brings the need for Api gatway. It also helps in implementing cross cutting concerns such as load balancing, logging, tracing, rate limiting, authentication and authorization. It also helps in request aggregation.

## Service Discovery
Another commonly known architecture pattern is Service discovery. It allows automatic detection of network locations for service instances, which could have dynamically assigned addresses because of auto-scaling, failures and upgrades. [Spring registration and discovery](https://spring.io/guides/gs/service-registration-and-discovery/) was used forthis application.

