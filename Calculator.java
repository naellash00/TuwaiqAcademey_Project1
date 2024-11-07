import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please, Enter number1: ");
        int number1 = input.nextInt();
        System.out.println("And Enter number2: ");
        int number2 = input.nextInt();
        int userInput = 0;
        do {
            System.out.println("Choose an operation from the menu: " +
                    "\n1. Addition " + "\n2. Subtraction " + "\n3. Multiplication " + "\n4. Division "
                    + "\n5. Remainder" + "\nOr enter (0) to exit the calculator");
            userInput = input.nextInt();
            switch (userInput) {
                case 1:
                    System.out.println(number1 + " + " + number2 + " = " + addition(number1, number2));
                    break;
                case 2:
                    System.out.println(number1 + " - " + number2 + " = " + subtraction(number1, number2));
                    break;
                case 3:
                    System.out.println(number1 + " * " + number2 + " = " + multiplication(number1, number2));
                    break;
                case 4:
                    System.out.println(number1 + " / " + number2 + " = " + division(number1, number2));
                    break;
                case 5:
                    System.out.println(number1 + " % " + number2 + " = " + remainder(number1, number2));
                    break;
//                default:
//                    System.out.println("You Did Not Choose Any Of The 5 Operations, Try Again");

            }
        } while (userInput != 0);
        System.out.println("You Are Out Of The Calculator");
    }

    public static int addition(int number1, int number2) {
        return number1 + number2;
    }

    public static int subtraction(int number1, int number2) {
        return number1 - number2;
    }

    public static int multiplication(int number1, int number2) {
        return number1 * number2;
    }

    public static int division(int number1, int number2) {
        return number1 / number2;
    }

    public static int remainder(int number1, int number2) {
        return number1 % number2;
    }
}
