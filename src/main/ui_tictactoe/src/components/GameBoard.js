import React from "react";
import Square from "./Square";

const GameBoard = ({ game, makeMove, updateGame, showToast }) => {
  const renderSquare = (i) => {
    const value = game && game.board ? game.board[i] : null;
    return <Square value={value} onClick={() => handleSquareClick(i)} />;
  };

  const handleSquareClick = (i) => {
    // Check if the game is over or the square is already taken
    if (!game || game.gameState !== "PLAYING" || game.board[i] !== " ") {
      return;
    }

    // Make a move
    makeMove(i);
  };

  return (
    <div className="game-board">
      <div className="board-row">
        {renderSquare(0)}
        {renderSquare(1)}
        {renderSquare(2)}
      </div>
      <div className="board-row">
        {renderSquare(3)}
        {renderSquare(4)}
        {renderSquare(5)}
      </div>
      <div className="board-row">
        {renderSquare(6)}
        {renderSquare(7)}
        {renderSquare(8)}
      </div>
    </div>
  );
};

export default GameBoard;
