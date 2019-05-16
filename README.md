# customer-phones
<p>This application is using Spring Boot and built by a red-green-refactoring process.
<h1>Run</h1>
Build the project with Maven, and run the application in an IDE, or run with the command:
java -jar custemer-phones-0.0.1-SNAPSHOT.jar</p> 
<h1>RESTful API call examples</h1>
<h3>Get all phone numbers:</h3>
<p>Request from browser: http://localhost:8080/customer-phones/numbers <br/>
or run command : curl -X GET http://localhost:8080/customer-phones/numbers <br/>
It will return all the phone numbers.</p>

<h3>Get phone numbers of a single user:</h3>
<p>Here we assume the user id is 1. <br/>
Request from browser: http://localhost:8080/customer-phones/customer/1 <br/>
or run command : curl -X GET http://localhost:8080/customer-phones/customer/1 <br/>
It will return all the phone numbers of this user or just an empty array [ ] if the user has no <br/>
phone number or there is no such user.</p>

<h3>Activated a phone number:</h3>
<p>Here we try to active a phone number +447653127772 <br/>
Run command : curl -X PATCH http://localhost:8080/customer-phones/numbers/+447653127772 <br/>
It will return the phone number with activated attriute as true {"number":"+447653127772","activated":true} <br/>
or empty if the phone number is not exit.</p>
<h1>Test</h1>
<p>This application has been fully tested by using MockMvc to send GET and PATCH request with or without parameters<br/>
to the APIs. Also the empty data or no data situation has been considered.</p>
