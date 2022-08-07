# microServicesSpringBoot
Microservices application built with Spring boot, Cloud Services and Resilience4j.

###Services
The functional services are composed into three, which includes Accounts Microservice, Loans Microservice and Cards Microservice.

|                   |    Api Gateway     |                  |
|   -------------   |    -------------   |    -----------   |
| Accounts Service  |  Cards Service     |  Loans Service   |
| Accounts Database |  Cards Database    |  Loans Database  |

##Accounts
Contains APIs for registering a customer, creating an account, getting account details, fallback method for getting account details,
getting customer details which includes different accounts, loans on the diffrent accounts and cards.

|  Method  |         Path             |        Description       |
|  ------  |       -------------      |      ---------------
|   Post   |   /api/users/register    |   Create new account     |
|   Get    |   /api/users/confirm     |   Confirm token          |
|   Get    |   /api/users/resendToken |   Resend expired token   |
