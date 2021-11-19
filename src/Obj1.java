import java.io.*;

/*
    Objective of this page:
        This object consists of 2 functions:
        "GetStock": reads the data from text-file and returns the stock of the Required Item.
        "UpdateStock": reads the data from text-file and writes the value of the updated stock of the Required Item
 */

public class Obj1{
    //'name' refers to Required Item name
    int getStock(String name) throws IOException{
        FileReader fr = new FileReader("stock.txt");
        BufferedReader br=new BufferedReader(fr);
        String s=br.readLine();
        while(s!=null){
            if(s.contains(name))
                break;
            else
                s=br.readLine();
        }
        String str=s.substring(s.indexOf(" ")+1);
        int st=Integer.parseInt(str);
        br.close();
        fr.close();
        return st;
    }

    //'name' refers to Required Item name and 'st' refers to new quantity
    void updateStock(String name,int st) throws IOException{
        FileReader fr = new FileReader("stock.txt");
        BufferedReader br=new BufferedReader(fr);
        String line=br.readLine();
        String old="";
        while(line!=null){
            if(line.contains(name))
                line=name+" "+String.valueOf(st);
            old=old+line+System.lineSeparator();
            line=br.readLine();
        }
        br.close();
        fr.close();
        FileWriter fw=new FileWriter("stock.txt");
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(old);
        bw.close();
        fw.close();
    }
}