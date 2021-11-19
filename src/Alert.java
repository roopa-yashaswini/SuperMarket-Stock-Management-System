import java.io.*;
import java.util.ArrayList;

/*
    Objective of this object:
    Calculates the number of items in the super market whose "Stock<10"
 */

public class Alert {
    int count=0;
    Obj1 o1=new Obj1();
    Products_Info info=new Products_Info();

    int update() throws IOException{
        count=0;
        ArrayList<String> items=info.name();
        for(int i=0;i<items.size();i++){
            if(o1.getStock(items.get(i))<10)
                count++;
        }
        return count;
    }
}