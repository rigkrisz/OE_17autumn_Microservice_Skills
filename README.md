# OE_17autumn_Microservice_Skills
Hősök tulajdonsgai

Erőteljesen az előzőleg elkészült verzíó mintájára Dropwizard környezetben.

Weboldal címe: http://80.211.148.10/

Api útvonalak:

GET http://80.211.148.10:8080/skills/all
minden skill lekérdezése

GET http://80.211.148.10:8080/skills/{id}
id azonosítóval ellátott képesség lekérdezése

GET http://80.211.148.10:8080/skills/{id}/{count}
id azonosítóval ellátott képesség és az utána következő count db lekérdezése

DELETE http://80.211.148.10:8080/skills/{id}
id azonosítóval ellátott képesség törlése

POST http://80.211.148.10:8080/skills/new
új skill felvitele
szükséges Form Paraméterek:
name - String
description - String
species - String
hybridPercentRequirement - Integer

PUT http://80.211.148.10:8080/skills/{id}
id azonosítóval ellátott képesség módosítása
szükséges Form Paraméterek:
name - String
description - String
species - String
hybridPercentRequirement - Integer

GET http://80.211.148.10:8080/skills/skillsofspecies
query => species=["faj1","faj2"]&percentage=["faj1%","faj2%"]
example: http://80.211.148.10:8080/skills/skillsofspecies?species=[%22dwarf%22%2C%22human%22]&percentage=[%2290%22%2C%2210%22]
Hybrid faj kombinációk alapján adja vissza az elérhető skilleket.
