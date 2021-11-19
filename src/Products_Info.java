import java.util.ArrayList;
import java.io.Serializable;

/*
    Objective of this object:
        Stores an array-list of Items and array-list of the corresponding prices.
       *** To add a New Item into the Super-Market:
           **First add the name in the "stock.txt" text-file along with stock.
            Example:
                Item_name 0

                Name(space)quantity
           ** Later add the Item-Name in the name() function
           ** Add the its price in prices() function
 */

public class Products_Info implements Serializable{
    public ArrayList<String> name() {
        ArrayList<String> col = new ArrayList<String>();
        col.add("Chocolates");
        col.add("Cookies");
        col.add("Cakes");
        col.add("Chips");
        return col;
    }

    public ArrayList<Integer> prices(){
        ArrayList<Integer> price=new ArrayList<Integer>();
        price.add(100);
        price.add(200);
        price.add(50);
        price.add(10);
        return price;
    }
}
