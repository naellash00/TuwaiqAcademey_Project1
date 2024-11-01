import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // to give the user and computer a specific input (X or O)
        System.out.println("Please Enter Your Choice (X or O): ");
        char userInput = input.next().charAt(0);
        char computerInput = ' ';
        if(userInput=='X' || userInput == 'x'){
            computerInput = 'O';
        } else if(userInput == 'O' || userInput == 'o'){
            computerInput = 'X';
        } else {
            System.out.println("Your Input Is Incorrect");
        }

        //(1). Showcase the 9 positions of the tic tac toc:
        int[][] allThePositions = {{1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        // initial loop to just print the board
        for (int i = 0; i < allThePositions.length; i++) {
            for (int j = 0; j < allThePositions[i].length; j++) {
                System.out.print(allThePositions[i][j] + " ");
            }
            System.out.println(); // to print a new line between the rows
        }

        // (2). I want the user to enter a value at an index they choose: DONE
        // --> next step is to check if available position or not
        // THE USER VARIABLES
        int numberOfTriesForUser = 0; // The loop counter for how many times user enter values at the board
        int selectPosition = 0; // the position the user want to enter their value at
        int rowPosition = 0, columnPosition = 0;
        char[][] userInputs = new char[3][3];

        // THE COMPUTER VARIABLES
        int range = (9 - 1) + 1; // the range for the random computer position
        int computerRandomPosition = (int) (Math.random() * range) + 1;
        int rowPosition_forComputer = returnRowPosition(computerRandomPosition);
        int columnPosition_forComputer = returnColumnPosition(computerRandomPosition);
        char[][] computerInputs = new char[3][3];
        int computerNumberOfTries = 0;

        //INCOMPLETE:
        boolean isAvailablePosition = true;
        // this 2d array to store at it BOTH computer and user values
        char[][] theBoard_forUserAndComputer = new char[3][3];

        // THE USER POSITIONS LOOP
        while(numberOfTriesForUser < allThePositions.length){
            System.out.println("Choose available position from the board: ");
            selectPosition = input.nextInt();
            rowPosition = returnRowPosition(selectPosition);
            columnPosition = returnColumnPosition(selectPosition);

            // removing these because the user input is selected before
//            System.out.println("Enter " + userInput);
//            userInput = input.next().charAt(0);
            for(int i = 0; i < userInputs.length; i++) {
                userInputs[rowPosition][columnPosition] = userInput;
            }
            // (6). HERE it should print the updated board each time

            numberOfTriesForUser++;
        }
        // WORKING ON THE COMPUTER INPUT -- the computer positions loop
        while (computerNumberOfTries < 4) {
            // this three lines to give the computer new position everytime
            computerRandomPosition = (int) (Math.random() * range) + 1;
            rowPosition_forComputer = returnRowPosition(computerRandomPosition);
            columnPosition_forComputer = returnColumnPosition(computerRandomPosition);
            for (int i = 0; i < computerInputs.length; i++) {
                computerInputs[rowPosition_forComputer][columnPosition_forComputer] = computerInput;
            }
            computerNumberOfTries++;
        }
        // this loop just to print the user inputs
        for (int i = 0; i < userInputs.length; i++) {
            for (int j = 0; j < userInputs[i].length; j++) {
                System.out.print(userInputs[i][j]);
            }
            System.out.println();
        }
        // This loop is just to print the computer positions
        for (int i = 0; i < computerInputs.length; i++) {
            for (int j = 0; j < computerInputs[i].length; j++) {
                System.out.print(computerInputs[i][j] + " ");
            }
            System.out.println();
        }

        // working on combining user and computer inputs in one board



        //(3). I want to fix the previous code to check if the position is available or not
        //(4). I want the computer to select its random position too and enter its value: DONE
        // (5). I want to print the updated board each time the user and computer enter something


    }

    public static int returnRowPosition(int userSelectedPosition) {
        int rowNumber = 0;
        if (userSelectedPosition == 1 || userSelectedPosition == 2 || userSelectedPosition == 3) {
            rowNumber = 0;
        } else if (userSelectedPosition == 4 || userSelectedPosition == 5 || userSelectedPosition == 6) {
            rowNumber = 1;
        } else if (userSelectedPosition == 7 || userSelectedPosition == 8 || userSelectedPosition == 9) {
            rowNumber = 2;
        } else
            System.out.println("your number is incorrect");
        return rowNumber;
    }

    public static int returnColumnPosition(int userSelectedPosition) {
        int columnNumber = 0;
        if (userSelectedPosition == 1 || userSelectedPosition == 4 || userSelectedPosition == 7) {
            columnNumber = 0;
        } else if (userSelectedPosition == 2 || userSelectedPosition == 5 || userSelectedPosition == 8) {
            columnNumber = 1;
        } else if (userSelectedPosition == 3 || userSelectedPosition == 6 || userSelectedPosition == 9) {
            columnNumber = 2;
        } else
            System.out.println("your number is incorrect");
        return columnNumber;
    }

    public static boolean isAvailablePosition(char[][] array, int rownum, int columnNum) {
        //currently the array is alla digit thats why its always false
        boolean isAvailable = false;
        if (Character.isDigit(array[rownum][columnNum])) {
            isAvailable = true;
        }
        return isAvailable;
    }
}