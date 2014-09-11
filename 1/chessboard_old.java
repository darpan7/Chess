/*
 * chessboard.java
 *
 * Created on August 2, 2008, 12:37 PM
 */
 import java.awt.*;
 import java.awt.geom.*;
 import javax.swing.*;
/**
 *
 * @author Administrator
 */
public class chessboard {
    
  public static void main(String[] args)
     {
        DrawFrame frame = new DrawFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.show();
     }      
    
}
/**
     A frame that contains a panel with drawings
 */
 class DrawFrame extends JFrame
 {
     static int one_for_all=700; //decides the width and heigth ofthe frame
     Toolkit kit = Toolkit.getDefaultToolkit();
       Dimension screenSize = kit.getScreenSize();
       public int screenHeight = screenSize.height;
       public int screenWidth = screenSize.width;
      
     public DrawFrame()
     {            
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
        // center frame in screen       
       setLocation(screenWidth / 4, screenHeight / 4);
       setResizable(true);      
       setTitle("A Standard Chess Board");       
       setUndecorated(false);//try changing TRUE | FALSE       
       /*The title bar, title, frame border, close(X) button everything will be removed
         What you will see will just be a plain frame,a plain area.
        */
        // add panel to frame
        DrawPanel panel = new DrawPanel();
        Container contentPane = getContentPane();
        contentPane.add(panel);
     }
     public static final int FRAME_WIDTH = one_for_all;
     public static final int FRAME_HEIGHT = one_for_all;
 }
 /**
     A panel that displays rectangles and ellipses.
 */
 class DrawPanel extends JPanel
{    
    public void paintComponent(Graphics g)
    {
       DrawFrame frame = new DrawFrame();
       int XXX=25;//x coordinate of the point where drawing starts
       int YYY=25;//x coordinate of the point where drawing starts
       System.out.println(XXX);
       int co_x=XXX;
       int co_y=YYY;
       int no=8;   // value of no should be at the most frameSize/10
       int size=80; //size of each box
       boolean flag=false;
       setBackground(new Color(80,40,40));
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D)g;       
       for(int j=0;j<no;j++)
       {
       if(j%2==0)
        {flag=false;}
       else if(j%2==1)
        {flag=true;}  
       co_x=XXX;
       for(int i=0;i<no;i++)
        {                
        if(flag==true)
        {
        g2.setPaint(Color.WHITE);
        g2.fill(new Rectangle2D.Double(co_x,co_y,size,size));
        flag=false;
        }
        else
        {
        g2.setPaint(Color.BLACK);
        g2.fill(new Rectangle2D.Double(co_x,co_y,size,size));
        flag=true;
        }
        co_x+=size;
       }                       
       co_y+=size;
       }
    }
}