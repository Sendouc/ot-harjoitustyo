# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria:

runningdiaryapp.ui (vastaa tekstikäyttöliittymästä)

|

|

v

runningdiaryapp.domain (sovelluslogiikka)

|

|

v

runningdiaryapp.dao (tietojen pysyväistallennus H2-tietokantaan)

## Käyttöliittymä

Käyttöliittymä on toteuttu tekstikäyttöliittymänä:

```
Hello runner!

-----
1 = Add a new route to the database
2 = View routes in database
3 = Search for a route by name
4 = Record new run
5 = View runs
6 = Quit the program
```

Käyttöliittymästä vastaa oma luokkansa: _TUI_. Jokaista käyttäjän mahdollista valintaa vastaa oma metodinsa, joten käyttöliittymää on helppo laajentaa.

## Sovelluslogiikka

Reittejä ja lenkkejä kuvaavat kaksi luokkaa (huomaa alla olevasta kuvasta, että kaikkia kenttiä ei ole toteuttu vaan ne ovat lisäkehityksen varalle dokumentoitu):

<img src="https://raw.githubusercontent.com/Sendouc/ot-harjoitustyo/master/dokumentaatio/kuvat/rakenne.png">

_AppService_ vastaa kaikista käyttöliittymästä tulevista pyynnöistä. Näitä ovat mm. uusien reittien luominen, uusien lenkkien luominen ja näiden hakeminen.

## Tietojen pysyväistallennus

Reitit ja lenkit on tallennettu käyttämällä [H2-tietokantaa](https://www.h2database.com/html/main.html).

Luokat noudattavat Data Access Object -suunnittelumallia, joten ne on helposti vaihdettavissa toisenlaiseen tallennusmalliin.

Käyttäjä voi määrittää tietokannan nimen ympäristömuuttujaa käyttämällä.

## Päätoiminallisuuksia

**Reitin lisääminen**

<img src="https://raw.githubusercontent.com/Sendouc/ot-harjoitustyo/master/dokumentaatio/kuvat/reitin-lisaaminen.png">

Oikeastaan kaikki toiminnallisuudet on toteuttu samanlaista mallia käyttämällä mm.

- Reitin etsiminen
- Lenkin lisääminen
- Lenkkien selaaminen
- Lenkkien yhteispituuden hakeminen
