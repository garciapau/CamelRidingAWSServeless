# CamelLab

## Lab Description
Lab that shows hot to use Camel to orchestrate a process using AWS Lambdas, SQS and SNS Since this solution implements asynchronous patterns, every stage is defined as a separate Camel route

## Design
TODO

## Tracking instances - Database Persistence
Improvement. Instead of keeping it in  memory, Camel stores the instances history into a non-embedded Apache Derby database.
More info at http://camel.apache.org/tracer-example.html

First install Derby and an instance at: jdbc:derby://localhost/[Workspace]/CamelLab/target/derby
You can set Derby's database URI updating the file [Workspace]\CamelLab\src\main\resources\META-INF\persistence.xml

Then start Derby server:
```bash
<db-derby-home>\bin>./NetworkServerControl start
```
To explode the content, SQuirreL (https://db.apache.org/derby/integrate/SQuirreL_Derby.html) can be easily configured as an SQL client.
Below is a sample query to retrieve the instances tracking:
```sql
    select TIMESTAMP, ROUTEID, BODY, BODYTYPE, CAUSEDBYEXCEPTION, EXCHANGEPATTERN,
           PREVIOUSNODE, TONODE, SHORTEXCHANGEID
    from APP.CAMEL_MESSAGETRACED;
```
## How to use it
```bash
git clone https://github.com/garciapau/CamelRidingAWSServeless.git
cd CamelRidingAWSServeless
mvn hawtio:camel
```
It automatically starts the Hawtio UI to model, monitor, track... the workflows at http://localhost:8090/hawtio/
