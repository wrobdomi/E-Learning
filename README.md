# Table of Contents 
- [Short Description](#short-description)
- [Database Project](#database-project)
- [Application Demo](#application-demo)
  * [Main features](#main-features)
  * [Add or Remove Quiz](#add-or-remove-quiz)
  * [Add Question](#add-question)
- [Project Structure](#project-structure)
- [Technologies](#technologies)
- [Coworkers](#coworkers)


<!-- toc -->


## Short Description
This little project was created during Java classes at AGH-UST. The software enables user to add or remove quizzes 
and questions. Every question can be multiple choice. After adding questions, quizzes can be solved in order to consolidate 
knowledge. 

## Database Project

* Conceptual model
![conc](https://user-images.githubusercontent.com/37666186/52165558-bb75fb00-2702-11e9-9fdd-7ec186c467db.JPG)

## Application Demo

### Main features
![quiz](https://user-images.githubusercontent.com/37666186/52165625-72727680-2703-11e9-8fa1-868ea33a263f.gif)

### Add or Remove Quiz
![addremove](https://user-images.githubusercontent.com/37666186/52165658-ec0a6480-2703-11e9-8943-b42dc538ee3e.gif)

### Add Question
![addques](https://user-images.githubusercontent.com/37666186/52165777-3213f800-2705-11e9-8606-80c4a34f658b.gif)


## Project Structure

* E-Learning
  * src/main/java
    + controllers
      * AddingQuestionController
      * LoginController
      * PlatformController
    + database
      * DatabaseService
    + entities
      * Answer
      * Question
      * Quizz
      * User
    + enums
      * QuizMenuEnum
      * QuizMenuPicturesEnum
      * QuizOptions
    + main
      * Main
    + stylesheets
      * CSS files
    + views
      * .fxml files

## Technologies
* Java 8
* JavaFX
* CSS

## Coworkers 
* https://github.com/pineappleexp



