package com.example.tictactoe.controller;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.tictactoe.model.TicTacToe;
public class MessageController {
/**
 * Template for sending essages to clients through the messagebroker.
 */
@Autowired
private SimpMessagingTemplate messagingTemplate;
/**!SECTION
 * Manager for Tic-Tac-Toe games.
 */
private final TicTacToeManager ticTacToeManager = new TicTacToeManager();
/**!SECTION
 * Handles a request between two players. If a game is available anf the player has successfully joined 
 * the game room, HTTP response is sended.
 * If error, HTTP request sends an error
 */
@MessageMapping("/game.join")
@SendTo("/topic/game.state")
public Object joinGame(@Payload JoinMessage message, SimpMessageHeaderAccessor headerAccessor) {
    TicTacToe game = ticTacToeManager.joinGame(message.getPlayer());
    if (game == null) {
        TicTacToeMessage errorMessage = new TictacToeMessage();
        errorMessage.setType("error");
        errorMessage.setContent("There was en error in joining game. Please, try again...")
        return errorMessage;
    }
    headerAccessor.getSessionAttributes().put("gameId", game.getGameId());
   headerAccessor.getSessionAttributes().put("gameId", game.getPlayer()); 
}
}
