# Overview
Technical Assessment Exercise for Gaggle
Web Api for contacts management

This project can be executed by following these instructions:
* Clone the repo on your local machine: $ git clone https://github.com/wadigzon/candidate-technical-assessment-exercise.git
* Go to the project directory: $ cd ./candidate-technical-assessment-exercise/contacts-api
* Deploy/install the app: $ mvn install
* Start Rest Api server: $ java -jar target/contacts-api-0.0.1-SNAPSHOT.jar
* You can leave that console running (this is your server instance), to stop your server press CTRL+C in this window
* Open another window (either postman or another console to test the running server) and this will be your client tester (see Test section)

# Requirements
* Project was done using Java 11, so make sure you have that on your local machine and your JAVA_HOME variable is pointing towards that directory.
* Using H2 in memory database, so once you close the server, all the data will be gone.

# Test Using Curl
* You can use curl command (either on windows/mac/linux) to test the App on a console
* Note that curl supports single quote on console if you're on linux or mac, on windows use the escape (\") to put quotes within quotes
* Test 1: Check Records
  * At this point there should not be records on the DB
  * At the command line do: $ curl -v http://localhost:8081/api/contacts
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
  * Note that text at the bottom: "[]", right after the last "<" and before "Connection #0 ..." text, that's the response from REST API server (an empty array), which means no contacts on the DB
* Test 2: Insert some contacts
  * At the command line do: $ curl -d "{\"name\":\"Wadsui Diaz-Alvarado\"}" -H "Content-Type: application/json" http://localhost:8081/api/contacts/
  * If you're in linux/mac you can do: $ curl -d '{"name":"Wadsui Diaz-Alvarado"}' -H 'Content-Type: application/json' http://localhost:8081/api/contacts/
  * You should see this right after pressing Enter: {"id":1,"name":"Wadsui Diaz-Alvarado"}
  * Which represents the record (contact) we just inserted
  * Insert another contact: $ curl -d "{\"name\":\"Wadigzon Diaz-Wong\"}" -H "Content-Type: application/json" http://localhost:8081/api/contacts/
  * And another one: $ curl -d "{\"name\":\"Susana Alvarado-Robles\"}" -H "Content-Type: application/json" http://localhost:8081/api/contacts/
  * Make sure you see the corresponding added record as a response right after sending the request
* Test 3: Insert same contact
  * If you try and insert a contact with the same exact name that already exists, the server will respond with that existing record, and WILL NOT add another record with a different id, let's try it next
  * At the command line do: $ curl -d "{\"name\":\"Wadsui Diaz-Alvarado\"}" -H "Content-Type: application/json" http://localhost:8081/api/contacts/
  * You should see the same record as a responose, if you do it more than one (up arrow to repeat the last command)
* Test 4: Repeat Test 1 (Check Records)
  * At the command line do: $ curl -v http://localhost:8081/api/contacts
  * Like in Test 1, note the text at the bottom section, right after the last "<" and before "* Connection #0 to host ...",</br> this time we have an array with three records: [{"id":1,"name":"Wadsui Diaz-Alvarado"},{"id":2,"name":"Wadigzon Diaz-Wong"},{"id":3,"name":"Susana Alvarado-Robles"}]
* Test 5: Get an element by Id
  * Let's get the element with id = 2, shown in previous test
  * At the command line do: $ curl -v http://localhost:8081/api/contacts/2
  * Like before, note the text at the bottom section, right after the last "<" and before "* Connection #0 to host ...",</br> we've got the requested contact: {"id":2,"name":"Wadigzon Diaz-Wong"}
  * Try it with another id and make sure you get the right record
* Test 6: Do a search (elements that contain a text within the name)
  * Let's get those contacts that have the words (not case sensitive) "alvarado" in the name
  * At the command line do: $ curl -v http://localhost:8081/api/contacts/namesearch/alvarado
  * Like before, note the text at the bottom section, right after the last "<" and before "* Connection #0 to host ...",</br> we've got those contacts that contain 'alvarado' in the name field: [{"id":1,"name":"Wadsui Diaz-Alvarado"},{"id":3,"name":"Susana Alvarado-Robles"}]
  * Do another search, let's get those contacts with word "diaz" in the name
  * At the command line do: $ curl -v http://localhost:8081/api/contacts/namesearch/alvarado
  * Like before, note the text at the bottom section, right after the last "<" and before "* Connection #0 to host ...",</br> we've got those contacts that contain 'diaz' in the name field: [{"id":1,"name":"Wadsui Diaz-Alvarado"},{"id":2,"name":"Wadigzon Diaz-Wong"}]
* Test 7: Do an exact search
  * Let's get the contact with the name: "wadigzon%20diaz-wong" (NOT case sensitive)
  * Note the %20 (escape character for space) in the url
  * At the command line do: $ curl -v http://localhost:8081/api/contacts/name/wadigzon%20diaz-wong
  * Like before, note the text at the bottom section, right after the last "<" and before "* Connection #0 to host ...",</br> we've got that contact with that exact name (ignoring case): [{"id":2,"name":"Wadigzon Diaz-Wong"}]
