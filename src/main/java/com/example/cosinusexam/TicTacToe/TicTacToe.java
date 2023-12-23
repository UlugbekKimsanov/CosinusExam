package com.example.cosinusexam.TicTacToe;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static int cmd;
    private static Scanner scanInt = new Scanner(System.in);
    private static Scanner scanStr = new Scanner(System.in);
    private static char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
    private static char currentPlayer = 'X';
    private static char[] availableMoves = {'1','2','3','4','5','6','7','8','9'};
    private static Random random = new Random();
    private static boolean won = false;
    private static int moves = 0;
    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        System.out.println("1. Play with computer");
        System.out.println("2. Play with player");
        System.out.print("Enter (exit = any number!) : ");
        cmd = scanInt.nextInt();
        switch (cmd){
            case 1 -> playWithComp();
            case 2 -> playWithPlayer();
        }
    }

    private static void playWithPlayer() {
        while (!won && moves<9){
            printBoard();
            System.out.print("\n\nEnter (" + currentPlayer +") : ");
            char move = scanStr.nextLine().charAt(0);
            if(isAvailableMove(move)){
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++){
                        if(Objects.equals(board[i][j],move)){
                            board[i][j] = currentPlayer;
                            checkWinner();
                            if(Objects.equals(currentPlayer,'X')){
                                currentPlayer = 'O';
                            }else {currentPlayer = 'X';}
                            deleteMove(move);
                            moves++;
                        }
                    }
                }
            }else {
                System.out.println("Invalid Value try again!");
            }
            if(moves == 9){
                System.out.println("Draw!");
            }
        }
    }

    private static void playWithComp() {
        char comp = 'X';
        char player ;
        while (true){
            System.out.print("Choose shape (X or O): ");
            player = scanStr.nextLine().charAt(0);
            if(player == 'X'){
                comp = 'O';
                currentPlayer = player;
                break;
            } else if (player == 'O') {
                currentPlayer = comp;
                break;
            }else {
                System.out.println("Enter valid value!");
            }
        }
        while (!won && moves<9){

            if(Objects.equals(currentPlayer,comp)){
                char randMove;
                if(availableMoves.length == 1){
                    randMove = availableMoves[0];
                    won = true;
                }else {
                    randMove = availableMoves[random.nextInt(availableMoves.length)];
                }
                deleteMove(randMove);
                switch (randMove){
                    case ('1') -> board[0][0] = comp;
                    case ('2') -> board[0][1] = comp;
                    case ('3') -> board[0][2] = comp;
                    case ('4') -> board[1][0] = comp;
                    case ('5') -> board[1][1] = comp;
                    case ('6') -> board[1][2] = comp;
                    case ('7') -> board[2][0] = comp;
                    case ('8') -> board[2][1] = comp;
                    case ('9') -> board[2][2] = comp;
                }
                moves++;
                checkWinner();
                currentPlayer = player;
            }else {
                printBoard();
                System.out.print("\n\nEnter: ");
                char move = scanStr.nextLine().charAt(0);
                if(isAvailableMove(move)){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++){
                            if(Objects.equals(board[i][j],move)){
                                board[i][j] = player;
                                checkWinner();
                                currentPlayer = comp;
                                deleteMove(move);
                                moves++;
                            }
                        }
                    }
                }else {
                    System.out.println("Invalid Value try again!");
                }

            }if(moves == 9){
                System.out.println("Draw!");
            }

        }
    }

    private static boolean isAvailableMove(char move) {
        for (char availableMove : availableMoves) {
            if(Objects.equals(move,availableMove)){
                return true;
            }
        }return false;
    }

    private static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if(j != 2){
                    System.out.print(" | ");
                }
            }
            if (i != 2){
                System.out.println("\n__________");
            }

        }
    }

    private static void deleteMove(char randMove) {
        char[] movesArray = new char[availableMoves.length-1];
        int index  = 0;
        for (char availableMove : availableMoves) {
            if(!Objects.equals(availableMove, randMove)){
                movesArray[index++] = availableMove;
            }
        }
        availableMoves = movesArray;
        System.out.println();
    }
    private static void checkWinner() {
        if (
            (
                (board[0][0] == currentPlayer && board[0][1] == currentPlayer && board[0][2] == currentPlayer) ||
                (board[1][0] == currentPlayer && board[1][1] == currentPlayer && board[1][2] == currentPlayer) ||
                (board[2][0] == currentPlayer && board[2][1] == currentPlayer && board[2][2] == currentPlayer)
            )
            ||(
                (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
            )
            ||(
                (board[0][0] == currentPlayer && board[1][0] == currentPlayer && board[2][0] == currentPlayer) ||
                (board[0][1] == currentPlayer && board[1][1] == currentPlayer && board[2][1] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][2] == currentPlayer && board[2][2] == currentPlayer)
            )){
            printBoard();
            won = true;
            System.out.println("\n\nWinner is " + currentPlayer);
        }
    }
}
