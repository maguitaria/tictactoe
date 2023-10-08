package com.example.tictactoe.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.tictactoe.model.TicTacToe;
import com.example.tictactoe.enumeration.GameState;
import com.example.tictactoe.model.dto.JoinMessage;
import com.example.tictactoe.model.dto.PlayerMessage;
import com.example.tictactoe.model.dto.TicTacToeMessage;
import com.example.tictactoe.manager.TicTacToeManager;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public class MessageController {
    /**
     * Template for sending essages to clients through the messagebroker.
     */
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    /**
     * !SECTION
     * Manager for Tic-Tac-Toe games.
     */
    private final TicTacToeManager ticTacToeManager = new TicTacToeManager();

    /**
     * !SECTION
     * Handles a request between two players. If a game is available anf the player
     * has successfully joined
     * the game room, HTTP response is sended.
     * If error, HTTP request sends an error
     */
    @MessageMapping("/game.join")
    @SendTo("/topic/game.state")
    public Object joinGame(@Payload JoinMessage message, SimpMessageHeaderAccessor headerAccessor) {
        TicTacToe game = ticTacToeManager.joinGame(message.getPlayer());
        if (game == null) {
            TicTacToeMessage errorMessage = new TicTacToeMessage();
            errorMessage.setType("error");
            errorMessage.setContent("There was en error in joining game. Please, try again...");
            return errorMessage;
        }
        headerAccessor.getSessionAttributes().put("gameId", game.getGameId());
        headerAccessor.getSessionAttributes().put("gameId", message.getPlayer());
        TicTacToeMessage gameMessage = gameToMessage(game);
        gameMessage.setType("game.joined");
        return gameMessage;
    }

    /**
     * Handles a request from a client to leave a Tic-Tac-Toe game.
     * If the player is succussgully removed from the game, a message is sent to
     * sender
     * of the game's topic indicating that the player has left.
     * 
     * 
     * @param message the message from the client containing the player's name
     * 
     */
    @MessageMapping("/game.move")
    public void makeMove(@Payload TicTacToeMessage message) {
        String player = message.getSender();
        String gameId = message.getGameId();
        int move = message.getMove();
        TicTacToe game = ticTacToeManager.getGame(gameId);

    }

    private TicTacToeMessage gameToMessage(TicTacToe game) {
        TicTacToeMessage message = new TicTacToeMessage();
        message.setGameId(game.getGameId());
        message.setPLayer1(game.getPlayer1());
        message.setPLayer2(game.getPlayer2());
        message.setBoard(game.getBoard());
        message.setTurn(game.getTurn());
        message.setGameState(game.getGameState());
        message.setWinner(game.getWinner());
        return message;
    }

}
