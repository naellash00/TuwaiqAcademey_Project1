import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // to give the user and computer a specific input (X or O)
        System.out.println("Please Enter Your Choice of the game (X or O): ");
        char userInput = input.next().charAt(0);
        char computerInput = ' ';
        if (userInput == 'X' || userInput == 'x') {
            computerInput = 'O';
        } else if (userInput == 'O' || userInput == 'o') {
            computerInput = 'X';
        } else {
            System.out.println("Your Input Is Incorrect");
        }

        int[][] theGameBoard = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        //  loop to just print the board
        System.out.println("The Game Board: ");
        for (int i = 0; i < theGameBoard.length; i++) {
            for (int j = 0; j < theGameBoard[i].length; j++) {
                System.out.print(theGameBoard[i][j] + " ");
            }
            System.out.println(); // to print a new line between the rows
        }

        // THE USER VARIABLES
        int selectPosition = 0; // the position the user want to enter their value at
        int rowPosition_forUser = 0, columnPosition_forUser = 0;
        int numberOfTriesForUser = 0; // The loop counter for how many times user enter values at the board
        // THE COMPUTER VARIABLES
        int range = (9 - 1) + 1; // the range for the random computer position
        int computerRandomPosition = (int) (Math.random() * range) + 1;
        int rowPosition_forComputer = 0, columnPosition_forComputer = 0;
        int numberOfTriesForComputer = 0;
        //System variables:
        // this 2d array to store at it BOTH computer and user values
        char[][] theBoard_forUserAndComputer = new char[3][3];
        int numberOfTries_forUserAndComputer = 0; // numberOfTriesForUser + numberOfTriesForComputer;
        final int MAX_MOVES = 9;

