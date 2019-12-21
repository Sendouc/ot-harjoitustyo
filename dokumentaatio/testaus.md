# Testausdokumentti

Ohjelman testauksen pääpaino on DBDiaryDao luokan integraatiotesteissä. Yksittötestausta on hyödynnetty esim. Run-luokan konstruktorin testissä. Manuaalista testausta on myös tehty.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluslogiikka itsessään ei ole juurikaan testattu, sillä ohjelman pääpaino on selkeästi tietokannasta haettavan tiedon kanssa toimiminen ja mitään vähänkään monimutkaisempaa sovelluslogiikka ei tässä mielessä ole.

### DAO-luokat

DAO-luokkia testatessa tietokantana on käytetty [in-memory tietokantaa](http://www.h2database.com/html/features.html#in_memory_databases).

Testauksessa on pyritty tekemään integraatiotestejä ohjelman suorituksen kannalta olennaisista metodeista mm.

- Reittien lisääminen
- Reittien etsiminen
- Juoksujen pituuden summan hakeminen

### Testauskattavuus

Sovelluksen testauksen rivikattavuus on 80% ja haarautumakattavuus 81%

<img src="https://raw.githubusercontent.com/mluukkai/OtmTodoApp/master/dokumentaatio/kuvat/testauskattavuus.png" width="800">

Testausta olisi voinut parantaa erityisesti corner casejen kohdalla.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu käyttöohjetta mukailen Windows- (WSL), OSX- ja Linux-ympäristöissä. Testausta on tehty sekä tilanteissa, missä käyttäjä on määritellyt tietokannan nimen ja missä sitä ei ole tehty.

### Toiminnallisuudet

Kaikki määrittelydokumentin listaamat toteutetut toiminnallisuudet on käyty läpi. Testauksessa on pyritty ottamaan huomioon myös huonojen syötteiden antaminen.

## Sovellukseen jääneet laatuongelmat

Sovelluksessa ei ole annettu tarkkoja virheilmoituksia esimerkiksi, kun tietokantaan kirjoitus ei onnistu.
