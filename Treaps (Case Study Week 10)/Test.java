import java.util.*;

public class Test {

    static Scanner console = new Scanner(System.in);
    static Treap t = new Treap();
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println(choices());
            System.out.print("Enter your choice: ");
            choice = console.nextInt();

            switch (choice) {

                case 1: 
                    addToTreap();
                    break;
                
                case 2:

                    System.out.print("Enter the node you want to delete: ");
                    int value = console.nextInt();

                    t.deleteNode(value, null);
                    break;
                case 3:
                    System.out.println(t.Display());
            }
        } while (choice != 4);

        System.out.println("Thank you! Created by Opo, Pornelosa, Suelo, and Marcelo. <143");
    }

    static String choices() {

        return "\n[1] Add\n[2] Delete\n[3] Display\n[4] Exit\n";
    }

    static void addToTreap() {

        int number;

        System.out.print("How many tree nodes do you prefer? ");
        int noOfNodes = console.nextInt();

        for (int i = 0; i < noOfNodes; i++) {

            System.out.print("Enter a number: ");
            number = console.nextInt();

            t.insert(number);
        }
        System.out.println("Node/s successfully added!");
    }
}
