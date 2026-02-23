---
layout: single
title: "Projects"
permalink: /projects/cs-320/
---

Initial Project: CS-320 Project Two

Enhanced Project: Databases

<a href="https://github.com/plastine/plastine.github.io/tree/main/CS-499_refactor_CS_320" class ="btn btn--info" target="_blank" rel="noopener">
  View CS-320 Enhanced Project (GitHub Folder)
</a>

Context:This project initially started as as a set of J-Unit tests that were created for both Java classes as well as their counterpart services. The three types of classes being appointments, contacts, as well as tasks. Each of these had CRUD functionality, however there was no database attached to the application. 

For the enhancement, I created a new set of J-Unit test cases. I also wired a local database(mySQL) with an XAMPP control panel. Although functional, the database did not serve well for testing. This prompted me to convert the application into a maven project. I then decided to utilize mockito for database testing, as I felt this thematically fit with the initial application while also serving the purpose of an enhancment. I created eight new test cases through JUNIT, and boosted test coverage up to nearly 90%, which was not initially required for the course.

Course Outcome Accomplished: develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources

Justification: I believe that I developed a security mindset by implementing the most proactive approach, that being testing. Testing is important, because developers should not wait for faults or defects after their application meets users as a live service. While users may be useful in terms of feedback, their PPI should not be used as guinea pigs for avoidable exploits. Preventing SQL injections, forcing users to engage with certain inputs, and testing for extremes are some of the ways I anticipated real world scenarios that could very well cause security concerns in other areas. 


<a href="https://docs.google.com/document/d/1AuHidNngWN2VrNPyHYdgJTrG0KJP1kWdB-wxdKJyghQ/edit?usp=sharing" class ="btn btn--info" target="_blank" rel="noopener">
  View CS-320 narrative
</a>



