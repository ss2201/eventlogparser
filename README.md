# eventlogparser
1. Clone this repository.
2. Make sure you are using JDK 1.11 and Maven 3.x.
3. You can build the project and run the tests by running mvn clean package.
4. Once successfully built, you can run the service using below command, in the arguments pass the location of logfile.txt containing event log details:
        mvn spring-boot:run -Drun.arguments="/logfile.txt"
