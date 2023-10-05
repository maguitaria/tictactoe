package com.example.tictactoe.controller;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/")
public class TicTacToeController {
 /**
  * Renders Tic tac toe game with an empty board
  * @return model and view for game page
  */
    @GetMapping("/")
    public ModelAndView index() {
        return tictacToe();
    }
    /**
     * Renders Tic tac toe game with an empty board
     * 
     * @return model and view for game page
     */
    @RequestMapping("/index")
    public ModelAndView tictacToe() {
        ModelAndView modelAndView = new ModelAndView("index");
        String[][] board = new String[3][3];
        Arrays.stream(board).forEach(row -> Arrays.fill(row, " "));
        modelAndView.addObject("board", board);
        return modelAndView;
    }
}
