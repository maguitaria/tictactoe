package com.example.tictactoe.model;

import java.util.UUID;

import com.example.tictactoe.enumeration.GameState;

import java.util.ArrayList;
import java.util.List;
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

    // Create a list to store game history
    private static List<String> gameHistory = new ArrayList<>();

    public  TicTacToe(String player1, String player2) {
            this.gameId = UUID.randomUUID().toString();
            this.player1 = player1;
            this.player2 = player2;
            this.turn = determineStartingPlayer(); //NOTE - assume Player X starts first game
            this.board = new String [3][3];
    
         // Clear board
            for (int i = 0; i < 3; i++ ) {
                for (int j = 0; j< 3; j++) {
                    this.board[i][j] = " ";
                }
            }
        gameState= GameState.WAITING_FOR_PLAYER; // NOTE - Implement Game STate
    }

    public static List<String> getGameHistory() {
        return gameHistory;
    }

    // Method to determine starting player based on game history


    public static String determineStartingPlayer() {
        // Get the winner from game history
        String lastWinner = gameHistory.get(gameHistory.size() - 1);
        // If there is no game history/ there was a draw, start with Player X
        if (gameHistory.isEmpty() || lastWinner.equals("Draw")) {
            return "Player X";
        }

        // Set the starting player based on the last winner
        return lastWinner.equals("Player X") ? "Player O" : "Player X";

    }


    /**
     * Makes a move in specified position on the board
     * 
     * @param player name of the player making the move
     * @param move   the position of the move
     */
    public void makeMove(String player, int move) {
        int row = move / 3;
        int col = move % 3;
        if (Objects.equals(board[row][col], " ")) {
            board[row][col] = Objects.equals(player, player1) ? "X" : "0";
            turn = player.equals(player1) ? player2 : player1;
            checkWinner();
            updateGameState();
        }
    }



    /**!SECTION
         * Check if there is a winner; If a winner combination is find,
         *   
         * 
         the winner is set to the corresponding player.
         * @category TicTacToe Game logic
         */
        private void checkWinner() {
            /**!SECTION
                     * In the first for loop, it checks each row to see 
                     * if all three cells in that row have the same value (either "X" or "O").
                     */
            for (int i = 0; i < 3; i++) {
            if (Objects.equals(board[i][0], board[i][1])
             && Objects.equals(board[i][0] , board[i][2])) {
                 //  If it finds a row where all three cells have the same value and that value is not
        // a space (" "), it sets the winner to the player whose symbol matches the value in the
        // row (either player1 or player2).
                if (!Objects.equals(board[i][0], " ")) {
                    setWinner(Objects.equals(board[i][0], player1) ? player1 : player2);
                    return;
                    
                }
            }
        }
    //   It does a similar check for columns in the second for loop. 
    // It checks each column to see if all three cells in that column
    // have the same value. 
 for (int i = 0; i < 3; i++) {
            if (Objects.equals(board[0][i], board[1][i]) 
            && Objects.equals(board[0][i] , board[2][i])) {
                if (!Objects.equals(board[0][i], " ")) {
                    setWinner(Objects.equals(board[0][i], player1) ? player1 : player2);
                    return;
                    
                }
            }
        }

    // Finally, it checks the two diagonals on the board
    // to see if all three cells in either diagonal have the same value.
    if (Objects.equals(board[0][0], board[1][1]) && 
    Objects.equals(board[0][0], board[2][2])) {
        if (!Objects.equals(board[0][0], " ")) {
            setWinner(Objects.equals(board[0][0], player1) ? player1 : player2);
            return;
        }
    }
        }
        
        /**!SECTION
         * Updates game state based on current state of a game.
         */
        private void updateGameState() {
            if (winner != null) {
                gameState = winner.equals(player1) ? GameState.PLAYER1_WON : GameState.PLAYER2_WON;
            } else if (isBoardFull()) {
                gameState = GameState.TIE;
            } else {
                gameState = turn.equals(player1) ? GameState.PLAYER1_TURN : GameState.PLAYER2_TURN;
            }
        }
        /**!CHeck if the board is full 
         * @return true if the board is full, false otherwise
         * 
        */
        private boolean isBoardFull() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Objects.equals(board[i][j]," ")) {
                        return false;
                    }
                }
            }
        return true;
        }
        /**!SECTION
         * Check if the game is over.
         * @return true if the game is over, false otherwise
         */
        public boolean isGameOver() {
            return winner != null || isBoardFull();
        }
        /**!SECTION
         * Getters and Setters
         */
        public String getGameId() {
            return gameId;
        }
        public void setGameId(String gameId) {
             this.gameId = gameId;
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

}    

