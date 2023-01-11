## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Overview

This project was done following along from a tutorial from https://copyassignment.com/employee-management-system-project-in-java/. My goal with this project was to get an idea of how a GUI is typically designed and implemented in Java so that I can write my next project independently without relying on the source code.

The project is an employee management system that uses Java, Swing, and MySQL to login and perform CRUD operations on employees.

## Setup

git clone

To implement this program you must first create a database:
`create database ems;`
Select the database:
`use ems;`
Create the employee table:
`create table employee (
  id int primary key,
  name varchar(25),
  gender varchar(10),
  phoneNum varchar(13),
  email varchar(25),
  designation varchar(20),
  salary double
);`
Create the admin table:
`create table admin (
  username varchar(25) primary key,
  password varchar(25)
);`
And insert some values into the admin table for login:
`insert into admin values
("admin","admin123"),
("admin2","0000");`

Then run the program.

## Takeaways

Questions I had going into this project were:

- Should I create a new page everytime the user renders that view? Or should I manipulate a single page like you could with Javascript?
- What should the file structure be like?
- How do I work with layouts in Java/Swing?
- What does good design look like in terms of Java GUI?

What I've learned:

- How to implement CRUD operations with MySQL/Java using a Swing GUI.
- How to design and structure a GUI in terms of code design and UX.
- How to think of a GUI in terms of Java instead of in terms of Javascript/DOM manipulation.
