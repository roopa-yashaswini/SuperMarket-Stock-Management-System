/*
    Objective of this object:
        runs a page(thread) of "Bill" object and updates the stock in a synchronised manner
*/

public class BillThread extends Thread{
    public void run(){
        Bill billing=new Bill();
        billing.setVisible(true);
    }
}
