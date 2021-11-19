import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.NumberFormatException;
import java.util.ArrayList;

/*Objective of this object:
    Creates a page that displays the list of items in the super market along with text fields.
    The quantity should be entered in text field and press "ENTER" to read the quantity.
    After entering all the required quantities, click on "BILL" button to get the total price and to update stock in the text-file.
 */

public class Bill extends JFrame implements ActionListener{
    Alert a=new Alert();
    Obj1 o1=new Obj1();
    Products_Info info=new Products_Info();
    ArrayList<String> items=info.name();
    int[] entered_qt=new int[items.size()];
    int total=0;
    int count=0;
    int[] calc_price=new int[items.size()];
    ArrayList<Integer> costs=info.prices();

    JLabel item=new JLabel("Items");
    JLabel quantity=new JLabel("Quantity");
    JLabel price=new JLabel("Price");
    //creates jlabels and jtextfields according to the number of items in the super market
    JLabel[] tags=new JLabel[items.size()];
    JLabel[] prices=new JLabel[items.size()];
    JTextField[] qts=new JTextField[items.size()];
    JButton bill=new JButton("Bill");

    public Bill(){
        Font f = new Font("TimesRoman",Font.BOLD,20);
        setLayout(null);
        setSize(400,500);

        add(item);
        item.setBounds(20,20,150,50);
        add(quantity);
        quantity.setBounds(175,20,100,50);
        add(price);
        price.setBounds(280,20,50,50);
        int y=75;

        //adding components according to the number of items in the super market
        for(int i=0;i<items.size();i++){
            tags[i]=new JLabel();
            tags[i].setText(items.get(i));
            tags[i].setFont(f);
            tags[i].setBounds(20,y,150,50);
            tags[i].setVisible(true);
            add(tags[i]);

            qts[i]=new JTextField("0",5);
            qts[i].setBounds(165,y,70,40);
            qts[i].setVisible(true);
            add(qts[i]);

            prices[i]=new JLabel();
            prices[i].setBounds(280,y-10,50,50);
            prices[i].setVisible(true);
            add(prices[i]);

            y=y+50;
        }

        add(bill);
        bill.setBounds(150,y,100,40);

        for(int j=0;j<items.size();j++)
            qts[j].addActionListener(this);
        bill.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    //function to bill items in a synchronised manner if more than one billing pages are open
    public synchronized void actionPerformed(ActionEvent e){
        try{
            for(int i=0;i<items.size();i++){
                if(Integer.parseInt(qts[i].getText())<0)
                    JOptionPane.showMessageDialog(this,"Enter only positive numbers");
                else{
                    if(e.getSource()==qts[i]){
                        entered_qt[i]=Integer.parseInt(qts[i].getText());
                        calc_price[i]=entered_qt[i]*costs.get(i);
                        prices[i].setText(String.valueOf(calc_price[i]));
                        total+=calc_price[i];
                    }
                }
            }

            if(e.getSource()==bill){
                int[] stock = new int[items.size()];
                for (int i = 0; i < items.size(); i++) {
                    stock[i] = o1.getStock(items.get(i));
                }
                for (int i = 0; i < items.size(); i++) {
                    if (entered_qt[i] > stock[i]) {
                        JOptionPane.showMessageDialog(this, "The stock is not enough\nThe available stock of " + items.get(i) + " is " + stock[i]);
                        total=total-calc_price[i];
                        count++;
                    }
                }
                if (count == 0) {
                    JOptionPane.showMessageDialog(this, "The total price is " + total);
                    for (int i = 0; i < items.size(); i++) {
                        o1.updateStock(items.get(i), stock[i] - entered_qt[i]);
                    }
                }
                count = 0;
            }

        }
        catch(NumberFormatException no){
            JOptionPane.showMessageDialog(this,"Enter only positive numbers");
        }
        catch(Exception exp){
            System.out.println(exp);
        }
    }
}
