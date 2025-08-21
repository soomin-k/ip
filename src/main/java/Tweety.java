import java.util.Scanner;

public class Tweety {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // define scanner to read user inputs
        String[] strArr = new String[100];
        int i = 0;

        System.out.println("     ____________________________________________________________");
        System.out.println("     Hello! I'm Tweety");
        System.out.println("     What can I do for you?");
        System.out.println("     ____________________________________________________________");

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("list")) {
                int index = 0;
                System.out.println("     ____________________________________________________________");
                while (strArr[index] != null) {
                    System.out.println("     " + (index + 1) + ". " + strArr[index]);
                    index++;
                }
                System.out.println("     ____________________________________________________________");
            } else if (userInput.equals("bye")) {
                System.out.println("     ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("     ____________________________________________________________");
                break;
            } else {
                strArr[i] = userInput;
                System.out.println("     ____________________________________________________________");
                System.out.println("     " + "added: " + userInput);
                System.out.println("     ____________________________________________________________");
                i++;
            }
        }
    }
}
