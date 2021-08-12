import java.util.*;

public class Test {
    
    static Scanner console = new Scanner(System.in);
    static Stack stack = new Stack();
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n" + displayMenu());
            System.out.print("Enter your choice: ");
            choice = console.nextInt();

            switch (choice) {

                case 1: 
                    System.out.println("YOU CHOSE TO ADD AN ELEMENT!");
                    addElements();
                    break;

                case 2:
                    if (stack.isEmpty()) {

                        System.out.println("STACK IS EMPTY!");
                        break;
                    }
                    System.out.println("YOU CHOSE TO REMOVE AN ELEMENT!");
                    System.out.println("POPPED ELEMENT: " + stack.pop());

                    if (stack.isEmpty())
                        System.out.println("STACK IS EMPTY!");
                    else
                        System.out.println("CURRENT LIST: " + stack.Display());
                    break;

                case 3:
                     if (stack.isEmpty()) {

                        System.out.println("STACK IS EMPTY!");
                        break;
                    }
                    System.out.println("YOU CHOSE TO PEEK THE TOP OF STACK!");
                    System.out.println("POPPED ELEMENT: " + stack.peek());
                    break;

                case 4:
                     if (stack.isEmpty()) {

                        System.out.println("STACK IS EMPTY!");
                        break;
                    }
                    System.out.println("YOU CHOSE TO DISPLAY THE CURRENT VALUES OF STACK");
                    System.out.println("CURRENT LIST: " + stack.Display());
                    break;

                case 5: 
                    System.out.println("YOU CHOSE TO DISPLAY THE HISTORY VALUES OF STACK");
                    System.out.println("HISTORY LIST: " + stack.DisplayHistory());
                    break;

                default:
                    break;
            }
        } while (choice != 6);
        System.out.println("Thank You!\nCreated By: Nads Marcelo");
    }

    static String displayMenu() {

        return "[1] Push\n[2] Pop\n[3] Top\n[4] Display\n[5] Display History\n[6] Exit\n";
    }

    static void addElements() {

        System.out.print("How many elements do you prefer? ");
        int noOfElements = console.nextInt();

        for (int i = 0; i < noOfElements; i++) {

            System.out.print("\nEnter an element: ");
            stack.push(console.nextInt());
        }
    }
}
