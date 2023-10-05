package com.example.tictactoe.model.dto;

import java.util.UUID;

import io.scalajs.dom.html.phaser.GameState;

import java.util.Objects;

public class TicTacToe {
    /**
     * Class reprsenting a Tic Tac Toe Game
     * 
     * @author Mariia Glushenkova
     */

    private String gameId;
    private String[][] board;
    private String player1;
    private String player2;
    private String winner;
    private String turn;
    private GameState gameState;

    public  TicTacToe(String player1, String player2) {
            this.gameId = UUID.randomUUID().toString();
            this.player1 = player1;
            this.player2 = player2;
            this.turn = player1; //NOTE - - Should depend on previous win/lose
            this.board = new String [3][3];
            for (int = 0; i < 3; i++ ) {
                for (int j = 0; j< 3; j++) {
                    this.board[i][j] = " ";
                }
            }
        }
            gameState = GameState.WAITING_FOR_PLAYER; //NOTE -  Implement Game STate
}
