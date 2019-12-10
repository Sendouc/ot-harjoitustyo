# Käyttöohje

Lataa uusin versio ohjelmasta [runningdiaryapp.jar](https://github.com/Sendouc/ot-harjoitustyo/releases)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar runningdiaryapp.jar
```

## Uuden reitin lisääminen

Ennen varsinaisen lenkin lisäämistä tulee lisätä ainakin yksi lenkkireitti:

```
Your choice?
1
What is the route called?
Lampireitti
How long is the route in meters?
7500
Lampireitti (7500 meters) (yes/no)?
yes
New route called Lampireitti successfully created!
```

## Uuden lenkin lisääminen

Kun ainakin yksi reitti on lisätty voidaan lisätä lenkki:

```
Your choice?
4
[1] Lampireitti(7500 meters)
Which route you ran? (-1 to quit)
1
New run successfully created!
```

## Reittien ja lenkkien listaaminen

Reittejä ja lenkkejä voidaan selailla:

```
Your choice?
2
Routes currently registered:

Name: Lampireitti Length: 7500 meters
-----

Your choice?
5
Date: 2019-12-10 Length: 7500 meters
-----

Your choice?
3
What route do you want to search for?
lampi
Following routes match the name given:
Name: Lampireitti Length: 7500 meters
```
