# Cheapeast hotels search using java
Project for entry level interview

## About the project:

HOTEL RESERVATION PROBLEM
 
A hotel chain operating in Miami wishes to offer room reservation services over the internet. 
They have three hotels in Miami: Lakewood, Bridgewood and Ridgewood. Each hotel has 
separate weekday and weekend(Saturday and Sunday) rates. There are special rates for 
rewards customer as a part of loyalty program. Each hotel has a rating assigned to it.
● Lakewood with a rating of 3 has weekday rates as 110$ for regular customer and 80$ 
for rewards customer. The weekend rates are 90$ for regular customer and 80$ for a 
rewards customer.
● Bridgewood with a rating of 4 has weekday rates as 160$ for regular customer and 110$ 
for rewards customer. The weekend rates are 60$ for regular customer and 50$ for a 
rewards customer.
● Ridgewood with a rating of 5 has weekday rates as 220$ for regular customer and 100$ 
for rewards customer. The weekend rates are 150$ for regular customer and 40$ for a 
rewards customer.

Write a program to help an online customer find the cheapest hotel.
The input to the program will be a range of dates for a regular or rewards customer. The output should be the cheapest available hotel. In case of a tie, the hotel with highest rating should be returned.
 
INPUT FORMAT:
<customer_type>: <date1>, <date2>, <date3>, ...

OUTPUT FORMAT:
<name_of_the_cheapest_hotel>
INPUT 1:
Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)
OUTPUT 1:
Lakewood
INPUT 2:
Regular: 20Mar2009(fri), 21Mar2009(sat), 22Mar2009(sun)
OUTPUT 2:
Bridgewood
INPUT 3:
Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)
OUTPUT 3:
Ridgewood

## Explanation:

###### 1) To Run:
    a. with maven (*Preferible)
        i.  ) cambiar directorio a:
               cd ~/Hotel_java 
        ii. ) mvn clean install
        iii.) mvn exec:java -Dexec.mainClass=Hotel_java

    b.  cmd windows or Terminal mac
        i. ) Cambiar directorio a:
            cd ~/Hotel_java/src/main/java

        ii. ) javac Hotel_java.java
        iii.) java Hotel_java
        iv. ) dialogo le pedirá insertar archivo de texto

###### 2) System design:
I used the Factory Pattern design pattern for hotel creation. The use of this pattern facilitates the creation of hotels by specifying only their names. This allows the code to be extensible and less dependent on the specific instance of a hotel. Because there is common behavior among hotels, the `HotelBase` class implements methods that all hotels can inherit. Derivatives of `HotelBase` can be created with or without specifying the type of customer to use ( "reward" or "regular" customer). I added the functionality to set the customer type, so if a customer becomes "reward" in the future, reduced rates can be applied using the `hotel.setCustomerType()` method.

For handling hotel information, I created the `HotelsAnalyzer` class. This makes it easier to add new functionalities in the future. `HotelsAnalyzer` provides functionalities such as obtaining the price of a hotel on specific dates, getting the hotel with the highest ranking, the cheapest hotel, and the highest-ranking hotel that is the cheapest. All these functions are used to deliver the cheapest hotel (the program's objective). However, by making each function more modular, the code becomes extensible and easy to test.

To read files, assuming that only text files will be provided, I created the `HotelsReader` class to accept new input formats in the future and validate them. Currently, I assumed that the only format to use is: "Regular: 16Mar2009(mon), 17Mar2009(tues), 18Mar2009(wed)". However, the code is ready for modifications and to accept more formats. Also, I decided to add the option to accept files with multiple lines of input to make the program more powerful and easier to subject to robust testing.

The code includes various tests that I designed. The use of `JOptionPane` was to improve the user experience.
