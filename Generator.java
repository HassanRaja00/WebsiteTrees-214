import java.util.InputMismatchException;
import java.util.Scanner;
public class Generator {
    public static void main (String[] a){
        Scanner input = new Scanner(System.in);
        WebSite tree = new WebSite();
        System.out.println("Welcome to ARK Website Generator! What would you like to do today?");
        String menu = "Menu:\n(I) - Import .txt File\n(A) - Add Department\n(R) - Remove Department\n" +
                "(C) - Current Department\n(G) - Go to Sub-department\n(H) - Head Department\n(P) - Print Format\n" +
                "(E) - Empty Tree\n(Q) - Quit";
        WebPage cursor = null; //used for getting department names currently
        WebPage parent = null; //used to get head department of a department
        while(true){
            System.out.println(menu);
            System.out.println("Enter Selection: ");
            String user = input.nextLine();
            switch(user){
                case "I":
                    //import file
                    System.out.print("Please enter the name of the .txt file: ");
                    tree.buildTree(input.nextLine());
                    cursor = tree.getHomepage();

                    break;
                case "A":
                    //add department
                    if(tree.getHomepage() == null){
                        System.out.println("You cannot add a department to an empty tree! Import tree first");
                    } else{
                        System.out.print("What would you like to name the department?");
                        String newName = input.nextLine();
                        tree.addDepartment(cursor, newName);
                    }
                    break;
                case "R":
                    //remove
                    if(tree.getHomepage() == null){
                        System.out.println("You cannot remove a department to an empty tree! Import tree first");
                    } else{
                        System.out.println(cursor.getName() + " has been removed");
                        tree.removeDepartment(parent, cursor);
                        cursor = tree.getHomepage();
                    }
                    break;
                case "C":
                    //current
                    if(tree.getHomepage() == null){
                        System.out.println("Empty tree! Import tree first");
                    } else{
                        System.out.println("You are currently at " + cursor.getName());
                    }
                    break;
                case "G":
                    //go to sub
                    if(tree.getHomepage() == null){
                        System.out.println("Empty tree! Import tree first");
                    }
                    else{
                        if(cursor.getChild(0) == null){
                            System.out.println("There are no further sub-departments.");
                        } else{
                            parent = cursor;
                            System.out.println("Which department would you like to go to?"); //prints departments if cursor is at root
                            tree.printDepartments(cursor);
                            System.out.print("Enter selection: ");
                            try{
                                int selection = Integer.parseInt(input.nextLine());
                                cursor = tree.findDepartment(cursor,selection-1);
                                System.out.println("You are currently at " + cursor.getName());
                            } catch(InputMismatchException ex){
                                System.out.println("You entered a wrong input.");
                            } catch(Exception ex){
                                System.out.println("Error. Sorry that was incorrect input");
                            }
                        }

                    }
                    break;
                case "H":
                    //head department
                    if(tree.getHomepage() == null){
                        System.out.println("Empty tree! Import tree first");
                    } else{
                        if(cursor != tree.getHomepage()){
                            cursor = parent;
                            System.out.println(cursor.getName());
                            parent = tree.getHomepage();
                        } else{
                            System.out.println("You are already at the homepage");
                        }

                    }
                    break;
                case "P":
                    //print
                    if(tree.getHomepage() == null){
                        System.out.println("There is currently no Website’s format to print.");
                    } else{
                        try{
                            tree.printFormat(tree.getHomepage(),0);
                        } catch(StackOverflowError ex){
                            System.out.println("Something went wrong!");
                        }

                    }
                    break;
                case "E":
                    //empty by resetting tree
                    if(tree.getHomepage() == null){
                        System.out.println("Tree is already empty!");
                    } else{
                        tree = new WebSite();
                        System.out.println("The tree is now empty!");
                    }

                    break;
                case "Q":
                    input.close();
                    System.out.println("Sorry to see you go! Have a good day!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Incorrect input. Please try again.");
            }
        }
    }
}
