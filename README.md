# 4448 Project 3
### Contact information
 - Name: Leyen Qian (108384916)</br>
 - Email: jiqi2811@colorado.edu</br>
 - Team Members: Yi Hou (109288627), Ziyue Guo (107037369)

### Files Description
 - Main.java</br>
the entry point of the program.</br>

 - output.pdf / .docx<br>
sample simulation output of 30 days

 - /lib/*
included lib for unit testing

 - /Customer/Customer.java
abstract class for Business.java, Casual.java and Catering.java<br>
Provide functionalities that shared in common

 - /Customer/CustomerFactory.java
all functions are static, used to generate different customers based on the customer type (types are defined within class)

 - /Food/Food.java
abstract class for EggRoll.java, JellyRoll.java, PastryRoll.java, SausageRoll.java and SpringRoll.java<br>
Provide functionalities that shared in common

 - /Food/FoodFactory.java
all functions are static, used to generate different foods based on the food type (types are defined within class)

 - /FoodStore/Service/Product.java
It acts like a wapper for different rolls (food)

 - /FoodStore/Service/ServiceDecorator.java
Decorator for product, extends by Filling.java, Sauce.java and Topping.java

 - /FoodStore/ServiceFactory.java
Based on the given service type, generate corresponding service that include the input original product(ServiceDecorator)

 - /FoodStore/Constants.java
predefine of product price and service price index

 - /FoodStore/FoodStore.java
It defines the basic functionalities that a food store supposed to have<br>
Such as get product, cancle order, check bill

 - /FoodStore/Inventory.java
It used to store the inventory information

 - /FoodStore/Records.java
It used to store order information and outage information