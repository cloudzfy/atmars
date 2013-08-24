## README
### An Implementation of a Micro Blog
Copyright (C) 2013 Cloudzfy. All Rights Reserved.

=======================================================

This project is an open source project that focuses on the implementation of a Micro Blog called AtMars. Using Struts, Spring and Hibernate Architecture and HTML5 page render technology.

### Introduction
This is an open source implementation of micro blog. It can also be named **@Mars**, which is a symbol of micro blog. Mars, as the fourth planet in the Solar System, is the first planet that human begin to study and research except earth. @Mars, means that the user group of the micro blog is focus on the foresight and pioneer of some new technology and design idea.

### Functions
AtMars, as a implementation of micro blog, has several functions as following:

* Standard Publish of micro blog
* Forward and Comment submission of micro blog
* Followers and Followings function
* Face emotion of micro blog
* Photo submission of micro blog
* Location of HTML5 Navigator
* Mail Validation

### System Topology
The system contains **proxy servers**, **web servers** and **back-end servers**. Users can not only use PC client but also use mobile device, such as PDA, cellphones, to visit @Mars Micro Blog.

When a user visit the website of @Mars Micro Blog, the **proxy server** is in use of load balance to balance the load of two or more **web servers**. Each of web server will deal with the sessions assigned for it. The data sharing server will be used in individual servers, including picture server, mail server and database server. **Picture server** is responsible for storing the pictures uploaded by users. **Mail server** is responsible for sending authentication e-mail to new users. **Database server** is responsible for storing structural data in relational tables.

![Topology](https://raw.github.com/wiki/cloudzfy/atmars/images/topology.png)

### System Architecture
The architecture of the system can be separated into three parts: **UI Layer**, **SSH Layer** and **DB Layer**.

* **UI Layer**, means User Interface Layer, is implemented by Java Server Pages (JSP). Using Hypertext Markup Language (HTML) as the page-render technology, we choose HTML5 as the main page development standard. In addition, we use Cascading Style Sheets version 3 (CSS3) to decorate the pages and JavaScript to control the page functions. Asynchronous JavaScript and XML (AJAX) is used to deal with the communication between client and server.

* **SSH Layer**, means the Layer of Struts, Spring and Hibernate. It is the integration of Open Source Architecture. Struts is responsible for the MVC Layer. Spring is responsible for the Service Layer. Hibernate is responsible for the ORM Mapping.

* **DB Layer**, means Database Layer. MySQL is used as the database for this project to store structural data.

![Architecture](https://raw.github.com/wiki/cloudzfy/atmars/images/architecture.png)

### Third-party Technology
* JavaMail API 1.4.5
* Jasypt 1.9.0
* ActiveMQ 5.6.0
* Java Message Service (JMS)
* Hypertext Markup Language Version 5 (HTML5)
* Cascading Style Sheets Version 3 (CSS3)
* JQuery 1.7.2
* Asynchronous JavaScript and XML (AJAX)
* JavaScript Object Notiation (JSON)
* Squid 3.1.20
* Android 2.3.3
* Struts Model-View-Controller (Struts MVC) 2
* Spring Inversion of Control (Spring IoC) 3.0
* Hibernate Object/Relation Mapping (Hibernate ORM) 3.3
* MySQL 5.5
