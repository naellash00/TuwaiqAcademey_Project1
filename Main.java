import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please Enter Your Choice Of The Game (X or O): ");
        char userInput = input.next().charAt(0);
        input.nextLine();
        char computerInput = ' ';
        if (userInput == 'X' || userInput == 'x') {
            computerInput = 'O';
        } else if (userInput == 'O' || userInput == 'o') {
            computerInput = 'X';
        } else {
            System.out.println("Your Input Is Incorrect");
        }
        System.out.println("How Many Rounds You Want To Play? ");
        int numberOfRounds = input.nextInt();
        int numberOf_userWins = 0, numberOf_computerWins = 0;
        boolean theWinner_isUser, theWinner_isComputer;
        switch (numberOfRounds) {
            case 1:
                theWinner_isUser = playOneRound(input, userInput, computerInput);
                if (theWinner_isUser) {
                    numberOf_userWins++;
                    System.out.println("Congrats, You Win The Game!");
                } else {
                    numberOf_computerWins++;
                    System.out.println("Computer Wins The Game");
                }
                break;
            case 3:
                for (int i = 1; i <= numberOfRounds; i++) {
                    System.out.println("This Is Round " + i);
                    theWinner_isUser = playOneRound(input, userInput, computerInput);

                    // check who wins first round
                    if (theWinner_isUser) {
                        numberOf_userWins++;
                        System.out.println("You Win This Round!");
                    } else {
                        numberOf_computerWins++;
                        System.out.println("Computer Wins This Round");
                    }

                    //check who two rounds wins
                    if (numberOf_userWins == 2) {
                        System.out.println("Congrats, You Win The Game!");
                        break;
                    } else if (numberOf_computerWins == 2) {
                        // used if-else so the below condition dont become unreachable
                        System.out.println("Computer Wins The Game");
                        break;
                    }
//                    else {
//                        System.out.println("There Is A Tie");
//
//                    }

                }
                // check three rounds wins
                // added the != 2 condition so it dosent print the win two times
                if (numberOf_userWins > numberOf_computerWins && numberOf_userWins != 2) {
                    System.out.println("Congrats, You Win The Game!");
                } else if (numberOf_computerWins > numberOf_userWins && numberOf_computerWins != 2) {
                    System.out.println("Computer Wins The Game");
                }
//                else {
//                    System.out.println("There Is A Tie");
//                }
                break;
            default:
                System.out.println("Incorrect Number, The Rounds Are Either One Or Three.");
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
        for (int i = 0; i < theUserAndComputerBoard.length; i++) {
            for (int j = 0; j < theUserAndComputerBoard[i].length; j++) {
                //check if the char position is empty --> print the corresponding number in the board
                if (theUserAndComputerBoard[i][j] == '\u0000') {
                    System.out.print(positionNumber + " ");
                }
                // and if its unavailable position --> print the char value (x or o)
                else {
                    System.out.print(theUserAndComputerBoard[i][j] + " ");
                }
                positionNumber++; // to move it forward each time
            }
            System.out.println();
        }

    }

    // [0][0]== / [0][1] == / [0][2] ==
    // [1][0]== / [1][1] == / [1][2] ==
    // [2][0]== / [2][1] == / [2][2] ==
    public static boolean checkWin(char[][] theUserAndComputerBoard, char XorO) {
        // there is 3 rows - 3 columns - and 2 diagonals wins

        // first just loop through the rows and check if they are the same
        for (int i = 0; i < theUserAndComputerBoard.length; i++) {
            if (theUserAndComputerBoard[i][0] == XorO &&
                    theUserAndComputerBoard[i][1] == XorO &&
                    theUserAndComputerBoard[i][2] == XorO) {
                return true;
            }
        }
        // second loop through columns and check them
        for (int i = 0; i < theUserAndComputerBoard[0].length; i++) {
            if (theUserAndComputerBoard[0][i] == XorO &&
                    theUserAndComputerBoard[1][i] == XorO &&
                    theUserAndComputerBoard[2][i] == XorO) {
                return true;
            }
        }
        // lastly loop through the 2 diagonals and check them
        //the upper left - lower right diagonal:
        if (theUserAndComputerBoard[0][0] == XorO &&
                theUserAndComputerBoard[1][1] == XorO &&
                theUserAndComputerBoard[2][2] == XorO) {
            return true;
        }
        // the upper right - lower left diagonal:
        if (theUserAndComputerBoard[0][2] == XorO &&
                theUserAndComputerBoard[1][1] == XorO &&
                theUserAndComputerBoard[2][0] == XorO) {
            return true;
        }
        return false;
    }

    public static boolean playOneRound(Scanner input, char userInput, char computerInput) {
        char[][] theGameBoard = new char[3][3];
        System.out.println("The Game Board: ");
        printGameBoard(theGameBoard);

        // THE SYSTEM VARIABLES:
        int numberOfTries_forUserAndComputer = 0;
        final int MAX_MOVES = 9;
        // USER:
        int selectPosition = 0; // the position the user want to enter their value at
        int rowPosition_forUser = 0, columnPosition_forUser = 0;
        // COMPUTER:
        int range = (9 - 1) + 1; // the range for the random computer position
        int computerRandomPosition = (int) (Math.random() * range) + 1;
        int rowPosition_forComputer = 0, columnPosition_forComputer = 0;

        //(7). complete checkWin method: DONE
        while (numberOfTries_forUserAndComputer < MAX_MOVES) {
            // user turn
            do {
                System.out.println("Choose available position from the board: ");
                selectPosition = input.nextInt();
                rowPosition_forUser = returnRowPosition(selectPosition);
                columnPosition_forUser = returnColumnPosition(selectPosition);
                // condition to check if position available or not
                if (!isAvailablePosition(theGameBoard, rowPosition_forUser, columnPosition_forUser)) {
                    System.out.println("The Position Is Unavailable, Try Again. ");
                }
            } while (!isAvailablePosition(theGameBoard, rowPosition_forUser, columnPosition_forUser));
            // set the position after checking the available condition
            theGameBoard[rowPosition_forUser][columnPosition_forUser] = userInput;
            numberOfTries_forUserAndComputer++;

            if (checkWin(theGameBoard, userInput)) {
//                System.out.println("Congrats You Win");
                // will return true if the user wins, false otherwise
                return true;
                //break;
            }
            if (numberOfTries_forUserAndComputer >= MAX_MOVES) {
//                System.out.println("There Is A Tie");
                return false;
                // break;
            }
            // computer round
            do {
                computerRandomPosition = (int) (Math.random() * range) + 1;
                rowPosition_forComputer = returnRowPosition(computerRandomPosition);
                columnPosition_forComputer = returnColumnPosition(computerRandomPosition);
                // this loop will continue as long as the position is unavailable
                // so it can randomly choose available one each time
            } while (!isAvailablePosition(theGameBoard, rowPosition_forComputer, columnPosition_forComputer));

            // now if the condition is available, put the value
            theGameBoard[rowPosition_forComputer][columnPosition_forComputer] = computerInput;
            numberOfTries_forUserAndComputer++;

            if (checkWin(theGameBoard, computerInput)) {
//                System.out.println("Computer Wins");
                return false;
                // break;
            }
            //print the game board
            printGameBoard(theGameBoard);
        }
        // this loop to print the board with both user and computer side
        System.out.println("The Final Game Board");
        printGameBoard(theGameBoard);

        return false;
    }

}