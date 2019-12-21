# Running Diary App

Sovelluksen avulla juoksija voi pitää kirjaa lenkeistään. Sovellus antaa käyttäjän vertailla tuloksiaan omiin entisiin lenkkeihin. Palautetta annetaan käyttäjälle sen mukaan, miten paljon lenkkejä on viime aikoina kertynyt.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Sendouc/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Sendouc/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/Sendouc/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/Sendouc/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

## Releaset

[Viikko 5](https://github.com/Sendouc/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/Sendouc/ot-harjoitustyo/releases/tag/viikko6)

## Komentorivitoiminnot

### Jarin suorittaminen

Jar-tiedoston nimi on `RunningDiaryApp-1.0-SNAPSHOT.jar` jos olet generoinut sen itse tai `runningdiaryapp.jar` jos se on ladattu GitHub releaseista.

```
java -jar runningdiaryapp.jar
```

Tietokannan nimi voidaan määrittää ympäristömuuttujan avulla ohjelman suorittamisen yhteydessä:

`DB_NAME=myruns java -jar runningdiaryapp.jar`

jolloin siis tietokannan nimeksi tulisi `myruns.db`. Jos tietokannan nimen määrittävää ympäristömuuttujaa ei anneta käytetään oletusarvoista nimeä `runningdiaryapp.db`

### Jarin generointi

```
cd Running-Diary-App/
mvn package
```

Jar-tiedosto löytyy `target` kansiosta.

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Ohjelman kääntäminen ja ajaminen

Ohjelma suoritetaan komennolla:

```
mvn compile exec:java -Dexec.mainClass=runningdiaryapp.Main
```

kun ollaan kansiossa `Running-Diary-App`

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_
