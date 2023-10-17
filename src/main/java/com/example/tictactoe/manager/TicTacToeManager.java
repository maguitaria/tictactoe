package com.example.tictactoe.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.tictactoe.enumeration.GameState;
import com.example.tictactoe.model.TicTacToe;

  /**
    * Manager class for the Tic-tac-toe games.
    Handles adding and removing players from games and storing and retrieving 
    current games.
    @author Mariia Glushenkova
    */ 

public class TicTacToeManager {
 
    /**  Map of active games wth keyID */
    private final Map <String,TicTacToe> games;
   /**  Map of players waiting to join a Tic-Tac-Toe game, 
    * players name as a key
   */ 
    protected final Map <String, String> waitingPlayers;

    public TicTacToeManager() {
        games = new ConcurrentHashMap<>();
        waitingPlayers = new ConcurrentHashMap<>();
    }
/**  Attempts to add  player ot creates a new game function
 * */
public synchronized TicTacToe joinGame(String player) {
    if (games.values().stream().anyMatch(game -> game.getPlayer1().equals(player)
            || (game.getPlayer2() != null && game.getPlayer2().equals(player)))) {
        return games.values().stream().filter(game -> game.getPlayer1().equals(player) ||
                game.getPlayer2().equals(player)).findFirst().get();
    }

    for (TicTacToe game : games.values()) {
        if (game.getPlayer1() != null && game.getPlayer2() == null) {
            game.setPlayer2(player);
            game.setGameState(GameState.PLAYER1_TURN);
            return game;
        }
    }

    TicTacToe game = new TicTacToe(player, null);
    games.put(game.getGameId(), game);
    waitingPlayers.put(player, game.getGameId());
    return game;
}
/** Removes a player from Tic tac toe game. IF there was only one player in a game, this game history is removed. */
public synchronized TicTacToe leaveGame(String player) {
    String gameId = getGameByPlayer(player) != null ? getGameByPlayer(player).getGameId() : null;
    if (gameId != null) {
        waitingPlayers.remove(player);
        TicTacToe game = games.get(gameId);
        if (player.equals(game.getPlayer1())) {
            if (game.getPlayer2() != null) {
                game.setPlayer1(game.getPlayer2());
                game.setPlayer2(null);
                game.setGameState(GameState.WAITING_FOR_PLAYER);
                System.out.println("Game is over. Thank you for playing. New game began automatically");
                game.setBoard(new String[3][3]);
                waitingPlayers.put(game.getPlayer1(), game.getGameId());
            } else {
                games.remove(gameId);
                return null;
            }

        } else if (player.equals(game.getPlayer2())) {
            game.setPlayer2(null);
            game.setGameState(GameState.WAITING_FOR_PLAYER);
            game.setBoard(new String[3][3]);
            waitingPlayers.put(game.getPlayer1(), game.getGameId());
        }
        return game;
    }
    return null;
}
    public Map<String,TicTacToe> getGames() {
        return this.games;
    }
/**
 * Return the TicTacToe game of given gameId
 * @param gameId
 * @return game
 */
    public  TicTacToe getGame(String gameId) {
        return games.get(gameId);
    };

    /**
     * Return the TicTacToe game of given playerId
     * 
     * @param player
     * @return game
     */
 public TicTacToe getGameByPlayer(String player) {
return games.values().stream().filter( game -> game.getPlayer1().equals(player) ||
(game.getPlayer2() != null &&
game.getPlayer2().equals(player))).findFirst().orElse(null);
 } 
 /**
  * Removes the Tic tac toe game with given game id
  * @param gameIDof the game to remove
  */
public void removeGame(String gameId) {
    games.remove(gameId);
}

    public Map<String,String> getWaitingPlayers() {
        return this.waitingPlayers;
    }



}
