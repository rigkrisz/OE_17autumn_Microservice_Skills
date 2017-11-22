# OE_17autumn_Microservice_Skills
Hősök tulajdonsgai

Erőteljesen az előzőleg elkészült verzíó mintájára Dropwizard környezetben.<br/>
<br/>
Weboldal címe: http://80.211.148.10/<br/>
<br/>
Api útvonalak:<br/>
<br/>
GET http://80.211.148.10:8080/skills/all<br/>
minden skill lekérdezése<br/>
<br/>
GET http://80.211.148.10:8080/skills/{id}<br/>
id azonosítóval ellátott képesség lekérdezése<br/>
<br/>
GET http://80.211.148.10:8080/skills/{id}/{count}<br/>
id azonosítóval ellátott képesség és az utána következő count db lekérdezése<br/>
<br/>
DELETE http://80.211.148.10:8080/skills/{id}<br/>
id azonosítóval ellátott képesség törlése<br/>
<br/>
POST http://80.211.148.10:8080/skills/new<br/>
új skill felvitele<br/>
szükséges Form Paraméterek:<br/>
name - String<br/>
description - String<br/>
species - String<br/>
hybridPercentRequirement - Integer<br/>
<br/>
PUT http://80.211.148.10:8080/skills/{id}<br/>
id azonosítóval ellátott képesség módosítása<br/>
szükséges Form Paraméterek:</br>
name - String<br/>
description - String<br/>
species - String<br/>
hybridPercentRequirement - Integer<br/>
<br/>
GET http://80.211.148.10:8080/skills/skillsofspecies<br/>
query => species=["faj1","faj2"]&percentage=["faj1%","faj2%"]<br/>
example: http://80.211.148.10:8080/skills/skillsofspecies?species=[%22dwarf%22%2C%22human%22]&percentage=[%2290%22%2C%2210%22]<br/>
Hybrid faj kombinációk alapján adja vissza az elérhető skilleket.<br/>
