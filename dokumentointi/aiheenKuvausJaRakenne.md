## SudokuWorldSaga (work name)

![logo](https://raw.githubusercontent.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/master/dokumentointi/art.png)

SudokuWorldSaga on projektin puitteissa kyhätty sudokupeli. Alunperin projektia lähdettiin kehittämään ihan sudokuratkaisijana.
Kuitenkin projektin edetessä ja eri toimintojen määrän kasvaessa todettiin että 'pelimoodin' lisääminen ei olisikaan mikään iso homma.

Pelissä voi täytellä sudokuja käsin tai ratkaista niitä ratkaisija-algoritmilla.
Sudokujen lataus peliin tapahtuu joko automaattisen generaattorin kautta, tiedostosta tai käsin.

#### Tähän mennessä toteutetut toiminnot

* Graafinen käyttöliittymä, jolla voi ladata ruudulle sudokuita ja ratkaista niitä
** Graafisen käyttöliittymän käyttäjän toiminnot:**
  * Lataa tyhjä sudoku
  * Lataa uusi generoitu sudoku (mahdollisuus myös vaikuttaa generoitujen sudokuiden vaikeuteen)
  * Sudokun lataaminen tiedostosta
  * Ladatun sudokun ratkaiseminen automaattisesti ratkaisijalla
  * Ladatun sudokun ratkaiseminen käsin syöttämällä vastauksia graafisen käyttöliittymän avulla.
** Graafisen käyttöliittymän ominaisuuksia:**
  * Käyttöliittymään rakennettu sudokulaudan ruutujen värikoodaus:
  ** valkoiset: sudokun alkuperäisarvot
  ** keltaiset: käyttäjän syöttämät arvot
  ** vihreät: ratkaisijan täyttämät arvot



![GraphicalUserInterFace_alpha](https://raw.githubusercontent.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/master/dokumentointi/GraphicalUserInterFace_alpha.png)

![GameStateLoop](https://raw.githubusercontent.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/master/dokumentointi/diagrams/GameStateLoop.png)

![SudokuGeneratorStateChart](https://raw.githubusercontent.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/master/dokumentointi/diagrams/SudokuGeneratorStateChart.png)

![SudokuSolvingAlgorithm](https://raw.githubusercontent.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/master/dokumentointi/diagrams/SudokuSolvingAlgorithm.png)

![luokkakaavio_v2.png](https://raw.githubusercontent.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/master/dokumentointi/diagrams/luokkakaavio_v2.png.png)

![SudokuTaulukkoRakenneDraft](https://raw.githubusercontent.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/master/dokumentointi/sudokutaulukkorakenne.png)
