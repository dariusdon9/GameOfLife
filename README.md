# Game of Life

## High-level diagram

![image](diagram.svg)

---

Our approach to the Game of Life project implies a JavaFX interface that will be similar to a chess board but bigger in size. The whole point of the game is to create a simulation of how cells live, feed, reproduce and die. 

We will implement three different types of gameplay:

- Automatic: the number of sexed and asexual cells is random, number of food units will be selected by the user
- Semi-automatic: the user inputs the number of food units, sexed and asexual cells and they will be placed randomly
- User-driven: the user inputs the number of food units, sexed and asexual cells and also selects where to place them on the board

When the game is launched the user will be prompted to insert the number of sexed and asexual cells. After selecting the number of cells the user will start the game. When the game starts the cells will be placed by the user, or randomly, across the board along with the food units that are to be consumed by them.

The representation of the different entities in the game is as follows:

- Game board: black or white grid
- Food: green square
- Sexed cells: red square
- Asexed cells: blue square
- Dead cell: yellow square (turns into food)

The game can be paused and started at any point in time with the simple press of a button. When the game is paused the state of the game is saved and the cells will stop their activity. When the game is started again it will resume its previous state and continue from that point on.

Methods for calculating the high score based on each type of gameplay will be implemented. The high score will be stored in a database(SQL), along with the state of each cell during a certain time period.

The game ends when all the cells die or when the user stops it.

RabbitMQ will be used for dealing with the different kinds of events that will be encountered during the game.


## Developed by:
### - Darius Don
### - Dragos Plitea
### - Rares Groza