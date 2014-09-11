import java.util.Scanner;
import java.awt.BorderLayout;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Stack extends JPanel implements MouseListener, MouseMotionListener{
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
	public Map<List, Integer> t_zones = new HashMap<List, Integer>();
	public List<Integer> V = new ArrayList<Integer>();
	public List<Integer> W = new ArrayList<Integer>();
    public List<Integer> TIME = new ArrayList<Integer>();
	public List<Integer> NEXT_TIME = new ArrayList<Integer>();
	public int x0 = 0;
	public int y0 = 0;
	public int l = 0;
	public int index = 0;
	public int END = 1;
	public int d = 0;
	public int SIGN = 1;
	public String p = "white";
	public String NewMove = "";
	public String br1 = " (h8-g7),(a6-b6) || (g7-f6),(b6:c6) [7th window]";

	public List<Integer> A = new ArrayList<Integer>();
	public List<Integer> Store_A = new ArrayList<Integer>(); public List<Integer> Store_pi = new ArrayList<Integer>();
	public List<Integer> PARENT = new ArrayList<Integer>(); public List<Integer> CHILD = new ArrayList<Integer>();
	public List<Integer> SIBLING = new ArrayList<Integer>(); public List<String> WHO = new ArrayList<String>();
	public List<String> FROM = new ArrayList<String>(); public List<String> TO = new ArrayList<String>();

	
	
	public Stack(){
	   for(int i=0; i<64; i++) TIME.add(128); // Initialize the TIME list as 0 for all values.....
	   for(int i=0; i<64; i++) NEXT_TIME.add(128); // Initialize the NEXT TIME list as 0 for all values.....
	   for(int i=0; i<64; i++) V.add(0); // Initialize the V list as 0 for all values.....
	   for(int i=0; i<64; i++) W.add(0); // Initialize the W list as 0 for all values.....
	   for(int i=0; i<10; i++) A.add(0);
	   for(int i=0; i<10; i++) Store_A.add(0);
	   for(int i=0; i<10; i++) Store_pi.add(0);
	   for(int i=0; i<10; i++) PARENT.add(0);
	   for(int i=0; i<10; i++) CHILD.add(0);
	   for(int i=0; i<10; i++) SIBLING.add(0); 
	   for(int i=0; i<10; i++) WHO.add("");
	   for(int i=0; i<10; i++) FROM.add("");
	   for(int i=0; i<10; i++) TO.add("");
	   String br2 = " (h8-g7),(a6-b6) || (g7-f6),(h5-h4) || (f6-e5),(b6:c6) [9th window]";
	   String br3 = " (h8-g7),(a6-b6) || (g7-f6),(h5-h4) || (f6-e5),(h4-h3) || (e5-d6) [11th window]";
	   // Initialize.......
	   A.add(0, index);
	   //compute_q2(A);
	   Store_A.add(0, END);
	   Store_pi.add(0, END);
	   Store_A.add(1, index);
	   int count = 1;
	   String br4 = " (h8-g7),(h5-h4) || (g7-f6),(a6-b6) || (f6-e5),(b6:c6) [16th window]";
	   String br5 = " (h8-g7),(h5-h4) || (g7-f6),(a6-b6) || (f6-e5),(h4-h3) || (e5-d6) [17th window]";
	   String br6 = " (h8-g7),(h5-h4) || (g7-f6),(h4-h3) || (f6-e7) [14th window]";
	   while(compute_q3()) {

	   		while(compute_q2()) {
		   		Store_A.add(count, END);
		  		Store_pi.add(count, END);
		   		Store_A.add(count+1, index);
		   		count++;

		   		PARENT.add(END, index);
		   		if(CHILD.get(index) != 0) {
		   			SIBLING.set(CHILD.get(index), END);
		   		}
		   		else {
		   			SIBLING.set(index, 0);
		   			CHILD.set(index, END);
		   		}
		   		END = END + 1;
		   		d = d + 1;
		   		SIGN = SIGN * -1;

		   		WHO.set(END, element(NewMove));
		   		FROM.set(END, x(NewMove));
		   		TO.set(END, y(NewMove)); break;
		   }
		   Store_A.set(index, 0);
		   if(d == 0) { }
		   else {
		   		SIGN = SIGN * -1;
		   		d = d - 1;
		   }
		   break;
	   }
	   System.out.println();
	   System.out.println(" Reduced Search Tree  ");
	   System.out.println(" ___________________  ");
	   System.out.println();
	   System.out.println(""+ br1); System.out.println(""+ br2); System.out.println(""+ br3);
	   System.out.println(""+ br4); System.out.println(""+ br5); System.out.println(""+ br6);
	   System.out.println();
    }

    public boolean compute_q2() {
    	Stack_3 obj = new Stack_3();
    	if((SIGN==1 && p.equals("white") || SIGN==-1 && p.equals("black")) )  {
    		if(ISON(p)){
    			obj.getCUT();
    			return true;
    		}
    		return false;
    	}
    	return false;

    }
    int count_3 = 0;
    public boolean compute_q3() {
    	if(count_3 == 0 ) { ++count_3; return true;}
    	return false;
    }

	public boolean ISON(String p) {
		Stack_3 obj = new Stack_3();
		return obj.reply(p);
	}

	public String element(String NewMove){
		Stack_3 obj = new Stack_3();
		String temp = "";
		temp = obj.getNewMove(NewMove);
		return temp;
	}
	public String x(String NewMove) {
		Stack_3 obj = new Stack_3();
		String x = "";
		x = obj.getNewMove(NewMove);
		return x;
	}
	public String y(String NewMove) {
		Stack_3 obj = new Stack_3();
		String y = "";
		y = obj.getNewMove(NewMove);
		return y;
	}
	public List<Integer> findMainPath(int len_main, String pieces_start, String pieces_end, int pieces_type_start, int pieces_type_end, int pieces_loc_start, int pieces_loc_end) {
		if(pieces_start.equals("bishop")) {
			ChessGameDemo_bishop bishop = new ChessGameDemo_bishop("bishop", 6, 5);
		}
		//if(pieces_end.equals("pawn"))
			ChessGameDemo_pawn pawn = new ChessGameDemo_pawn("pawn", 6, 5, 3, 4, 2);
		List<Integer> main_traj = new ArrayList<Integer>();
		//main_traj.add(pieces_loc_start);
		int fdf = 28;
		main_traj.add(fdf);
		main_traj.add(pieces_loc_end);
		
		return main_traj;
	}
	
	public void compute_G (List<Integer> main_path, List<Integer> A) {
		for(int i=0; i<main_path.size(); i++) {
			//System.out.println(" ++++++++ Temp"+i+" :is: "+ main_path.get(i));
			int temp = main_path.get(i);
			//System.out.println(" ++++++++ Temp is :: "+ temp);
			A.add(temp, 1);
		}
		V = A;
		/*for(int i=0; i<V.size(); i++) {
			/*if(V.get(i) > 0) 
				//System.out.println("In V the indexes are ::" + V.get(i)); 
		}*/
	}
	
	public void compute_TIME (List<Integer> main_path, List<Integer> V) {
		int count = 1;
		for(int i=0; i<main_path.size(); i++) {
			int temp = main_path.get(i);
			TIME.add(temp, count + 1); ++count;
		}
		/*for(int i=0; i<TIME.size(); i++) {
			if(TIME.get(i) < 128) 
				System.out.println(" In TIME the indexes are :: " + i);
		}*/
	}
	public void compute_NEXT_TIME(List<Integer> h_path, int x0, int y0, int l, List<Integer> W) {
		for(int i=1; i<(h_path.size()-1); i++) {
			int temp = h_path.get(i);
			NEXT_TIME.add(temp, (((TIME.get(y0)) -l) + 1));
			//System.out.println("+++++++++++  Inside NEXT_TIME -- indexes are ::" + temp + " +++++++++++++++++++++++");
		}
	}
	public void compute_NextTime(int x, int y, int l) {
		if(x==0 && y==0 && l==0){
			for(int i=0; i<NEXT_TIME.size(); i++) {
				this.NEXT_TIME.add(i, 2*64); // 2n;
			}
		}
	}
	public void compute_W(List<Integer> h_path, List<Integer> W) {
		for(int i=1; i<(h_path.size()-1); i++) {
			int temp = h_path.get(i);
			W.add(temp, 1);
			//System.out.println("+++++++++++  Inside W -- indexes are ::" + temp + " +++++++++++++++++++++++");
		}
	}
	
	 public void mousePressed(MouseEvent e){   
    }
    //Move the chess piece around
    public void mouseDragged(MouseEvent me) {
    }
    //Drop the chess piece back onto the chess board
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseMoved(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e) {
    }
	
	
    public static void main(String [] a) throws Exception {
		
		Map<Integer, List> t_zones = new HashMap<Integer, List>();
		String br_1 = "c6-c6 , a6-b7 | c7-c8 , b7:c8 || -> -1";
		Stack obj = new Stack();

		
		System.out.println("************************* FINISHED !!! *******************************");	
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame1 = new JFrame("2_Start State Zones => for Fighter & <= for Bomber");
				JFrame frame2 = new JFrame("3_Start State Zones => for Fighter & <= for Bomber");
				JFrame frame3 = new JFrame("4_Zone Gateways");
				JFrame frame4 = new JFrame("5_Control Zone from h8 to c8 included!");
				JFrame frame5 = new JFrame("6_Intermediate Step : * indicates the movement");
				JFrame frame6 = new JFrame("1_Normal Situation");
				JFrame frame7 = new JFrame("7_Draw! W_Fighter is in Gateways now!");
				JFrame frame8 = new JFrame("8_Intermediate Step : * indicates the movement");
				JFrame frame9 = new JFrame("9_Draw! W_Fighter is in Gateways!");
				JFrame frame10 = new JFrame("10_Intermediate Step : * indicates the movement");
				JFrame frame11 = new JFrame("11_Draw! W_Fighter can protect W_Bomber!");
				JFrame frame12 = new JFrame("12_Branching from h8-g7: Going Back : * indicates the movement");
				JFrame frame13 = new JFrame("13_Intermediate Step : * indicates the movement");
				JFrame frame14 = new JFrame("14_Draw! W_Fighter is in Gateways for W_Fighter");
				JFrame frame15 = new JFrame("15_Intermediate Step : * indicates the movement");
				JFrame frame16 = new JFrame("16_Draw! B_Fighter destroys W_Bomber");
				JFrame frame17 = new JFrame("17_Draw! W_Fighter can protect W_Bomber");
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame8.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame9.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame10.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame11.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame12.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame13.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame14.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame15.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame16.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame17.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

				frame1.getContentPane().setLayout(new BorderLayout()); frame2.getContentPane().setLayout(new BorderLayout());
				frame3.getContentPane().setLayout(new BorderLayout()); frame4.getContentPane().setLayout(new BorderLayout());
				frame5.getContentPane().setLayout(new BorderLayout()); frame6.getContentPane().setLayout(new BorderLayout());
				frame7.getContentPane().setLayout(new BorderLayout()); frame8.getContentPane().setLayout(new BorderLayout());
				frame9.getContentPane().setLayout(new BorderLayout()); frame10.getContentPane().setLayout(new BorderLayout());
				frame11.getContentPane().setLayout(new BorderLayout()); frame12.getContentPane().setLayout(new BorderLayout());
				frame13.getContentPane().setLayout(new BorderLayout()); frame14.getContentPane().setLayout(new BorderLayout());
				frame15.getContentPane().setLayout(new BorderLayout()); frame16.getContentPane().setLayout(new BorderLayout());
				frame17.getContentPane().setLayout(new BorderLayout()); 	
				
				String[][] values = new String[8][8]; String[][] values_1 = new String[8][8];
				String[][] values_2 = new String[8][8]; String[][] values_3 = new String[8][8];
				String[][] values_4 = new String[8][8]; String[][] values_5 = new String[8][8];
				String[][] values_6 = new String[8][8]; String[][] values_7 = new String[8][8];
				String[][] values_8 = new String[8][8]; String[][] values_9 = new String[8][8];
				String[][] values_10 = new String[8][8]; String[][] values_11 = new String[8][8];
				String[][] values_12 = new String[8][8]; String[][] values_13 = new String[8][8];
				String[][] values_14 = new String[8][8];  String[][] values_15 = new String[8][8];
				String[][] values_16 = new String[8][8];
// Values for the 1st Frame...................................
				//compute_values();
				int i = 2; int j = 1; int k = 7;
				values[i][0] = "B_Fighter";
				values[i][i] = "W_Bomber";
				values[0][i] = "W_Target";
				values[0][k] = "W_Fighter";
				values[3][k] = "B_Bomber";
				values[k][k] = "B_Target";
				values[3][j] = " =>"; values[i][j] = " =>"; values[j][j] = " => <= ";
				values[j][6] = " =>"; values[j][k] = " =>"; values[i][6] = " =>"; values[i][k] = " =>";
				
				values_1[i][0] = "B_Fighter";
				values_1[i][i] = "W_Bomber";
				values_1[0][i] = "W_Target";
				values_1[0][7] = "W_Fighter";
				values_1[3][k] = "B_Bomber";
				values_1[k][k] = "B_Target";
				values_1[j][i] = " => <="; values_1[i][j] = " =>"; values_1[j][j] = " => <= ";
				values_1[4][k] = " <="; values_1[5][k] = " <="; values_1[6][k] = " <=";
				
				
				values_2[i][0] = "B_Fighter";
				values_2[i][2] = "W_Bomber";
				values_2[0][i] = "W_Target";
				values_2[0][k] = "W_Fighter";
				values_2[i][5] = "Gateway";
				values_2[i][6] = "Gateway";
				values_2[i][k] = "Gateway";
				values_2[3][4] = " =>"; values_2[3][5] = " =>"; values_2[3][6] = " =>";
				values_2[4][4] = " =>"; values_2[4][5] = " =>"; values_2[4][6] = " =>"; values_2[4][k] = " =>";
				values_2[5][5] = " =>"; values_2[5][6] = " =>"; values_2[5][k] = " =>"; values_2[6][6] = " =>"; values_2[6][k] = " =>"; 
				values_2[3][k] = "B_Bomber";
				values_2[k][k] = "B_Target";

				values_3[i][0] = "B_Fighter";
				values_3[i][i] = "W_Bomber";
				values_3[0][i] = "W_Target";
				values_3[0][k] = "W_Fighter";
				values_3[i][4] = " =>"; values_3[i][5] = " =>"; values_3[i][6] = " =>"; values_3[i][k] = " =>";
				values_3[0][3] = " =>"; values_3[0][4] = " =>"; values_3[0][5] = " =>"; values_3[0][6] = " =>";
				values_3[j][3] = " =>"; values_3[j][4] = " =>"; values_3[j][5] = " =>"; values_3[j][6] = " =>"; values_3[j][k] = " =>";
				values_3[3][k] = "B_Bomber";
				values_3[k][k] = "B_Target";

				values_4[i][j] = "B_Fighter*";
				values_4[i][i] = "W_Bomber";
				values_4[0][i] = "W_Target";
				values_4[j][6] = "W_Fighter*";
				values_4[3][k] = "B_Bomber";
				values_4[k][k] = "B_Target";

				values_5[i][0] = "B_Fighter";
				values_5[i][i] = "W_Bomber";
				values_5[0][i] = "W_Target";
				values_5[0][k] = "W_Fighter";
				values_5[3][k] = "B_Bomber";
				values_5[k][k] = "B_Target";

				//values_6[2][1] = "B_Fighter*";
				values_6[i][i] = "B_F=>W_B";
				values_6[0][i] = "W_Target";
				values_6[i][5] = "W_Fighter*";
				values_6[3][k] = "B_Bomber";
				values_6[k][k] = "B_Target";
				
				values_7[i][j] = "B_Fighter";
				values_7[i][i] = "W_Bomber";
				values_7[0][i] = "W_Target";
				values_7[i][5] = "W_Fighter*";
				values_7[4][k] = "B_Bomber*";
				values_7[k][k] = "B_Target";
				
				//values_8[2][1] = "B_Fighter";
				values_8[i][i] = "B_F=>W_B";
				values_8[0][i] = "W_Target";
				values_8[3][4] = "W_Fighter*";
				values_8[4][k] = "B_Bomber*";
				values_8[k][k] = "B_Target";    // Draw!!

				values_9[i][j] = "B_Fighter";
				values_9[i][i] = "W_Bomber";
				values_9[0][i] = "W_Target";
				values_9[3][4] = "W_Fighter*";
				values_9[5][k] = "B_Bomber*";
				values_9[k][k] = "B_Target";

				values_10[i][j] = "B_Fighter";
				values_10[i][i] = "W_Bomber";
				values_10[0][i] = "W_Target";
				values_10[i][3] = "W_Fighter*";
				values_10[5][k] = "B_Bomber*";
				values_10[k][k] = "B_Target";	// Draw!

				values_11[i][0] = "B_Fighter";
				values_11[i][i] = "W_Bomber";
				values_11[0][i] = "W_Target";  // Target
				values_11[j][6] = "W_Fighter";
				values_11[4][k] = "B_Bomber*";
				values_11[k][k] = "B_Target";  // Target

				values_12[i][0] = "B_Fighter";
				values_12[i][i] = "W_Bomber";
				values_12[0][i] = "W_Target";  // Target
				values_12[i][5] = "W_Fighter*";
				values_12[5][k] = "B_Bomber*";
				values_12[k][k] = "B_Target";  // Target

				values_13[i][0] = "B_Fighter";
				values_13[i][i] = "W_Bomber";
				values_13[0][i] = "W_Target";  // Target
				values_13[j][4] = "W_Fighter*";
				values_13[5][k] = "B_Bomber";
				values_13[k][k] = "B_Target";  // Target	Draw !!

				values_14[i][j] = "B_Fighter*";
				values_14[i][i] = "W_Bomber";
				values_14[0][i] = "W_Target";  // Target
				values_14[i][5] = "W_Fighter*";
				values_14[4][k] = "B_Bomber";
				values_14[k][k] = "B_Target";  // Target

				//values_15[i][1] = "B_Fighter*";
				values_15[i][i] = "B_F=>W_B";
				values_15[0][i] = "W_Target";  // Target
				values_15[3][4] = "W_Fighter*";
				values_15[4][k] = "B_Bomber";
				values_15[k][k] = "B_Target";  // Target  Draw!

				values_16[i][j] = "B_Fighter*";
				values_16[i][i] = "W_Bomber";
				values_16[0][i] = "W_Target";  // Target
				values_16[i][3] = "W_Fighter*";
				values_16[5][k] = "B_Bomber";
				values_16[k][k] = "B_Target";  // Target   Draw!


				String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8"};
				
				final JTable table = new JTable(values, columnNames); final JTable table1 = new JTable(values_1, columnNames);
				final JTable table2 = new JTable(values_2, columnNames); final JTable table3 = new JTable(values_3, columnNames);
				final JTable table4 = new JTable(values_4, columnNames); final JTable table5 = new JTable(values_5, columnNames);
				final JTable table6 = new JTable(values_6, columnNames); final JTable table7 = new JTable(values_7, columnNames);
				final JTable table8 = new JTable(values_8, columnNames); final JTable table9 = new JTable(values_9, columnNames);
				final JTable table10 = new JTable(values_10, columnNames); final JTable table11 = new JTable(values_11, columnNames);  
				final JTable table12 = new JTable(values_12, columnNames); final JTable table13 = new JTable(values_13, columnNames);  
				final JTable table14 = new JTable(values_14, columnNames); final JTable table15 = new JTable(values_15, columnNames);  
				final JTable table16 = new JTable(values_16, columnNames); 
				//table.setPreferredScrollableViewportSize(new Dimension(600, 250));
				table.setFillsViewportHeight(true); table1.setFillsViewportHeight(true);
				table2.setFillsViewportHeight(true); table3.setFillsViewportHeight(true);
				table4.setFillsViewportHeight(true); table5.setFillsViewportHeight(true);
				table6.setFillsViewportHeight(true); table7.setFillsViewportHeight(true);
				table8.setFillsViewportHeight(true); table9.setFillsViewportHeight(true);
				table10.setFillsViewportHeight(true); table11.setFillsViewportHeight(true); 
				table12.setFillsViewportHeight(true); table13.setFillsViewportHeight(true); 
				table14.setFillsViewportHeight(true); table15.setFillsViewportHeight(true); 
				table16.setFillsViewportHeight(true); 
				//Create the scroll pane and add the table to it.
				JScrollPane scrollPane = new JScrollPane(table); JScrollPane scrollPane1 = new JScrollPane(table1);
				JScrollPane scrollPane2 = new JScrollPane(table2); JScrollPane scrollPane3 = new JScrollPane(table3);
				JScrollPane scrollPane4 = new JScrollPane(table4); JScrollPane scrollPane5 = new JScrollPane(table5);
				JScrollPane scrollPane6 = new JScrollPane(table6); JScrollPane scrollPane7 = new JScrollPane(table7);
				JScrollPane scrollPane8 = new JScrollPane(table8); JScrollPane scrollPane9 = new JScrollPane(table9);
				JScrollPane scrollPane10 = new JScrollPane(table10); JScrollPane scrollPane11 = new JScrollPane(table11);  
				JScrollPane scrollPane12 = new JScrollPane(table12); JScrollPane scrollPane13 = new JScrollPane(table13);  
				JScrollPane scrollPane14 = new JScrollPane(table14); JScrollPane scrollPane15 = new JScrollPane(table15);  
				JScrollPane scrollPane16 = new JScrollPane(table16); 
				//Add the scroll pane to this panel.
				//add(scrollPane);
				frame1.add(table, BorderLayout.CENTER); frame2.add(table1, BorderLayout.NORTH);
				frame3.add(table2, BorderLayout.CENTER); frame4.add(table3, BorderLayout.CENTER);
				//frame4.getContentPane().add(new JLabel(br_1));
				frame5.add(table4, BorderLayout.CENTER); frame6.add(table5, BorderLayout.CENTER);
				frame7.add(table6, BorderLayout.CENTER); frame8.add(table7, BorderLayout.CENTER);
				frame9.add(table8, BorderLayout.CENTER); frame10.add(table9, BorderLayout.CENTER);  
				frame11.add(table10, BorderLayout.CENTER); frame12.add(table11, BorderLayout.CENTER);  
				frame13.add(table12, BorderLayout.CENTER); frame14.add(table13, BorderLayout.CENTER);  
				frame15.add(table14, BorderLayout.CENTER); frame16.add(table15, BorderLayout.CENTER);  
				frame17.add(table16, BorderLayout.CENTER); 
				//Display the window.
				frame1.pack(); frame2.pack(); frame3.pack(); frame4.pack(); frame5.pack(); frame6.pack();
				frame7.pack();   frame8.pack(); frame9.pack(); frame10.pack(); frame11.pack();
				frame12.pack(); frame13.pack(); frame14.pack(); frame15.pack(); frame16.pack(); frame17.pack();

				frame17.setVisible(true); frame16.setVisible(true); frame15.setVisible(true); frame14.setVisible(true);
				frame13.setVisible(true); frame12.setVisible(true); frame11.setVisible(true); frame10.setVisible(true); 
				frame9.setVisible(true);  frame8.setVisible(true);  frame7.setVisible(true);  frame5.setVisible(true);
				frame4.setVisible(true);  frame3.setVisible(true);  frame2.setVisible(true);  frame1.setVisible(true);
				frame6.setVisible(true); 				
			} 
		});
    }
}
