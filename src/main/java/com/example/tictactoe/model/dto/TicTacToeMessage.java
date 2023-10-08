package com.example.tictactoe.model.dto;

import java.util.List;

import com.example.tictactoe.enumeration.GameState;
import com.example.tictactoe.model.TicTacToe;

public class TicTacToeMessage implements Message {
private String type;
private String gameId;
private String player1;
private String player2;
private String winner;
private String turn;
private String content;
private String[][] board;
private int move;
private GameState gameState;
private String sender;
private List<String> gameHistory;   

    public TicTacToeMessage() {}

public TicTacToeMessage(TicTacToe game) {
    this.gameId = game.getGameId();
    this.player1 = game.getPlayer1();
    this.player2 = game.getPlayer2();
    this.winner = game.getWinner();
    this.turn = game.getTurn();
    this.board = game.getBoard();
    this.gameState = game.getGameState();
    this.gameHistory = game.getGameHistory();
}
/**Getters and setters */
public String getType() {
    return type;
}
public void setType(String type) {
    this.type = type;
}
public String getGameId() {
    return this.gameId;
}
public void setGameId(String gameId) {
    this.gameId = gameId;
}
public void setGameHistory(List<String> gameHistory ) {
    this.gameHistory = gameHistory;
}
public List<String> getGameHistory() {
 return this.gameHistory;
}

public String[][] getBoard() {
    return board;
}

public void setBoard(String[][] board) {
    this.board = board;
}

public String getPlayer1() {
    return player1;
}

public void setPLayer1(String player1) {
    this.player1 = player1;
}

public String getPlayer2() {
    return player2;
}

public void setPLayer2(String player2) {
    this.player2 = player2;
}

public String getWinner() {
    return winner;
}

// Record the winner of current game and update game history
public void setWinner(String winner) {
    this.winner = winner;
    gameHistory.add(winner);
}

public String getTurn() {
    return turn;
}

public void setTurn(String turn) {
    this.turn = turn;
}

public GameState getGameState() {
    return gameState;
}

public void setGameState(GameState gameState) {
    this.gameState = gameState;
}
// Private methods
public String getContent() {
    return content;
}
public void setContent(String content) {
    this.content = content;
}

public int getMove() {
    return move;
}

public void setMove(int move) {
    this.move = move;
}
public String getSender() {
    return sender;
}
public void setSender(String sender) {
    this.sender = sender;
}
}

