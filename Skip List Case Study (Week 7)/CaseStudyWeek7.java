import java.io.*;

public class CaseStudyWeek7 {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static DoublyLinkedList list = new DoublyLinkedList();
    static SkipList skipList = new SkipList();

    public static void main(String[] args) throws IOException {

        // Variable for operation of program.
        int operation = 0, number = 0;

        do {
            
            try {

                System.out.print(operations() + "\nPlease enter an operation: ");
                operation = Integer.parseInt(reader.readLine());

                if (operation == 10)
                    break;

            } catch (Exception e) { operation = 0; }
           
            switch (operation) {

                case 1: // INITIALIZING THE LIST.

                    if (list.isEmpty()) {

                        System.out.println("\nYou chose to initialize the list!");
                        initialize(operation);
                        System.out.println("Successfully initialize the list!");
                    }
                    else
                        System.out.println("YOU ALREADY INITIALIZE THE LIST!");
                break;
                
                case 2: // CREATING A SKIP LIST.

                    if (list.isEmpty()) 
                        System.out.println("INITIALIZE THE LIST!");
                    else if (skipList.isEmpty()) {

                        System.out.println("SKIP LIST CREATED!");
                        skipList.createSkipList(list);
                    }
                    else
                        System.out.println("SKIP LIST ALREADY CREATED!");
                break;

                case 3: // ADDING A NODE.

                    if (!list.isEmpty())
                        initialize(operation);
                    else {

                        System.out.println("PLEASE INITIALIZE THE LIST.");
                        continue;
                    }

                   // Check if the the size of skip list is equal to the square root of new updated doubly linked list.
                   if (!skipList.isEmpty() && skipList.size() != (int)Math.sqrt(list.size())) {

                        skipList.createSkipList(list);
                        System.out.println("SKIP LIST UPDATED!");
                   }
                   else 
                        System.out.println("YOU SUCCESFULLY ADDED A NODE!");
                   break;
                case 4: // DISPLAYING THE LIST FROM HEAD.

                    if (list.isEmpty()) 
                        System.out.println("LIST IS NOT INITIALIZED.");
                    else
                        System.out.println(list.DisplayFromHead());
                    
                    break;

                case 5: // DISPLAYING THE LIST FROM TAIL.

                    if (list.isEmpty()) 
                        System.out.println("LIST IS NOT INITIALIZED.");
                    else
                        System.out.println(list.DisplayFromTail());
                    
                    break;
                case 6: // DISPLAYING THE SKIP LIST FROM HEAD.

                    if (skipList.isEmpty())
                        System.out.println("SKIP LIST IS NOT INITIALIZED");
                    else
                        System.out.println(skipList.displayFromHead());
                    
                    break;

                case 7: // DISPLAYING THE SKIP LIST FROM TAIL.

                    if (skipList.isEmpty())
                        System.out.println("SKIP LIST IS NOT INITIALIZED");
                    else
                        System.out.println(skipList.displayFromTail());
                    
                    break;

                case 8: // SEARCH AN ELEMENT FROM THE HEAD OF THE LIST

                    if (skipList.isEmpty() || list.isEmpty())
                        System.out.println("OOPS! LOOK'S LIKE ONE OF YOUR LIST IS NOT CREATED. PLEASE MAKE SURE THAT BOTH IS CREATED.");
                    else {

                        while (true) {

                            try {

                                System.out.println("\n" + list.DisplayFromHead());
                                System.out.print("\nYOU CHOSE SEARCH FROM HEAD OPERATION\n\nEnter the number you want to search: ");
                                number = Integer.parseInt(reader.readLine());

                                System.out.println(list.searchFromHead(number)); 
                                break;
                            }
                            catch (Exception e) {

                                System.out.println("OOOPS! LOOK'S LIKE YOUR INPUT IS NOT AN INTEGER!");
                            }
                            
                        }
                        
                    }
                     break;
                case 9: // SEARCH AN ELEMENT FROM THE TAIL OF THE LIST.

                    if (skipList.isEmpty() || list.isEmpty()) 
                         System.out.println("OOPS! LOOK'S LIKE ONE OF YOUR LIST IS NOT CREATED. PLEASE MAKE SURE THAT BOTH IS CREATED.");
                    else {

                        while (true) {

                            try {

                                System.out.println("\n" + list.DisplayFromHead());
                                System.out.print("\nYOU CHOSE SEARCH FROM TAIL OPERATION\n\nEnter the number you want to search: ");
                                number = Integer.parseInt(reader.readLine());

                                System.out.println(list.searchFromTail(number)); 
                                break;
                            } catch (Exception e) {
                                System.out.println("OOOPS! LOOK'S LIKE YOUR INPUT IS NOT AN INTEGER!");
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("UNKNOWN OPERATION");
            }
        } while (operation != 10);
        System.out.println("\nEND OF PROGRAM!\nCREATED BY: Opo, Suelo, Alyana, Nads <3");
    }

    static void initialize(int operation)  {

        try { // a try block for checking if the user enters a valid integer for number of nodes.

            System.out.print("\nHow many nodes? ");
            int noOfNodes = Integer.parseInt(reader.readLine());

            for (int count = 1; count <= noOfNodes; count++) {

                try {

                    System.out.print("\nEnter a number: ");
                    int number = Integer.parseInt(reader.readLine());

                    if (operation == 3) { // if the user wants to add a node to the list.

                        // The input must greater than the last node of the doubly list.
                        if (list.getTail().getData() > number) {

                            System.out.printf("INPUT NUMBER MUST GREATER THAN %d (LAST NUMBER OF THE LIST)%n", list.getTail().getData());
                            count--;
                            continue;
                        }
                    }
                    list.add(number); // if the user is initializing we just add the node to the list.
                } catch (Exception e) {

                    System.out.println("Enter a valid integer value :)");
                    count--;
                }
            }
            list.sortInAsc(); // Sort in ascending order
        } catch (Exception e) {

            System.out.println("Please enter a valid size of the list.\n");
            initialize(operation); // recurse again
        }
    }

    /**
     * Operations that are used in program
     */
    static String operations() {

        return "\n[1] Initialize\n[2] Create Skiplist\n[3] Add Node\n[4] Display List From Head\n[5] Display List From Tail\n"
                + "[6] Display Skip List From Head\n[7] Display Skip List From Tail\n[8] Search From Head\n[9] Search From Tail\n[10] Exit\n";
    }
}
