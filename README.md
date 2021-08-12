# Overview
Technical Assessment Exercise for Gaggle
Web Api for contacts management

This project can be executed by following these instructions:
* Clone the repo on your local machine: $ git clone https://github.com/wadigzon/candidate-technical-assessment-exercise.git
* Go to the project directory: $ cd ./candidate-technical-assessment-exercise/contacts-api
* Deploy/install the app: $ mvn install
* Execute the Rest Api server: $ java -jar target/contacts-api-0.0.1-SNAPSHOT.jar
* You can leave that console running (this is your server instance), to stop your server press CTRL+C in this window
* Open another window (either postman or another console to test the running server) and this will be your client tester (see Test section)

# Requirements
* Project was done using Java 11, so make sure you have that on your local machine and your JAVA_HOME variable is pointing towards that directory.
* Using H2 in memory database, so once you close the server, all the data will be gone.

# Test
* You can use curl command (either on windows/mac/linux) to test the App on a console
* Note that curl supports single quote on console if you're on linux or mac, on windows use the escape (\") to put quotes within quotes
* Test 1: Check Records
  * At this point there should not be records on the DB
  * do a simple getAllRecords (GET): $ curl -v http://localhost:8081/api/contacts
  * Should see something like this right after:</br>

    <p> *   Trying ::1... </br>
    * TCP_NODELAY set </br>
    * Connected to localhost (::1) port 8081 (#0) </br>
    > GET /api/contacts HTTP/1.1 </br>
    > Host: localhost:8081</br>
    > User-Agent: curl/7.55.1</br>
    > Accept: */*</br>
    ></br>
    < HTTP/1.1 200</br>
    < Content-Type: application/json</br>
    < Transfer-Encoding: chunked</br>
    < Date: Thu, 12 Aug 2021 03:50:05 GMT</br>
    <</br>
    []* Connection #0 to host localhost left intact </br></p>
*
