# Task creation specification

## Task creation BDD test

Мы создадим новую задачу, назначим её пользователю и добавим её в проект.
Затем проверим, что задача создана корректно. Будем использовать библиотеку
 Masquerade.

* Open application in the browser
* Log in as user with "dev1" username and "dev1" password
* Open the task browser
* Open the task editor
* Fill form fields with following values: name is "BDD testing", start date is "03/11/2021", estimated efforts are "10", project is "Jmix Education"
* Save new task
* Make sure the new task with "BDD testing" name is added to tasks table