//        // working on combining user and computer inputs in one board: DONE
//        // USER SIDE:
//        // current problem --> the user enter more than 9 times not 3-4 times
//        // --> separate them and fix the available position issue: DONE
//        while (numberOfTriesForUser < 4) {
//            do {
//                System.out.println("Choose available position from the board: ");
//                selectPosition = input.nextInt();
//                rowPosition_forUser = returnRowPosition(selectPosition);
//                columnPosition_forUser = returnColumnPosition(selectPosition);
//                // condition to check each time if position available or not
//                if (!isAvailablePosition(theBoard_forUserAndComputer, rowPosition_forUser, columnPosition_forUser)) {
//                    System.out.println("The position is unavailable. try again");
//                }
//            } while (!isAvailablePosition(theBoard_forUserAndComputer, rowPosition_forUser, columnPosition_forUser));
//
//            // set the position after checking the available condition
//            theBoard_forUserAndComputer[rowPosition_forUser][columnPosition_forUser] = userInput;
//
//            // here print the board with each update
//            for (int i = 0; i < theBoard_forUserAndComputer.length; i++) {
//                for (int j = 0; j < theBoard_forUserAndComputer[i].length; j++) {
//                    System.out.print(theBoard_forUserAndComputer[i][j]);
//
//                }
//                System.out.println();
//            }
//
//            numberOfTriesForUser++;
//        }
//
//        // COMPUTER SIDE:
//        // Trying the computer side with different condition in the while loop
//        while (numberOfTriesForComputer < 4) {
//            do {
//                computerRandomPosition = (int) (Math.random() * range) + 1;
//                rowPosition_forComputer = returnRowPosition(computerRandomPosition);
//                columnPosition_forComputer = returnColumnPosition(computerRandomPosition);
//                // this loop will continue as long as the position is unavailable
//                // so it can randomly choose available one each time
//            } while (!isAvailablePosition(theBoard_forUserAndComputer, rowPosition_forComputer, columnPosition_forComputer));
//
//            // now if the condition is available, put the value
//            theBoard_forUserAndComputer[rowPosition_forComputer][columnPosition_forComputer] = computerInput;
//
//            // print the board
//            for (int i = 0; i < theBoard_forUserAndComputer.length; i++) {
//                for (int j = 0; j < theBoard_forUserAndComputer[i].length; j++) {
//                    System.out.print(theBoard_forUserAndComputer[i][j]);
//
//                }
//                System.out.println();
//            }
//            numberOfTriesForComputer++;
//        }
//
//


        //(3). fix the previous code to check if the position is available or not: DONE
        //(5). print the updated board each time the user and computer enter something: DONE
        //(6). make the user and computer enter values one after the other: DONE
        //(7). complete checkWin method and check for exceptions
        while (numberOfTries_forUserAndComputer < MAX_MOVES) {
            // user turn
            do {
                System.out.println("Choose available position from the board: ");
                selectPosition = input.nextInt();
                rowPosition_forUser = returnRowPosition(selectPosition);
                columnPosition_forUser = returnColumnPosition(selectPosition);
                // condition to check each time if position available or not
                if (!isAvailablePosition(theBoard_forUserAndComputer, rowPosition_forUser, columnPosition_forUser)) {
                    System.out.println("The position is unavailbale. try again");
                }
            } while (!isAvailablePosition(theBoard_forUserAndComputer, rowPosition_forUser, columnPosition_forUser));
            // set the position after checking the available condition
            theBoard_forUserAndComputer[rowPosition_forUser][columnPosition_forUser] = userInput;
            numberOfTries_forUserAndComputer++;
            if (checkWin()) {
                System.out.println("you win");
                break;
            }
            if (numberOfTries_forUserAndComputer >= MAX_MOVES) {
                System.out.println("tie");
                break;
            }

            // computer round
            do {
                computerRandomPosition = (int) (Math.random() * range) + 1;
                rowPosition_forComputer = returnRowPosition(computerRandomPosition);
                columnPosition_forComputer = returnColumnPosition(computerRandomPosition);
                // this loop will continue as long as the position is unavailable
                // so it can randomly choose available one each time
            } while (!isAvailablePosition(theBoard_forUserAndComputer, rowPosition_forComputer, columnPosition_forComputer));

            // now if the condition is available, put the value
            theBoard_forUserAndComputer[rowPosition_forComputer][columnPosition_forComputer] = computerInput;
            numberOfTries_forUserAndComputer++;

            if (checkWin()) {
                System.out.println("computer wins");
                break;
            }

            //print the game board
            printGameBoard(theBoard_forUserAndComputer);

        }
        // this loop to print the board with both user and computer side
        System.out.println("The final board");
        for (int i = 0; i < theBoard_forUserAndComputer.length; i++) {
            for (int j = 0; j < theBoard_forUserAndComputer[i].length; j++) {
                System.out.print(theBoard_forUserAndComputer[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static int returnRowPosition(int position) {
        int rowNumber = 0;
        if (position == 1 || position == 2 || position == 3) {
            rowNumber = 0;
        } else if (position == 4 || position == 5 || position == 6) {
            rowNumber = 1;
        } else if (position == 7 || position == 8 || position == 9) {
            rowNumber = 2;
        } else
            System.out.println("your number is incorrect");
        return rowNumber;
    }

    public static int returnColumnPosition(int position) {
        int columnNumber = 0;
        if (position == 1 || position == 4 || position == 7) {
            columnNumber = 0;
        } else if (position == 2 || position == 5 || position == 8) {
            columnNumber = 1;
        } else if (position == 3 || position == 6 || position == 9) {
            columnNumber = 2;
        } else
            System.out.println("your number is incorrect");
        return columnNumber;
    }

    public static boolean isAvailablePosition(char[][] array, int rowNumber, int columnNumber) {
        boolean isAvailable = false;
        if (array[rowNumber][columnNumber] == '\u0000') {
            isAvailable = true;
        }
        return isAvailable;
    }

    public static void printGameBoard(char[][] theUserAndComputerBoard) {
        int positionNumber = 1; // from 1 to 9
        // PROBLEM !! now its only printing the board with empty char values and
        // i want it to print it with numbers values: DONE
        for (int i = 0; i < theUserAndComputerBoard.length; i++) {
            for (int j = 0; j < theUserAndComputerBoard[i].length; j++) {
                //System.out.print(theUserAndComputerBoard[i][j] + " ");
                //check if the char position is empty --> print the corresponding number in the board
                if (theUserAndComputerBoard[i][j] == '\u0000') {
                    System.out.print(positionNumber + " ");
                } else { // and if its unavailable position --> print the char value (x or o)
                    System.out.print(theUserAndComputerBoard[i][j] + " ");
                }
                positionNumber++; // to move it forward each time
            }
            System.out.println();
        }

    }

    public static boolean checkWin() {
        System.out.println("congrats");

        return false;
    }

}