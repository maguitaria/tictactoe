package com.example.tictactoe.enumeration;

public enum GameState {
    WAITING_FOR_PLAYER("Waiting for player."),
    PLAYER1_TURN("Player 1, your turn!"),
    PLAYER2_TURN("Player 2, your turn!"),
    PLAYER1_WON("Player 1 won, congrats!"),
    PLAYER2_WON("Player 2 won, congrats!"),
    TIE("Tie.");

    String description;

    GameState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
