# Scoreboard

**Requirement**
1. Create a service to create, find and update scoreboard
2. Provide a mechanism to push score updates to client's browser


**Objective**
1. Working model
2. Demostrate use of unit, IT and code coverage
3. Frameworks/tech: Spring, hibernate/jpa, angular 

**Out of scope:**
1. RBAC model not implemented
2. Form based authentication used to get a jwt token
3. No Authorization atm
4. Spring SIMP/Stomp used.
5. In memory database H2 has been used for persistence.
6. Tests have been written for important classes and methods only
7. unit tests have been written for front end using jasmine/karma
8. unit tests for backend are using junit/mockito
9. CRUD operations are available for all entities but all may not be needed
10. Websockets have been used to push messages/updates to client browser


**Demo:**
1. Demo is hosted on Heroku and can web be accessed at https://wh-scorecard-web.herokuapp.com/. Login credentials to be provided
2. The api are availabled at https://wh-scorecard-api.herokuapp.com/
3. Api documentation is available at https://wh-scorecard-api.herokuapp.com/swagger-ui/index.html


**Note:**
1. Some class names may not make sense as part of the code has been taken from my existing private repo.


**DML available below**
![image](https://user-images.githubusercontent.com/4318051/158604289-d7bbe9d6-25f9-42ef-9e55-3f1ac1e49d17.png)


**Step to run the demo:**

1. Goto the url https://wh-scorecard-web.herokuapp.com/
![image](https://user-images.githubusercontent.com/4318051/158614643-bc530755-b94f-444e-9dc4-5ed8e0d0675b.png)

2. Login using the provided url (call it viewer)
![image](https://user-images.githubusercontent.com/4318051/158620758-c03af8eb-7ae6-463b-b035-c28a3aacf8e9.png)


3. In Another window open the same url with the same credentials (call it management)
 
4. In Management Click Manage to a scorecard
![image](https://user-images.githubusercontent.com/4318051/158620558-75579b19-92f8-45fb-8710-27211fec3834.png)

5.  us button +  or - to increase or decrease the score of that match
 ![image](https://user-images.githubusercontent.com/4318051/158613177-3d4901d7-f9a9-42e0-9853-16ddd2e1f588.png) 

7.  The changes will flow through the messaging system then to the viewer browser using websocket automatically
![image](https://user-images.githubusercontent.com/4318051/158621252-e2b5b257-0c57-4e5c-aa45-848df047e544.png)

**Component diagram to illustrate **

![image](https://user-images.githubusercontent.com/4318051/158621829-9f965ab5-c748-4c99-9db1-b5d2f8a872f9.png)


**Swagger APi Documentation:**
![image](https://user-images.githubusercontent.com/4318051/158622847-ea82e7f4-cd53-43a0-812e-45c71cd7ccbe.png)

**Test Results:**
![image](https://user-images.githubusercontent.com/4318051/158623521-8a645202-bef0-42f9-b5fa-61bed168ebdd.png)

**Ref:** https://www.continuum.be/blog/sending-stomp-messages-over-a-websocket-in-spring-boot-2/

