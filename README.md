# wh-scoreboard-api

**Requirement**
1. Create a service to create, find and update scoreboard
2. Provide a mechanism to push score updates to client's browser


**Objective**
1. Working model
2. Demostrate use of unit, IT and code coverage
3.

**Out of scope:**
1. RBAC model not implemented
2. Form based authentication used(idealy jwt should be used)
3. No Authorization
4. Spring SIMP used.
5. In memory database H2 has been used for persistence.
6. Tests have been written for important classes and methods
7. unit tests have been written for front end using jasmine/karma
8. unit tests for backend are using junit/mockito
9. CRUD operations are available for all entities but all may not be needed
10. Websockets have been used to push messages/updates to client browser


**Demo:**
1. Demo is hosted on Heroku and can web be accessed at https://wh-scorecard-web.herokuapp.com/. Login credentials to be provided
2. The api are availabled at https://wh-scorecard-api.herokuapp.com/
3. Api documentation is available at https://wh-scorecard-api.herokuapp.com/swagger-ui/index.html






1. Some class names may not make sense as some part of the code has been taken from my existing repo.
2.

**DML available below**
![image](https://user-images.githubusercontent.com/4318051/158604289-d7bbe9d6-25f9-42ef-9e55-3f1ac1e49d17.png)


Step to run the demo:
1. Goto the url
 ![image](https://user-images.githubusercontent.com/4318051/158612188-1e033ef5-441d-4314-8783-eb6c32d3e9a7.png)

2. Login using the provided url (call it viewer)
 ![image](https://user-images.githubusercontent.com/4318051/158612822-37cd4063-b147-4ddd-bea5-cca6f1de345d.png)


3. In Another window open the same url with the same credentials (call it management)
 
4. In Management Click Manage to a scorecard
  ![image](https://user-images.githubusercontent.com/4318051/158612988-77a11b88-964e-46b2-b12d-0524538b3236.png) 

5.  us button +  or - to increase or decrease the score of that match
 ![image](https://user-images.githubusercontent.com/4318051/158613177-3d4901d7-f9a9-42e0-9853-16ddd2e1f588.png) 

7.  The changes will flow through the messaging system then to the viewer browser using websocket automatically


Component diagram to illustrate 
