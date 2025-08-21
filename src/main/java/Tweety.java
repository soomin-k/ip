import java.util.Scanner;

public class Tweety {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // define scanner to read user inputs

        System.out.println("     ____________________________________________________________");
        System.out.println("     Hello! I'm Tweety");
        System.out.println("     What can I do for you?");
        System.out.println("     ____________________________________________________________");

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("     ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("     ____________________________________________________________");
                break;
            }

            System.out.println("     ____________________________________________________________");
            System.out.println("     " + userInput);
            System.out.println("     ____________________________________________________________");
        }
    }
}
