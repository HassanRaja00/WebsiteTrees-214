import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WebSite {
    private WebPage homepage; //root

    public WebSite(){
        this.homepage = null; //the tree starts at the homepage and is originally empty
    }

    /**
     * sets the homepage to the root
     * @param homepage sets root
     */
    public void setHomepage(WebPage homepage) {
        this.homepage = homepage;
    }

    /**
     * @return the root
     */
    public WebPage getHomepage() {
        return homepage;
    }

    /**
     * adds child
     * @param cursor who add the child to
     * @param newName the name of child
     */
    public void addDepartment(WebPage cursor, String newName){
        cursor.addChild(new WebPage(newName));
        System.out.println("The department " + newName + " has been added.");
    }

    /**
     * removes a department
     * @param parent parent cursor used to fix the children
     */
    public void removeDepartment(WebPage parent, WebPage cursor){
        if(parent == null){ //if at the root, clear the whole tree
            this.homepage = null;
        } else{
            WebPage[] child = parent.getLinks();
            for(int i=0; i<parent.getChildSize();i++){
                if(cursor.getName().equals(child[i].getName())){
                    child[i] = child[parent.getChildSize()-1];
                    child[parent.getChildSize()-1] = null;
                    break;
                }
            }
            parent.setChildSize(parent.getChildSize()-1);
            cursor = parent;
        }
    }

    /**
     * used to set cursor in main method to one the user chose
     * @param w the cursor
     * @param entered the position they want the cursor to go to
     * @return the webpage the cursor should be at
     */
    public WebPage findDepartment(WebPage w, int entered){
        if(w.getChild(entered) == null){
            System.out.println("Not found");
            return w; //returns the same cursor as it was before
        } else{
            return w.getChild(entered);
        }
    }

    /**
     * prints the children of a webpage
     * @param w the cursor
     */
    public void printDepartments(WebPage w){
        WebPage[] children = w.getLinks();
        if(children[0]==null){ //if at the deepest level, cannot print children
            System.out.println("There are further sub departments!");
            return;
        }
        for(int i=0; i<w.getChildSize(); i++){
            System.out.println("(" + (i+1) + ")-" + children[i].getName());
        }
    }

    /**
     * prints the tree
     * @param w the cursor
     * @param k what child number the cursor is at
     */
    public void printFormat(WebPage w, int k){
        WebPage cursor = w;
        WebPage parent = cursor;
        if(cursor == null){
            return;
        } else{
            if(cursor == homepage){
                System.out.println(cursor.getName());
                if(cursor.getChildSize()>0){
                    cursor = cursor.getChild(k);
                }

            } else{
                if(cursor.isChildLeaf(cursor)){
                    System.out.println("  +" + cursor.getName());
                    WebPage[] child = cursor.getLinks();
                    for(int i = 0; i<cursor.getChildSize(); i++){
                        System.out.println("    -" + child[i].getName());
                    }
                    cursor = homepage;
                    cursor = cursor.getChild(k);

                }else if(cursor.getChildSize()>0){
                    cursor = cursor.getChild(k);
                }
            }
            this.printFormat(cursor, k+1);
        }
    }

    /**
     * builds tree by importing the text file
     * @param location the file the user wants to read
     */
    public void buildTree(String location){
        String inputLine;
        try{
            Scanner reader = new Scanner(new File(location));
            while(reader.hasNextLine()){
                String str = reader.nextLine();
                String[] arrOfStr = str.split(" ");
                int spot = Integer.parseInt(arrOfStr[0]);
                String name="";
                for(int i=1; i<arrOfStr.length;i++){
                    name += " "+ arrOfStr[i];
                }
                if(spot==0) { //adding the root
                    this.homepage = new WebPage(name);
                } else{
                    if(spot>0 && spot<10){ //add first children
                        this.homepage.addChild(new WebPage(name));
                    }
                    if(spot>10 && spot<20){ //adding sub department of first child
                        WebPage[] subChild1 = this.homepage.getLinks();
                        subChild1[0].addChild(new WebPage(name));
                    }
                    if(spot>20 && spot<30){ //adding sub department of second child in file
                        WebPage[] subChild2 = this.homepage.getLinks();
                        subChild2[1].addChild(new WebPage(name));
                    }
                    if(spot>30 && spot<40){ //adding sub department for third child in file
                        WebPage[] subChild3 = this.homepage.getLinks();
                        subChild3[2].addChild(new WebPage(name));
                    }
                    if(spot>40 && spot<50){
                        WebPage[] subChild4 = this.homepage.getLinks();
                        subChild4[3].addChild(new WebPage(name));
                    }
                    if(spot>50 && spot<60){
                        WebPage[] subChild4 = this.homepage.getLinks();
                        subChild4[4].addChild(new WebPage(name));
                    }
                }
            }
            reader.close();
            System.out.println("\nTree loading...\n\n");
            System.out.println("The tree has loaded.");
        } catch(FileNotFoundException ex){
            System.out.println("Incorrect file! Try again.");
        }
    }
}