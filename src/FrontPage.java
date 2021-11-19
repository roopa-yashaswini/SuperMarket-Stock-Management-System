import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;

/*
    Objective of this class:
    Front Page provides three options(buttons):
    1. Billing: directs to a new page to bill the items
    2. Stock: directs to a new page to add or remove the stock
    3. Alert: displays a message dialog box that provides an alert if stock is less than 10
*/

public class FrontPage extends JFrame implements ActionListener{
    Alert a=new Alert();
    Obj1 o1=new Obj1();
    Products_Info info=new Products_Info();
    ArrayList<String> items=info.name();
    int count;
    JButton stock=new JButton("Stock");
    JButton bill=new JButton("Billing");
    JButton alert=new JButton("Alerts");
    JLabel no=new JLabel();
    public FrontPage(){
        setSize(260,300);
        setDefaultLookAndFeelDecorated(true);
        setLayout(null);

        add(stock);
        stock.setBounds(50,50,100,40);
        add(bill);
        bill.setBounds(50,120,100,40);
        add(alert);
        alert.setBounds(50,190,100,40);
        add(no);
        no.setBounds(170,203,20,15);

        try{
            count=a.update();
            if(count>0) {
                no.setText(String.valueOf(count));
                no.setForeground(Color.RED);
            }
        }catch(Exception exp) {
            System.out.println(exp);
        }

        bill.addActionListener(this);
        stock.addActionListener(this);
        alert.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==bill){
            BillThread bt1=new BillThread();
            bt1.start();
        }
        if(e.getSource()==stock){
            Stocks stock=new Stocks();
            stock.setVisible(true);
        }
        //reduces the count for every click on Alert button
        if(e.getSource()==alert){
            try {
                for (int i = 0; i < items.size(); i++) {
                    if(o1.getStock(items.get(i)) < 10){
                        JOptionPane.showMessageDialog(this,items.get(i)+"\nThe stock is "+o1.getStock(items.get(i)));
                        count--;
                        if(count<=0)
                            no.setText("");
                        else
                            no.setText(String.valueOf(count));
                    }
                }
            }catch(Exception ex){
                System.out.println(ex);
            }

        }
    }
    public static void main(String[] args){
        new FrontPage();
        try{
            //Serialization to "products.txt" file
            Products_Info p_info=new Products_Info();
            FileOutputStream fout=new FileOutputStream("products.txt");
            ObjectOutputStream oos=new ObjectOutputStream(fout);
            oos.writeObject(p_info);
            oos.flush();
            oos.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}