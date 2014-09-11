import java.util.Scanner;
import java.awt.BorderLayout;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Stack_3 extends JPanel implements MouseListener, MouseMotionListener{
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
	public int end = 0;
	
	public Stack_3(){
       for(int i=0; i<64; i++) TIME.add(128); // Initialize the TIME list as 0 for all values.....
	   for(int i=0; i<64; i++) NEXT_TIME.add(128); // Initialize the NEXT TIME list as 0 for all values.....
	   for(int i=0; i<64; i++) V.add(0); // Initialize the V list as 0 for all values.....
	   for(int i=0; i<64; i++) W.add(0); // Initialize the W list as 0 for all values.....
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

	public boolean reply(String str) {
		return true;
	}

	public String getCUT() {
		return "";
	}

	public String getNewMove(String x) {
		return "returned!";
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
	
	public int findMap_xy(int x, int y, int ln, String piece) {
		if(piece.equals("knight") && x == 55 && y == 28) {
			//System.out.println(" -- Knight in FindMap_xy -- "); 
			return 2;
		}
		else if(ln == 3) {
			if (piece.equals("king") && x == 50 && y == 37) {
				 //System.out.println(" -- Knight in FindMap_xy -- ");
				 return 3;
			}
			else return 100;
		}
		else if(ln == 1 && piece.equals("pawn") && y == 44 && x == 36) return 1;
		else return 100;
		//return 2;
	}
	public List<Integer> find_path(int x, int y, int l, String piece) { 
		List<Integer> main_traj = new ArrayList<Integer>();
		if(piece.equals("knight") && y == 28) {
			main_traj.add(x);
			main_traj.add(38);
			main_traj.add(y);
			//System.out.println("Coming in to the Knight where x = 55 , y == 28,");
		}
		else if(piece.equals("king") && y == 37) {
			main_traj.add(x);
			main_traj.add(43);
			main_traj.add(44);
			main_traj.add(y);
			//System.out.println("Coming in to the King where x = 50 , y == 37");
		}
		else if(piece.equals("pawn") && y == 44 && x == 36 && l == 1) {
			main_traj.add(x);
			main_traj.add(y);
			//System.out.println("Coming in to the Pawn where x = 36 , y == 44");
		}		
		return main_traj;
	}
	
	
	public void compute_f (int x, int y, int l0, List<Integer> V, int flag) {
		if((x < 62 && l0 > 0 ) || ( y == 62 && l0 <= 0 )) {
			x0 = x + 1;
		}
		else if (x == 62 || (l0 <= 0 && y < 62)) {
			x0 = 1;
			y0 = y + 1;
			l = (TIME.get(y+1) * V.get(y+1));
		}
		/*if(flag == 1) {
			for(int i=0; i<V.size(); i++) {
				if(V.get(i) == 1) {
					System.out.println("1 in new V is at :: " + i);
				}
			}
			System.out.println("Checking ------ "+ TIME.get(38)*V.get(38) );
		}*/
	}
	public void resetW() {
		for(int i=0; i<64; i++) {
			W.set(i,0);
		}
	}
	
	public void W_to_V(List<Integer> v, List<Integer> w) {
		for(int i=0; i<w.size(); i++) {
			this.V.set(i, w.get(i));
		}
	}
	
	public void NT_to_T(List<Integer> t, List<Integer> nt) {
		for(int i=0; i<nt.size(); i++) {
			this.TIME.set(i, nt.get(i));
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
		int[] pieces_type = new int[64]; // ************* Pieces Type 0 or 1. white/black *******************
		//for(int i=0; i<=pieces_type.length; i++) {
			//pieces_type = 0;
		//}
		int[] pieces_loc = new int[10]; // ************* Pieces location starting from 0 to 64 *******************
		
		Arrays.fill(pieces_type, 0);
		Arrays.fill(pieces_loc, 0);
		Scanner in = new Scanner(System.in);
		System.out.println("How many elements are in the board? ");
		final int no_of_piece = Integer.parseInt(in.nextLine());
		String[] pieces = new String[10]; // ************* Pieces name *******************
		
		int start_main = 0;
		int start_index_main = 0;
		int start_loc_main = 0;
		int end_main = 0;
		int end_index_main = 0;
		int end_loc_main = 0;
		int white_flag = 0, black_flag = 0;
		Stack_3 stack = new Stack_3();
		for(int i=0; i<no_of_piece; i++) {
			System.out.println("Piece Name?");
			pieces[i] = in.nextLine();
			System.out.println("Enter location of this piece: ");
			pieces_loc[i] = Integer.parseInt(in.nextLine());
			System.out.println("White/Black? Write =>{1/2}:");
			int temp_type = pieces_loc[i];
			pieces_type[temp_type] = Integer.parseInt(in.nextLine());
			if(white_flag < 1) {
				System.out.println("Is this start of Main trajectory? 1 for yes and 2 for No :");
				start_main = Integer.parseInt(in.nextLine());
				if(start_main == 1 ) { // && pieces_loc[i] == 0
					start_index_main = i; white_flag = 1; start_loc_main = pieces_loc[i];
				}
			}
						
			if(black_flag < 1) {
				System.out.println("Is this End of Main trajectory? 1 for yes and 2 for No :");
				end_main = Integer.parseInt(in.nextLine());
				if (end_main == 1 ) { // && pieces_loc[i] == 1
					end_index_main = i; black_flag = 1; end_loc_main = pieces_loc[i];
				}
			}
		}
		System.out.println("------------ Done !!! -----Start is at ::" + start_index_main + "******* End is at ::"+ end_index_main+" ---------");
		System.out.println("Enter the length of main trajectory ::");
		final int len_main = Integer.parseInt(in.nextLine()); // ************************** Length of Main Trajectory ************************
		/*for(int i=0; i<pieces_loc.length; i++) {
			System.out.println("Location of Pieces Array :: " + pieces_loc[i]);
		}
		*/
		
		
		// ************************************** Main Trajectories Method ************************************************		
		List<Integer> main_path = new ArrayList<Integer>(); // *********************** points of Main Trajectory ******************
		List<Integer> h_path = new ArrayList<Integer>(); // ************************* Points added by diff trajectories after *******************
		List<Integer> total_path = new ArrayList<Integer>(); // ************* to merge above two lists **************************
		System.out.println(":*********** Main Trajectories Method ******:");
		
		main_path = stack.findMainPath(len_main, pieces[start_index_main], pieces[end_index_main], pieces_type[start_loc_main], pieces_type[end_loc_main], pieces_loc[start_index_main], pieces_loc[end_index_main]);
			System.out.println(":*********** Size of array is  ******:" + main_path.size());
		// ************************************** Main Trajectories Method ****** Computation of Q2.****************************
			int new_l = len_main + 1;
		stack.t_zones.put(main_path, new_l); // ********************************* t(Tb, Length) ******************************
		stack.compute_G (main_path, stack.V); // **************** computation of V box. it updates V. ***************************
		stack.compute_TIME (main_path, stack.V); // ********************** Computation of TIME *********************************
		
		stack.x0 = 0; stack.y0 = 0; stack.l = 0; // ******************* A(x0, y0, l0) ****************** that keeps vary ****************
		total_path = main_path;
		int flag_q5 = 0;
		int terminate = 0; int flag = 0;
		System.out.println(":*********** DO loop ******:");
		do {
			//System.out.println("x0 is = " +stack.x0 + "y0 is = " + stack.y0 + "length is = " + stack.l );
			// *************************************** Condition Q3 **********************************************************
			if(flag == 1) {
				stack.V = new ArrayList<Integer>(stack.W);
				//stack.W_to_V(stack.V, stack.W);
				//stack.V = stack.W;
				/*System.out.println("size of W is " + stack.W.size());
				System.out.println("size of V is " + stack.V.size());
				*/
				/*for(int i=0; i<stack.V.size(); i++) {
					if(stack.V.get(i) == 1) {
						System.out.println("1 in new V is at :: " + i);
					*/
				//System.out.println(" ******************************************************");
				stack.resetW();
				//System.out.println(" **************** W Reset !!! **********************");
				stack.TIME = new ArrayList<Integer>(stack.NEXT_TIME);
				//stack.NT_to_T(stack.TIME, stack.NEXT_TIME);	
				
			}
			while(stack.x0 < 62 || stack.y0 < 62) {
				//System.out.print(":*********** Inside Q3 ******:");
			// *********************************** Computation of f(u, v) *****************************************************
				if(flag == 1) {
					/*for(int i=0; i<stack.V.size(); i++) {
						if(stack.V.get(i) == 1) {
							System.out.println(" New V is at :: " + i);
						}
					}*/
				}
				stack.compute_f (stack.x0, stack.y0, stack.l, stack.V, flag);	// this function updates the values of x0, y0, and l
				stack.compute_NextTime(stack.x0, stack.y0, stack.l); // This function computes the NextTime for that given u = x0,y0,l for Q3.
				// *********************************** Condition Q4 *********************************************************
				//System.out.println("X0 is " + stack.x0 + "Y0 is " + stack. y0 + "Length is " + stack.l);
				for(int i=0; i<pieces_loc.length; i++) {
					
					if(stack.x0 == pieces_loc[i]) {
						//if(flag == 1) System.out.println("##########" + "x0 is = " +stack.x0 + "y0 is = " + stack.y0 + "length is = " + stack.l );
						if(stack.l > 0 && stack.x0 > 0 && stack.y0 > 0) {
								//System.out.print("2nd if :");
							int x = pieces_type[stack.x0];
							int x1 = pieces_type[start_loc_main];
							int find = stack.findMap_xy(stack.x0, stack.y0, stack.l, pieces[i]);
							if( ( x == x1  && find == 1) || ( ((!( x == x1)) && find <= stack.l ) ) ) {
								//System.out.println("pieces_type[stack.x0] :: " + pieces_type[stack.x0] + " pieces_type[start_loc_main] ::" + pieces_type[start_loc_main]);
								//System.out.println(" FindMap_xy() is :: " + stack.findMap_xy(stack.x0, stack.y0, stack.l, pieces[i]));
								System.out.println(":*********** Inside Q4 ******:");
								System.out.println("X0 is " + stack.x0 + " # Y0 is " + stack.y0 + " # Length is " + stack.l + "Piece is :"+ pieces[i]);
							
							h_path = stack.find_path(stack.x0, stack.y0, stack.l, pieces[i]);
							//System.out.println("Size of t(knight) is :: "+ h_path.size());
							stack.t_zones.put(h_path, stack.TIME.get(stack.y0));
							for(int j=0; j<h_path.size(); j++) {
								total_path.add(h_path.get(j));
							}
							stack.compute_W(h_path, stack.W); // ************** Updates W *****************
							stack.compute_NEXT_TIME(h_path, stack.x0, stack.y0, stack.l, stack.W); // ****** Updates NEXT_TIME *********
							
						}
					}
				}
					/*else {
					// Goes again to check Q3. Here Q4 fails...
					}*/
				}
				//if(stack.y0 > 30 && stack.y0 < 32) {System.out.println("Y0 is more than 30 ");}
				//System.out.println("*********** Outside Q4 Condition *****************");
			}
			System.out.println("*********** Outside Q3 Condition *****************");
			for(int i=0; i<stack.W.size(); i++) {
				if(stack.W.get(i) > 0){
					flag_q5 = 1; break;
				}
				else flag_q5 = 0;
			}
			if(flag_q5 == 1) { // ******************************* Q5 Success *****************************
				System.out.println(":*********** Inside Q5******:");
				stack.x0 = 0;
				stack.y0 = 0;
				stack.l = 0;
				//if(stack.V.size() == stack.W.size())  Collections.copy(stack.V, stack.W);
				/*stack.W_to_V(stack.V, stack.W);
				//stack.V = stack.W;
				System.out.println("size of W is " + stack.W.size());
				System.out.println("size of V is " + stack.V.size());
				for(int i=0; i<stack.V.size(); i++) {
					if(stack.V.get(i) == 1) {
						System.out.println("1 in new V is at :: " + i);
					}
				}
				System.out.println(" ******************************************************");
				stack.resetW();
				System.out.println(" **************** W Reset !!! **********************");
				stack.NT_to_T(stack.TIME, stack.NEXT_TIME);
				//stack.TIME = stack.NEXT_TIME;
				System.out.println(" **************** TIME RESET !!! **********************");
				/*for(int i=0; i<stack.TIME.size(); i++) {
					if(stack.TIME.get(i) == 1) {
						System.out.println("1 in new TIME is at :: " + i);
					}
				}*/
				flag = 1;
				//terminate = 1;
			}
			else {
				System.out.println("*********** Terminate *****************");
				for(Map.Entry<List, Integer> me11:stack.t_zones.entrySet()) {
					List temp_list = me11.getKey();
					System.out.print(" Trajectories are ");
					for(int i=0; i<temp_list.size(); i++) {
						System.out.print(""+ temp_list.get(i) + " , ");
					}
					
					System.out.print(" && Values are ::" + me11.getValue());
					System.out.println();
				}
				terminate = 1;
			}
				
			
		} while(terminate == 0);	
		// ******************************************* Condition 6 *******************************************************
		System.out.println("************************* FINISHED !!! *******************************");	
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame1 = new JFrame("LG Initialize");
				JFrame frame2 = new JFrame("LG Negation 1");
				JFrame frame3 = new JFrame("LG Negation 1");
				JFrame frame4 = new JFrame("LG Negation 2");
				JFrame frame5 = new JFrame("LG Negation 2");
				JFrame frame6 = new JFrame("LG Negation 3");
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.getContentPane().setLayout(new BorderLayout());
				frame2.getContentPane().setLayout(new BorderLayout());
				frame3.getContentPane().setLayout(new BorderLayout());
				frame4.getContentPane().setLayout(new BorderLayout());
				frame5.getContentPane().setLayout(new BorderLayout());
				frame6.getContentPane().setLayout(new BorderLayout());
				
				
				String[][] values = new String[8][8];
				String[][] values_1 = new String[8][8];
				String[][] values_2 = new String[8][8];
				String[][] values_3 = new String[8][8];
				String[][] values_4 = new String[8][8];
				String[][] values_5 = new String[8][8];

				values[7][1] = "King_white";
				values[6][5] = "bishop (START)";
				values[5][2] = "Pawn_white";
				values[3][3] = "Pawn_white";
				values[3][4] = "Pawn (END)";
				values[1][1] = "King_black";
				values[1][6] = "Knight_black";
				
				values_1[6][5] = "bishop (START)";
				values_1[4][3] = "2";
				values_1[3][5] = "2";
				values_1[3][4] = "Pawn (END)";
				values_1[1][6] = "Knight_black";
				
				values_2[6][5] = "bishop (START)";
				values_2[4][3] = "2";
				values_2[2][4] = "2";
				values_2[3][4] = "Pawn (END)";
				values_2[1][6] = "Knight_black";

				values_3[6][5] = "bishop (START)";
				values_3[1][2] = "3";
				values_3[2][3] = "3";
				values_3[3][4] = "Pawn (END)";
				values_3[1][1] = "King_black";

				values_4[6][5] = "bishop (START)";
				values_4[2][2] = "3";
				values_4[3][3] = "3";
				values_4[3][4] = "Pawn (END)";
				values_4[1][1] = "King_black";

				values_5[6][5] = "bishop (START)";
				values_5[2][3] = "1";
				values_5[3][3] = "1";
				values_5[3][4] = "Pawn (END)";
				

				String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8"};
				
				final JTable table = new JTable(values, columnNames);
				final JTable table1 = new JTable(values_1, columnNames);
				final JTable table2 = new JTable(values_2, columnNames);
				final JTable table3 = new JTable(values_3, columnNames);
				final JTable table4 = new JTable(values_4, columnNames);
				final JTable table5 = new JTable(values_5, columnNames);
				//table.setPreferredScrollableViewportSize(new Dimension(600, 250));
				table.setFillsViewportHeight(true);
				table1.setFillsViewportHeight(true);
				table2.setFillsViewportHeight(true);
				table3.setFillsViewportHeight(true);
				table4.setFillsViewportHeight(true);
				table5.setFillsViewportHeight(true);

				//Create the scroll pane and add the table to it.
				JScrollPane scrollPane = new JScrollPane(table);
				JScrollPane scrollPane1 = new JScrollPane(table1);
				JScrollPane scrollPane2 = new JScrollPane(table2);
				JScrollPane scrollPane3 = new JScrollPane(table3);
				JScrollPane scrollPane4 = new JScrollPane(table4);
				JScrollPane scrollPane5 = new JScrollPane(table5);

				//Add the scroll pane to this panel.
				//add(scrollPane);
				frame1.add(table, BorderLayout.CENTER);
				frame2.add(table1, BorderLayout.NORTH);
				frame3.add(table2, BorderLayout.CENTER);
				frame4.add(table3, BorderLayout.CENTER);
				frame5.add(table4, BorderLayout.CENTER);
				frame6.add(table5, BorderLayout.CENTER);
				//Display the window.
				frame1.pack();
				frame2.pack();
				frame3.pack();
				frame4.pack();
				frame5.pack();
				frame6.pack();
				frame1.setVisible(true);
				frame2.setVisible(true);
				frame3.setVisible(true);
				frame4.setVisible(true);
				frame5.setVisible(true);
				frame6.setVisible(true);
			}
		});
    }
}
