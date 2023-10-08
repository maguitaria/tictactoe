package com.example.tictactoe.model.dto;

public class PlayerMessage implements Message {
    private String type;
    private String gameId;
    private String player;
    private String content;
@Override
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
@Override
    public String getGameId() {
        return this.gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
@Override
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
