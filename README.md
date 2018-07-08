# Football_Application
Players and Halls Management system
This app is created using Android Studio. Target sdk version is 26 and minimum sdk version is 21.
SQLite database is used.
Basically app has 2 user types.
1. Players :- Players can register and authenticated on login.
2. Halls :- Halls can register and authenticated on login.

Functionality:
1. Halls :- after successful login, Homepage consists of list of Halls using RecyclerView. OnClick of each Hall item, new activity
   (Hall Details Activity) opens which has Halls details such as Hall Name, Address, Capacity, Reviews, Rating. There is a Create Event 
   Button only visible for Halls. Only Halls can create events and Players can Register for these events under each Halls. Halls data is 
   coming from SQLite database. When Event is created, it gets inserted in SQLite Database under respective Hall.
 
2. Players :- after successful login, Homepage consists of list of Halls using RecyclerView. OnClick of each Hall item, new activity
   opens which has Halls details such as Hall Name, Address, Capacity, Reviews, Rating. There is no Create Event Button. List of events 
   created by Halls are shown. Only Players can Register for these events under Halls.
   
Note: On Hall Details Activity, About CardView and Reviews Recyclerview data is static and is not coming from database.
 
