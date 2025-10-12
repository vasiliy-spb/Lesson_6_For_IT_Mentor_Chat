ОХОТА НА ВУМПУСА
Легендарная игра, про которую ты скорее всего не слышал.

ТЕМА: Декомпозиция ООП
Технологии: фабрика, билдер, связные списки.

ЗАДАНИЕ
+ Охотник ходит по лабиринту и охотится на Вумпуса
+ Умрет или Вумпус, или охотник
+ Карта мира Вумпуса построена по принципу додекаэдра  
  То есть, каждая комната соедининена с тремя другими

[Краткие правила](https://ru.wikipedia.org/wiki/Hunt_the_Wumpus)

Уровень сложности: 2 проект родмапа.


Номера комнат идут подряд

*********************
*  HUNT THE WUMPUS  *
*********************
//ход 1
BATS NEARBY! <- рядом летучие мыши
You are in room 1
Tunnels lead to [2, 5, 6]
Arrows left 2
Shoot or move (s-m)?
m
Where to?
6

//ход 2
You are in room 6
Tunnels lead to [1, 5, 7]
Arrows left 2
Shoot or move (s-m)?
m
Where to?
7

//ход 3
BATS NEARBY! <- рядом летучие мыши
You are in room 7
Tunnels lead to [2, 6, 8]
Arrows left 2
Shoot or move (s-m)?
m
Where to?
2
ZAP--SUPER BAT SNATCH! ELSEWHEREVILLE FOR YOU! <- зашли в комнату, там летучие мыши, они перенесли в случайную комнату

//ход 4
I SMELL A WUMPUS <- в одной из соседних комнат сидит вумпус
You are in room 5
Tunnels lead to [1, 4, 6]
Arrows left 2
Shoot or move (s-m)?
s  <- выбрали действие: стрелять
Where to?
4  <- выстрел в комнату 4
Shoot!
AHA! YOU GOT THE WUMPUS!  <- попали, подстрелили вумпуса
YOU WON! HEE HEE HEE - THE WUMPUS'LL GET YOU NEXT TIME!!
Game over