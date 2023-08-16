Reversi Game:

1. Introduction
   
  This project is about a board game called Reversi/Reversegam that can be played by two
  players (an AI player and a Human Player), designed using java Swing framework. The game board consists of an 8x8 grid
  where players take turns placing their colored tiles (black and white) strategically to flip
  their opponent's tiles and gain control of the board. The game continues until either the
  board is full or when both players consecutively pass their turns because no more valid
  moves are possible for both. The goal of the game is to have more of your tiles on the
  board at the end of the game.

2. Number of Players
   
  The game requires two players: an AI player uses black tiles ('B'), and a human player
  uses white tiles ('W'). Player 'W' starts the game by making the first move.

3. Win/Loss Conditions
   
  The objective of the game is to have the most tiles of your color on the board when the
  game ends. The game ends when one of the following conditions is met:
    ● The board is full, and no more moves are possible.
    ● Both players consecutively pass their turns.
   
4. Gameplay rules
   
  A. Initial Setup
  The game starts with an empty 8x8 board, except for the four central tiles, which
  are pre-filled as follows:

    B | W
   
    W | B
    
  B. Making a move

    ● Each player places a tile on the board in turn.
   
    ● A move is considered legal if a player sets the tile next to an opponent's
    tile in any direction (horizontally, vertically, or diagonally), sandwiching
    the opponent's tile(s) in that direction.
   
    ● As part of the move, the player must flip each of the opponent's tiles in the
    sandwiched row, column, or diagonal.
    
  C. Flipping opponent’s tiles

    ● When a player makes a move and flips the opponent's tiles, the flipped
    tiles change their color from 'W' to 'B' or vice versa.
   
    ● If multiple rows or columns of opponent tiles are sandwiched in one
    move, they all get flipped.
    Reversi Game
    
  D. Valid moves and passing

    ● If a player has no valid moves available, they must pass their turn to the
    opponent.
   
    ● If both players consecutively pass their turns, the game ends.
    
  E. Scoring

    ● The score is calculated based on the number of tiles each player has on the
    board at the end of the game.
   
    ● The player with the most tiles of their color wins the game.
   
    ● In the event of a tie, the game is declared a draw.
   
5. Gameplay examples
   
  These are the possible situations the player may encounter in the game
  
  Scenario 1: Valid Move
  
    Player 'B' places their tile on an empty cell adjacent to an opponent's tile, sandwiching the
    opponent's tile(s) in that direction. The sandwiched opponent's tiles change color to 'B'
    
  Scenario 2: No valid moves
  
    Player 'B' has no valid moves available. They must pass their turn, and Player 'W' gets to
    play
    
  Scenario 3: Consecutive passes
  
    Player 'B' passes his/her turn because of having no valid moves. Player 'W' also passes
    because of having no valid moves. As soon as both players have passed their turns,the
    game ends and scores will be calculated.
   
6. Conclusion
   
  Reversi/Reversegam is an engaging and challenging board game that requires strategic
  thinking and planning. By flipping the opponent's tiles, players aim to dominate the board
  and secure victory. The implementation of an AI player provides an enjoyable
  single-player experience.
