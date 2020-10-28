# EOLT  Web Application

## Description 
*Target is to replace paper version of ReleaseForProduction document  to dataBase with APN blocking feature.*

### Tasks
- [x] As a User (Tester Role) I would like to Create/Read/Update/Delete new EOLT and variants related to EOLT  in a database.
- [x] As a User (Quality Role) I would like to Release variants related to EOLT to increase production performance. 
- [x] As a User (Tester/Quality Role) I would like to block variant related to EOLT to increase production performance.
- [x] As a User I would like to have automatic way of  blocking variant to protect Customer against production on not released variants.
- [x] As a User I would like to have possibility to see on which EOLTs I can produce the variant.     
- [x] As a User I would like to see history of my Variant's status changes  to follow changes.
- [x] As a external User I would like to have access via API to data to be able prepare external software
    - [x]  REST API   :  /api/eolt                 - list of all testers
    - [x]  REST API   :  /api/eolt/{eoltName}      - tester's details 
    - [x]  REST API   :  /api/{eoltName}/variants  - variants list according tester
    - [x]  REST API   :  /api/eolts/{variant}"     - on which testers  the variant was prepared
 
### Used Technologies
- [x] SpringBoot, SpringMVC, SpringData,SpringSecurity
- [x] JUnit, AssertJ
- [x] DataBase : MySQL ,Hibernate(JPA)
- [x] Thymeleaf
- [x] REST Api
- [x] Locale Language
- [x] Socket 
- [x] Docker Compose ( linked containers app + mysql)
- [x] Deployed to external server
- [ ] Implementing a Custom Spring AOP Annotation -> https://www.baeldung.com/spring-aop-annotation
- [ ] Selenium  ->https://www.javatpoint.com/selenium-tutorial

### Issues 
- [x] What's happen if type is in web but not in EOLT  : my message is = ADDVARIANT|variant=28112222|station=VIDEO_EOLT_F|status=PASS--->>> FIS response is = FAIL Variant not exists in FIS
     ADDVARIANT|variant=28112222|station=VIDEO_EOLT_F|status=PASSFAIL Variant not exists in FIS  
     ------> I have throw exception which was intercepted by HandlerException
- [x] Blocking tester -> checked on production's enviroment 
   
     
     