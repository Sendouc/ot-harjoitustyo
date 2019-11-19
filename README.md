# Running Diary App

Sovelluksen avulla juoksija voi pitää kirjaa lenkeistään. Sovellus antaa käyttäjän vertailla tuloksiaan omiin entisiin lenkkeihin. Palautetta annetaan käyttäjälle sen mukaan, miten paljon lenkkejä on viime aikoina kertynyt.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Sendouc/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/Sendouc/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

### Ohjelman ajaminen

Ohjelma suoritetaan komennolla:

```
mvn compile exec:java -Dexec.mainClass=runningdiaryapp.Main
```

kun ollaan kansiossa `Running-Diary-App`

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
