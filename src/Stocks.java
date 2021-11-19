import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.NumberFormatException;
import java.util.ArrayList;

/*
    Objective of this object:
    Displays the list of items along with two text fields each.
    One text-field for "Adding" quantity and another for "Removing" quantity
    Click "ENTER" after entering in each text-field to read the data.
    Finally, click on "Update" to update the stock in the text-file.
*/

public class Stocks extends JFrame implements ActionListener{
    int cnt=0;
    Alert a = new Alert();
    Products_Info info=new Products_Info();
    ArrayList<String> items=info.name();
    int[] ad = new int[items.size()];
    Obj1 o1 = new Obj1();
    JLabel item = new JLabel("Items");
    JLabel add = new JLabel("Add");
    JLabel rem = new JLabel("Remove");
    //creates jlabels and jtextfields according to the number of items in the super market
    JLabel[] tags=new JLabel[items.size()];
    JTextField[] add_tag=new JTextField[items.size()];
    JTextField[] rem_tag=new JTextField[items.size()];
    JButton update = new JButton("Update");

    public Stocks() {
        setLayout(null);
        Font f = new Font("TimesRoman", Font.BOLD, 20);
        setSize(350, 480);

        add(item);
        item.setBounds(30, 0, 50, 50);
        add(add);
        add.setBounds(160, 0, 50, 50);
        add(rem);
        rem.setBounds(250, 0, 50, 50);

        int y=50;
        //adding components according to the number of items in the super market
        for(int i=0;i<items.size();i++){
            tags[i]=new JLabel();
            tags[i].setText(items.get(i));
            tags[i].setFont(f);
            tags[i].setBounds(10,y,150,40);
            tags[i].setVisible(true);
            add(tags[i]);

            add_tag[i]=new JTextField("0",5);
            add_tag[i].setBounds(150,y+5,50,30);
            add_tag[i].setVisible(true);
            add(add_tag[i]);
            add_tag[i].addActionListener(this);

            rem_tag[i]=new JTextField("0",5);
            rem_tag[i].setBounds(250,y+5,50,30);
            rem_tag[i].setVisible(true);
            add(rem_tag[i]);
            rem_tag[i].addActionListener(this);

            y=y+50;
        }

        add(update);
        update.setBounds(120, y, 80, 40);
        update.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    //function to update the stock in the text-file
    public void actionPerformed(ActionEvent e) {
        try{
            for(int i=0;i<items.size();i++){
                if(Integer.parseInt(add_tag[i].getText())<0 || Integer.parseInt(rem_tag[i].getText())<0){
                    JOptionPane.showMessageDialog(this,"Enter only positive numbers");
                }else{
                    if(e.getSource()==add_tag[i] || e.getSource()==rem_tag[i]){
                        ad[i]=Integer.parseInt(add_tag[i].getText());
                        int r=Integer.parseInt(rem_tag[i].getText());
                        ad[i]=ad[i]-r;
                    }
                }
            }
            if(e.getSource()==update){
                for(int j=0;j<items.size();j++)
                    o1.updateStock(items.get(j),o1.getStock(items.get(j))+ad[j]);
                JOptionPane.showMessageDialog(this,"Stock updated");
            }
        }
        catch(NumberFormatException no){
            JOptionPane.showMessageDialog(this, "Enter only positive numbers");
        }
        catch(Exception exp){
            System.out.println(exp);
        }
    }
}