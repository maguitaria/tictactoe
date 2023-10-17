import React, { useState } from "react";
import Gameboard from "./components/GameBoard"; // Import your gameboard component
import Square from "./components/Square"; // Import your square component
import showToast from "./utils/ToastMessages"; // Import the showToast function from your toast message file
const App = () => {
  const [gameState, setGameState] = useState(/* Initial game state here */);

  const handleSquareClick = (squareIndex) => {
    // Handle square click logic here
    // Update game state as needed
    // You can also trigger toast messages using showToast function
    showToast("Square clicked!");
  };

  return (
    <div className="app">
      <h1>Tic-Tac-Toe Game</h1>
      <Gameboard gameState={gameState}>
        {gameState.board.map((value, index) => (
          <Square
            key={index}
            value={value}
            onClick={() => handleSquareClick(index)}
          />
        ))}
      </Gameboard>
    </div>
  );
};

export default App;
