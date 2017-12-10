# OE_17autumn_Microservice_Skills
Hősök tulajdonságai

Jersey (SkillsService és SkillsClientApp): <br/>
E48AH0 – Szabó Gergely Krisztián.<br/>
Költözés https://github.com/HomeWorksJava/OE_17autumn_Microservice_Combat repóról ide. <br/>
Weboldal címe: http://18.217.2.245/ <br/>
API Elérése: http://18.217.2.245:8080/myapp/application.wadl <br/>


DROPWIZARD:

Erőteljesen az előzőleg elkészült verzió mintájára Dropwizard környezetben.<br/>
https://github.com/HomeWorksJava/OE_17autumn_Microservice_Combat<br/>
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
example: http://80.211.148.10:8080/skills/skillsofspecies?species=["dwarf","human"]&percentage=["90","10"]<br/>
Hybrid faj kombinációk alapján adja vissza az elérhető skilleket.<br/>

SpringBoot:
IXYZII - Rigler Nándor Krisztián
Azért készült SpringBootban végül mert nem tudtam a Wildfly Swarm-ot sehogy sem működésre bírni és sok utánaolvasás után úgy láttam ezzel egyszerűbb lehet és az is volt.
Valamiért nem tudok ide feltölteni, nincs hozzá push jogom azt írja és ip-t sem kaptam, legalábbis a moodle-ön nincs fent, úgyhogy jövőhét szerdán(12.13.), beviszem személyesen a feladatomat.
