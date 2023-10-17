import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import React, { useEffect, useState } from "react";
import SockJS from "sockjs-client";
import Stomp from "stompjs";

const TicTacToe = () => {
    const [stompClient, setStompClient] = useState();
    const [game, setGame] = useState();
    const [player, setPlayer] = useState();
    useEffect(() => {
        // Initialize STOMP client and connect to server
        const socket = new SockJS("/ws");
        const client = Stomp.over(socket);
        client.connect({}, (frame) => {
            setStompClient(client);
            subscribeToGameUpdates(client);
            loadGame();
        });
        // Clean up when unmounting the component
        return () => {
            if (stompClient) {
                stompClient.disconnect();
            }
        };
    }, [stompClient]);

    const subscribeToGameUpdates = (client) => {
        // Subscripe to game updates via Stomp
        client.subscribe('/topic/game.state', (message) => {
            const gameUpdate = JSON.parse(message.body);
            handleGameUpdate(gameUpdate);
        })
    }
    const handleGameUpdate = (message) => {
        // Handle various types of game updates
        switch (message.type) {
            case "game.join":
                updateGame(message);
                break;
            case "game.gameOver":
                updateGame(message);
                if (message.gameState === 'TIE') {
                    showToast('Game over! It is a tie!', 'success')
                } else {
                    showWinner(message.winner)
                }
                break;
            case "game.joined":
                if (game !== null && game.gameId !== message.gameId) return;
                setPlayer(localStorage.getItem('playerName'));
                updateGame(message);
                subscribeToGameUpdates(stompClient)
                break;
            case 'game.move':
                updateGame(message);
                break;
            case 'game.left':
                updateGame(message)
                if (message.winner) showWinner(message.winner);
                break;
            case 'error':
                showToast(message.content, 'error');
                break;
            default:
                break;
        }
    }
    const sendMessage = (message) => {
        // Send a message to the server using STOMP
        stompClient.send(`/app/${message.type}`, {}.JSON.stringify(message));
    }
    const makeMove = (move) => {
        // Make a move in the game
        sendMessage({
            type: 'game.move',
            move: move,
            turn: game.turn,
            sender: player,
            gameId: game.gameId,
        })
    }
    const updateGame = (message) => {
        // Update the game state
        setGame({
            gameId: message.gameId,
            board: message.board,
            turn: message.turn,
            player1: message.player1,
            player2: message.player2,
            gameState: message.gameState,
            winner: message.winner,
        })
    }
    const showWinner = (winner) => {
        showToast(`THE Winner is ${winner}!`, 'success')
    }
    const showToast = (message, type = 'info') => {
        // Show a toast notification
        switch (type) {
            case 'success':
                toast.success(message);
                break;
            case 'error':
                toast.error(message);
                break;
            case 'warning':
                toast.warning(message);
                break;
            default:
                toast.info(messsage);
                break;
        }
    }

   const loadGame = () => {
        const playerName = localStorage.getItem('playerName');
        if (playerName) {
            sendMessage({
                type: 'game.join',
                player: playerName,
            });
        } else {
            const playerName = prompt("Enter your name:");
            if (playerName) {
                localStorage.setItem("playerName", playerName);
                sendMessage({
                    type: 'game.join',
                    player: playerName,
                });
            }
        }
    }
    return (
        <div className="tic-tac-toe">
            <ToastContainer position='top-right' autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
            />
        </div>
    )
}
export default TicTacToe;

