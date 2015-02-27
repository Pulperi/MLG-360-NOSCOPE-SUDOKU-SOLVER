## SudokuWorldSaga (work name) by the Crazy Cat Game Studio

![luokkakaavio](https://raw.githubusercontent.com/Pulperi/MLG-360-NOSCOPE-SUDOKU-SOLVER/master/dokumentointi/diagrams/luokkakaavio_v4.png)


#### Ohjelman luokkahierarkia

Ohjelma koostuu kolmesta kokonaisuudesta. Graafiseen käyttöliittymään liittyvät luokat, ns. dataluokat jotka
hallitsevat sudokudataa ja apuluokat jotka sisältävät eri sudokuihin liittyviä algoritmeja.

SudokuWorldSaga, eli pää käyttöliittymäluokka, ja siihen liittyvät käyttöliittymäkomponentit SudokuPanel,
SudokuPanelSubset ja SudokuCell muodostavat käyttöliittymän. SudokuWorldSaga -luokka toimii yläluokkana,
joka hallitsee ohjelmassa tapahtuvia toimintoja käyttäen apunaan apuluokkia SudokuSolver, SudokuGenerator ja
FileManager. Näiden apuluokkien julkisia metodeja hyödyntämällä SudokuWorldSaga -yläluokka voi toteuttaa
käyttäjän haluamia toimintoja.

Ohjelman ydin on Sudoku luokka joka toimii rajapintana SudokuData -luokan ja muiden sudokudataa hyödyntävien
luokkien välillä. Sudoku luokkaan liittyvä SudokuData on käytännössä vain 'älykäs' int matriisi, joka hallitsee
sudokun numeroarvoja. SudokuShuffler on apuluokka, jota SudokuData luokka hyödyntää generoitaessa satunnaisia
sudokuja.