* Test 8: Do an Update
  * We're going to update the name of contact with id = 3  ("Susana Alvarado-Robles"), with the new name = "Susana Alvarado-Roblesa"
  * At the command line do: $ curl -d "{\"name\":\"Susana Alvarado-Roblesa\"}" -H "Content-Type: application/json" -X PUT http://localhost:8081/api/contacts/2
  * You will get a single entry as a response, the new updated record: {"id":2,"name":"Susana Alvarado-Roblesa"}
* Test 9: Insert another record
  * At the console do: curl -d "{\"name\":\"Michael Jackson-Five\"}" -H "Content-Type: application/json" http://localhost:8081/api/contacts/
  * Make sure you see the corresponding added record as a response right after sending the request
* Test 10: Get the count of records
  * At the console do: curl -v http://localhost:8081/api/contacts/count
  * Like before, note the text at the bottom section, right after the last "<" and before "* Connection #0 to host ...",</br> we've got the number of records in the DB: 4
* Test 11: Delete a contact
  * Let's delete the contact we just added (id = 4)
  * At the command line do: $ curl -X DELETE http://localhost:8081/api/contacts/4
  * The response should be an empty line
  * To test that contact is gone do: $ curl -v http://localhost:8081/api/contacts/4
  * Should get a "Contact not found" message at the end section of response (as shown before)

# Test Using Postman
  * Open Postman app
  * Import the file contained at the root of the project .\Gaggle.postman_collection.json by following these steps:
    * On the menu go to File > Import (or press CTRL+O)
    * Click on Upload Files and select the json file mentioned previously (or drag the file to this modal)
    * Once file has been uploaded, click on Import
    * You will see a collection named Gaggle on the left hand side of the screen, click on it (expand it)
    * You will see all the requests to test the Rest API server: getAllContacts, getContactById, getContactByName, insertContact, deleteContact, updateContact, nameSearch, contactsCount
  * Test 1: Check records
    * Click on getAllContacts request on your left
    * A tab should open on your right containing the details of the request, just click on Send
    * On the Body of the response you should see all the records (if starting the test it should be [])
  * Test 2: Insert some contacts
    * Click on insertContact request on your left
    * A tab should open on your right containing the details of the request, click on the Body section of the request an put the contact you want there (you might want to change the one that's there)
    * Click on Send, make sure you get the new record (with the new assigned id value) as a response
    * Click send again and make sure you get the same record (it won't let you insert two records with the same name)
    * Feel free to add more records by changing the name and clicking Send
  * Test 3: Check records
    * Go back to getAllContacts tab and click Send
    * Should see the newly added records there surrounded by [] in the body of response
  * Test 4: Get an Element by Id
    * Select getContactById on your list of requests (click on it)
    * There should be a new tab opened on your right, make sure the URL contains the right id
    * Make sure you get "Contact not found" if you put an id that is not there
  * Test 5: Search a contact
    * Select nameSearch request on your left (click on it)
    * Make sure you use %20 to use spaces on your text
    * On the right tab opened, Change the url to contain the text you're searching for, e.g. localhost:8081/api/contacts/namesearch/text-to-search
    * Click Send
    * Make sure the record(s) returned contain the text searched for in the name field
  * Test 6: Search a contact with exact name
    * Select getContactByName request on your left (click on it)
    * On the right tab opened, Change the url to contain the text you're searching for, e.g. localhost:8081/api/contacts/name/text-to-search
    * Make sure you use %20 to use spaces on your text
    * Click Send
    * Make sure the record returned contain exact text searched for (name should be unique and is not case sensitive)
  * Test 7: Remove a contact
    * Select deleteContact request on your left (click on it)
    * On the right tab opened, Change the url to contain the id you're trying to delete for, e.g. localhost:8081/api/contacts/3
    * Click Send
    * Make sure you get a 200 OK on Status for the response (though the body is empty for now)
  * Test 8: Update a Contact
    * Select updateContact request on your left (click on it)
    * Change the id on url related to the contact you want to update
    * On Body section make sure you put the new name as a json element, e.g. {"name": "Susana Alvarado-Roblesa"}
    * Click Send
    * And check the response, it should contain the new name and same id you sent
  * Test 9: Get count
    * Click on contactsCount request on your lef (click on it)
    * Just click Send
    * Response should contain the number of records in the DB

# Missing things
Added some JUnit test using mockito library for the controller, but did not have time to make it work 100%, after an insert was done for instance, the data was gone at the next request, you still see those tests pass when you do mvn install (test is located at ContactsApiApplicationTests.java)

# Dependency injection
I used @Autowired annotation as much as I could across the app (even at test section) to leave the object creation mechanism to the container