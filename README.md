## Credit Card Microservice
Microservice for managing credit cards.

### what's used in application
- Junit with help of mockito
- Spring Boot 5
- Microservice
- REST based application
- Spring Exception Handler
- Spring Validation Framework.
- Spring repository for CRUD operations.
- OOPS concepts
- Design principles Keep-It-Simple and DRY
- code structure
- gradle (mvn could have also been used)
- In memory H2 database for sample 
- lambok apis for easing development
- Spring acurator

### How to run application - 
    1) Please use docker image and use postman to run the apis for posting your requests. 
    For documentation please refer Open Api. 
    docker image name is pinkimittal/card-service
    2) Or run CardserviceApplication.java file (your apis are up!) feel free to use post and get creditcards.
        For running on local use command  
    ./gradlew build test && java -jar build/libs/card-service-0.0.1-SNAPSHOT.jar

    applications is running on port-8081
### Test cases report generation url
    ..../card-service/build/reports/tests/test/index.html

### APIs
#####Please refer Open API documentation for latest information.
    
    http://localhost:8081/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

    To check system health: GET http://localhost:8081/actuator/health
    To add credit card use: POST http://localhost:8081/credit-card
    To get all credit cards: GET http://localhost:8081/credit-card/all
    To get one credit card: GET http://localhost:8081/credit-card/{cardNumberInput}

####More Enhancements i know below leftovers as well, but could not complete due to lack of time - 
- authentication and authorization
- Wiremock usage / Integration test 
- BDD (I could have written a BDD file)

##steps for dockerization on local
- Pre-requisite: first make sure you have all libs and files at right place 
    - mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)
- Now run below steps
    - docker build --build-arg DEPENDENCY=build/dependency -t pinkimittal/card-service .
    - ./gradlew bootBuildImage --imageName=pinkimittal/card-service
    - docker run -p 8081:8081 -t pinkimittal/card-service
- check your docker with below command
    - docker ps
- Now you can use apis to post / get your cards.