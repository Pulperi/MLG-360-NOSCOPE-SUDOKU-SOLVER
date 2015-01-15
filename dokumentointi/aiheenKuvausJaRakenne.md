## SudokuWorldSaga (work name) 

Projektin tarkoitus on lähteä työstämään sudokupeliä/-ohjelmaa. Projektin lopputulos tulee olemaan "kaikenkattava" sudokuohjelma, jolla pystyy ratkaisemaan sudokuita, generoimaan uusia sudokulautoja ja pelaamaan sudokuita (i.e. ratkaisemaan niitä käsin). 

Alla napkin draftiä ohjelman toiminnallisuudesta ja rakenteesta... 

![KayttoliittymaDraft](https://github.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/edit/master/dokumentointi/kayttoliittyma-draft.png)

![PerustoiminnallisuuskaavioDraft](https://github.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/edit/master/dokumentointi/perustoiminnalisuuskaavio-draft.png)

![SudokuTaulukkoRakenneDraft](https://github.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/edit/master/dokumentointi/sudokutaulukkorakenne.png)

Toimintojen lisääminen tapahtuu vaiheittain ja alussa tarkoitus on luoda perustoiminto eli sudokuiden syöttäminen ohjelmaan, joko käsin tai tiedostosta. Peruslogiikan valmistuttua voidaan luoda sen päälle graafinen käyttöliittymä.
Vaiheissa edetään kurssiin käytettävissä olevan ajan puitteissa.

#### Ensimmäinen vaihe 

* Toteutetaan sudokuratkaisija, jolla voi ratkaista siihen syötettyjä sudokuita.

**Käyttäjän toiminnot:**
* Sudokun syöttäminen manuaalisesti graafisen käyttöliittymän kautta
* Sudokun lataaminen tiedostosta
* Syötetyn sudokun ratkaiseminen
* ...

#### Toinen vaihe

* Lisätään mahdollisuus luoda uusia sudokuita, joilla uniikki ratkaisu.

**Käyttäjän lisätoiminnot:**
* Sudokujen generointi (mahdollisuus vaikuttaa generoitujen sudokuiden vaikeuteen)
* Generoitujen sudokuiden tallennus tiedostoon

#### Kolmas vaihe

* Mahdollisuus pelata ladattuja/generoituja sudokuita

