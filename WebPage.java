/**
 * Hassan Raja 
 */
public class WebPage {
    /**
     * name is the name of webpage
     * links are the links that are on the webpage (children)
     * size is how many links a node has
     */
    private String name;
    private WebPage[] links;
    private int size;

    /**
     * Constructor for webpage
     * @param title sets the name of webpage
     */
    public WebPage(String title){
        this.name = title;
        this.links = new WebPage[10];
        this.size = 0;
    }

    /**
     * @param k what index the child is at
     * @return the webpage at the index
     */
    public WebPage getChild(int k){
        return links[k];
    }

    /**
     * Checks if a websites; children are leaves
     * @param w a particular website the cursor is at
     * @return true is the websits links are leaves
     */
    public boolean isChildLeaf(WebPage w){
        boolean leaf = true;
        WebPage children[] = w.getLinks();
        for(int i = 0; i<w.getChildSize(); i++){
            if(children[i].getChildSize()!=0){
                leaf = false;
                break;
            }
        }
        return leaf;
    }

    /**
     * @return name of the webpage
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets name of a webpage
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the links of a webpage (children)
     */
    public WebPage[] getLinks() { //get child
        return links;
    }

    /**
     * @param size used to decrement number of links after removal
     */
    public void setChildSize(int size) {
        this.size = size;
    }

    /**
     * @return how many links a webpage has
     */
    public int getChildSize() {
        return size;
    }

    /**
     * adds a link (child) to a webpage
     * @param p webpage you are adding
     */
    public void addChild(WebPage p){
        if(size>= links.length){
            WebPage[] bigger = new WebPage[size*2+1];
            for(int i=0; i<links.length; i++){
                bigger[i] = links[i];
            }
            this.links = bigger;
        }
        this.links[size] = p;
        this.size++;
    }

    /**
     * sets the links for a websire
     * @param links new array of links
     */
    public void setLinks(WebPage[] links) {
        this.links = links;
    }
}
