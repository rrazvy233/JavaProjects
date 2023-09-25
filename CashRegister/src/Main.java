/* 1.make menu and get input
 * 2. initialize arrays and populate the articles array
 * 3. ask user for input for menu and use input in a switch
 * 4. based on user input, recreate or delete items in the articles array
 * 5. based on user input, sell a quantity of X item and log the sale
 * 6. based on user input, display sales or sort order history in ascending order
 * Razvan Petcu
 */

import java.util.Scanner;
import java.util.Date;

public class Main {
    private static Scanner userInput = new Scanner(System.in);
    public static void main(String[] args) {
        //initialization of arrays
        int[][] articles = new int[10][3];
        //populates the array startingg with 1000 and increasing by 1
        int startnum = 1000;
        for (int i = 0; i < articles.length; i++){
            articles[i][0] = startnum++;
            articles[i][1] = (int)(Math.random() * (10 - 1)) +1;
            articles[i][2] = (int)(Math.random() * (1000 - 100)) +100;
        }
        //initialize sales and date arrays
        int[][] salesArray = new int[1000][3];
        Date[] salesDate = new Date[1000];
        while(true){
            //while loop to keep the menu selection active until the user chooses to exit
            switch(menu())
            {
                case 1:
                    System.out.print("how many articles do you want to add \n");
                    int newArticles = input();
                    articles = insertArticles(articles, articles[articles.length-1][0]+1, newArticles);
                    System.out.print("\n articles successfully added \n");
                    break;
                case 2:
                    removeArticle(articles);
                    break;
                case 3:
                    printArticles(articles);
                    break;
                case 4:
                    sellArticle(salesArray, salesDate, articles);
                    break;
                case 5:
                    printSales(salesArray, salesDate);
                    break;
                case 6:
                    sortedTable(salesArray, salesDate);
                    break;
                case 7:
                    System.out.print("Program has been terminated");
                    System.exit(0);
                    break;
            }
        }
    }
    public static int menu(){
        //displays menu and returns the choice the user made
        System.out.print("\n 1. Add Article");
        System.out.print("\n 2. Delete Article");
        System.out.print("\n 3. Show Article");
        System.out.print("\n 4. Sell Item");
        System.out.print("\n 5. Order History");
        System.out.print("\n 6. Sort Order History");
        System.out.print("\n 7. End Program");
        System.out.print("\n Your Choice: ");
        int choice = input();
        return choice;
    }

    public static int input()
    {
        int input = 0;
        //is user inputs something not present in the menu
        while(!userInput.hasNextInt()){
            System.out.print("enter one of the options above: \n");
            userInput.next();
        }
        input = userInput.nextInt();
        return input;
    }

    public static int[][] insertArticles (int[][]articles, int articleNumber, int noOfArticles){
        int oldLength = articles.length;//gets previous array length
        //calls check full to look for empty spots and add new articles - empty spots and populate empty spots
        articles = checkFull(articles, noOfArticles);
        //fills in item quantity and price using a random number generator
        for (int i = oldLength; i < articles.length; i++){
            articles[i][0] = articleNumber++;
            articles[i][1] = (int)(Math.random() * (10 - 1)) +1;
            articles[i][2] = (int)(Math.random() * (1000 - 100)) +100;
        }
        for(int i = 0; i < articles.length; i++){
            //this fills up the empty article spots
            if(articles[i][0] == 0){
                articles[i][0] = articleNumber++;
                articles[i][1] = (int)(Math.random() * (10 - 1)) +1;
                articles[i][2] = (int)(Math.random() * (1000 - 100)) +100;
            }
        }
        return articles;
    }
    public static int[][] checkFull(int[][]articles, int noOfArticles){
        int freeSpot = 0;//number of empty spots
        for(int i = 0; i < articles.length; i++){
            if (articles[i][0] == 0){
                freeSpot++;
            }
        }
        //resizing the array and returning it
        int currentSize;
        currentSize = articles.length;
        //substracts the free spots that will be re populated instead of creating new ones
        int newSize = (currentSize - freeSpot) + noOfArticles;
        int[][] copy = new int [newSize][3];
        System.arraycopy(articles, 0, copy, 0, articles.length);
        return copy;
    }

    public static void printArticles (int[][]articles){
        //prints the articles
        System.out.print("article - quantity - price");
        for (int i = 0; i < articles.length; i++){
            System.out.println();
            for (int j = 0; j < articles[i].length; j++){
                System.out.print(articles[i][j] + " ");
            }
        }
    }
    public static void removeArticle (int[][]articles){
        //removes an article by inserting article number
        System.out.print("please enter the article number you wish to remove: ");
        int articleToRemove = input();
        //checks the index for the inputted item and sets everything to 0
        for(int i = 0; i < articles.length; i++){
            if(articles[i][0] == articleToRemove){
                articles[i][0] = 0;
                articles[i][1] = 0;
                articles[i][2] = 0;
            }
        }
    }
    public static void sellArticle(int[][]sales, Date[] salesDate, int[][]articles) {
        //sells an article
        System.out.print("enter article number of item to sell: ");
        int artnum = input();
        int index = -1;
        //this gets the index of an item
        for (int i = 0; i < articles.length; i++) {
            if (articles[i][0] == artnum) {
                index = i;
            }
        }
        //if no valid item is inputted this will run
        if (index == -1) {
            System.out.println("Item not in stock");
            return;
        }
        //scustracts items sold from stock aviable
        int available = articles[index][1];
        int result = 0;
        System.out.print("how many items have been sold? ");
        int itemToSell = input();
        result = available - itemToSell;
        //give error if more items are sold than there are in stock
        if (result < 0) {
            System.out.print("you cannot sell more items than there are available");
            result = available;
        } else {
            articles[index][1] = result;
            //adds the item number, quantity sold and date to the corresponding arrays
            if (result >= 0) {
                for (int j = 0; j < sales.length; j++) {
                    Date date = new Date();
                    if (sales[j][0] == 0) {
                        sales[j][0] = articles[index][0];
                        sales[j][1] = itemToSell;
                        salesDate[j] = date;
                        break;
                    }
                }
            }
        }

        //if there are 0 items remaining in stock of an item, it will be automatically removed
        for(int k = 0; k < articles.length; k++){
            if(articles[k][1] == 0 && articles[k][0] == artnum){
                System.out.print("An article stock has been completely sold, the article has been removed");
                articles[k][0] = 0;
                articles[k][1] = 0;
                articles[k][2] = 0;
                break;
            }
        }
    }
    public static void printSales(int[][]sales, Date[] salesDate){
        //prints the sales
        for (int i = 0; i < sales.length; i++){
            if(sales[i][0] != 0){
                System.out.println(sales[i][0] + " " + sales[i][1] + " " + salesDate[i]);
            }
        }
    }
    public static void sortedTable(int[][]sales, Date[] salesDate){
        //orders the sales log to ascending order
        int temp;
        int temp2;
        Date temp3;
        for(int i = 0; i < sales.length; i++){
            for(int j = i + 1; j < sales.length; j++){
                if(sales[i][0] > sales[j][0]){
                    temp = sales[i][0];
                    sales[i][0] = sales[j][0];
                    sales[j][0] = temp;
                    temp2 = sales[i][1];
                    sales[i][1] = sales[j][1];
                    sales[j][1] = temp2;
                    temp3 = salesDate[i];
                    salesDate[i] = salesDate[j];
                    salesDate[j] = temp3;
                }
            }
        }
    }
}