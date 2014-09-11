import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Scanner;
 
public class ChessGameDemo_Queen_adm extends JPanel implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	public Map<String, Integer> moves_15 = new HashMap<String, Integer>();
	Map<String, Integer> moves_15_copy = new HashMap<String, Integer>();
	Map<String, Integer> link = new HashMap<String, Integer>();
	Map<Integer, Integer> real_map = new HashMap<Integer, Integer>();
	int index_map = 1;
	
		
    public ChessGameDemo_Queen_adm(String pi, int x, int y, int x_1, int y_1, int length){
		super(new GridLayout(1,0));
		
		int x_loc =0;
		java.util.List<String> moves = new ArrayList<String>();
		int x_15 = 8;
		int y_15 = 8;
		int stop = 0;
		//java.util.List<String> moves_15 = new ArrayList<String>();
		//Map<String, Integer> moves_15 = new HashMap<String, Integer>();
		int count = 1;
		
		if( pi.equals("queen") ) {
			while (moves_15.size() < 225) {
			
				int y1=y_15, x1=x_15, y11=y_15, x11=x_15;
				// ------------------------------ Vertical Line --------------------------
				Map<String, Integer> moves_temp = new HashMap<String, Integer>();
				if (count > 1) {
					
					for(Map.Entry<String, Integer> me1:moves_15.entrySet()) {
						String key = me1.getKey();
						String[] keys = key.split(",");
						int x_key = Integer.parseInt(keys[0]);
						int y_key = Integer.parseInt(keys[1]);
						for(int i=1; i<=15; i++) {
							x_15 = x_key;
							y1 = y_key + i;
							String key_temp = ""+x_15+","+y1;
							if (y1<16 && y1>0) { 
								for(Map.Entry<String, Integer> me11:moves_15.entrySet()) {
									if(me11.getKey().equals(key_temp)) {
										stop = 1;
									} 
								}
								if( stop == 1) {stop = 0;} else {moves_temp.put(""+x_15+","+y1, count); stop = 0;};
							}
						}
						for(int j=1; j<=15; j++) {
							x_15 = x_key;
							y11 = y_key - j;
							String key_temp = ""+x_15+","+y11;
							if(y11>0 && y11<16 ) {
								for(Map.Entry<String, Integer> me11:moves_15.entrySet()) {
									if(me11.getKey().equals(key_temp)) {
										stop = 1;
									} 
								}
								if( stop == 1) {stop = 0;} else {moves_temp.put(""+x_15+","+y11, count); stop = 0;};
							}
						}
					}
				}
				else {
					for(int i=1; i<=15; i++) {
						if(count<2) y1 = y_15 + i;
						if (y1<16) 
							moves_15.put(""+x_15+","+y1, count);
					}
					for(int j=1; j<=15; j++) {
						if(count<2) y11 = y_15 - j;
						if(y11 >0 )
							moves_15.put(""+x_15+","+y11, count);
					}
				}
				// ------------------------------ Vertical Line -------------------------------
				for(Map.Entry<String, Integer> merge:moves_temp.entrySet()) {
					moves_15.put(merge.getKey(), merge.getValue());
				}
				// ------------------------------ Horizontal Line ------------------------------
				//moves_15.add("---");
				
				for(int i=1; i<=15; i++) {
					if(count<2) x1 = x_15 + i;
					if (x1<16 && count<2) {
						moves_15.put(""+x1+","+y_15, count);
					}
				}
				for(int j=1; j<=15; j++) {
					if(count<2) x11 = x_15 - j;
					if(x11 > 0 && count<2) {
						moves_15.put(""+x11+","+y_15,count);
					}
				}
				// ------------------------------ Horizontal Line ------------------------------
				// ------------------------------ Diagonal Line1 --------------------------------
				//moves_15.add("---");
				for(int i=1; i<=15; i++) {
					if(count<2) {
						x1 = x_15 + i;
						y1 = y_15 + i;
					}
					if (x1<16 && y1<16 && count<2) {
						moves_15.put(""+x1+","+y1, count);
					}
				}
				for(int j=1; j<=15; j++) {
					if(count<2) {
						x11 = x_15 - j;
						y11 = y_15 - j;
					}
					if(x11>0 && y11>0 && count<2) {
						moves_15.put(""+x11+","+y11, count);
					}
				}
				// ------------------------------ Diagonal Line1 --------------------------------
				// ------------------------------ Diagonal Line2 --------------------------------
				//moves_15.add("-*-");
				for(int i=1; i<=15; i++) {
					if(count<2) {
						x1 = x_15 + i;
						y1 = y_15 - i;
					}
					if (x1<16 && y1>0 && count<2) {
						moves_15.put(""+x1+","+y1, count);
					}
				}
				for(int j=1; j<=15; j++) {
					if(count<2) {
						x11 = x_15 - j;
						y11 = y_15 + j;
					}
					if(x11>0 && y11<16 && count<2) {
						moves_15.put(""+x11+","+y11, count);
					}
				}
				// ------------------------------ Diagonal Line2 --------------------------------
				moves_15.put(""+8+","+8, 0);
				count++;
			}
		}
		else {
			System.out.println("This program is specific to Queen only. So, it expects queen::");
		}
	
		System.out.println("SIZE IS::: "+ moves_15.size());
		// --------------------------------------- Match Arrays to GUI Points Using Map ------------------------
		
		// -----------------------------------------------------------------------------------------------------
		for(int k=1; k<=15; k++) {
			for(int l=1; l<=15; l++) {
				link.put(""+l+","+k, index_map);
				++index_map;
			}
		}
		
		for(Map.Entry<String, Integer> move:moves_15.entrySet()) {
			String key = move.getKey();
			
			for(Map.Entry<String, Integer> move_link: link.entrySet()) {
				if(key.equals(move_link.getKey())) {
					real_map.put(move_link.getValue(), move.getValue());
				}
			}
			
		}
	
		Map<Integer, Integer> sor_map = new TreeMap<Integer, Integer>(real_map);
	
		// ---------------------------- JPANEL START - CREATING TABLE --------------------------------------------------------------
		
		Vector v = new Vector(); int in_x = 0, in_y = 0;
		Integer[][] data_values = new Integer[15][15];
		Integer[][] data_values_1 = new Integer[15][15];
		
				for(Map.Entry<Integer, Integer> data_dd: sor_map.entrySet()) {
					data_values[in_x][in_y] = data_dd.getValue();
					data_values_1[in_x][in_y] = data_dd.getValue();
					if(in_y == 14) {
						++in_x;
						in_y = 0;
					}else ++in_y;
					if(in_x == 15) break;
				}
		
       // String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8","9", "10", "11", "12", "13", "14", "15"};
		// ------------------------------------------------------ SUPRIMPOSE 8*8 TABLE ON TO THE 15*15 TALBE ----------------------------------
		int x_diff = (7-x);  int y_diff = (7-y);
		int x_diff1 = (7-x_1); int y_diff1 = (7-y_1);
		System.out.println("XDIFF :" + x_diff+ " YDIFF: " + y_diff+ "XDIFF1 :"+ x_diff1 + "YDIFF :"+ y_diff1);
		Integer[][] sub_data = new Integer[8][8]; int a=0,b=0;
// --------------------------------------------------------- 111 in first location --------------------------------------------------------------------
		if( x_diff == 7) {
			for(int i=0; i<(7-x); i++) {
				for(int j=0; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			int xxx = y_diff+7;
			for(int i=7; i<15; i++) {
				for(int j=0; j<y_diff; j++) {
					data_values[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= 7; i<15; i++) {
					for(int j=xxx; j<15; j++) {
						data_values[i][j] = 111;
					}
				}
			}
		}
		else if(x_diff == 6) {
			for(int i=0; i<(7-x); i++) {
				for(int j=0; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			int xxx = y_diff+7; 
			for(int i=6; i<15; i++) {
				for(int j=0; j<y_diff; j++) {
					data_values[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values[i][j] = 111;
					}
				}
			}
			for(int i= 14; i<15; i++) {
				for(int j=y_diff; j<y_diff+8; j++) {
					data_values[i][j] = 111;
				}
			}
		}
		else if(x_diff == 5) {
			for(int i=0; i<(7-x); i++) {
				for(int j=0; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			int xxx = y_diff+7; 
			for(int i=(7-x); i<15; i++) {
				for(int j=0; j<y_diff; j++) {
					data_values[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>12; i--) {
				for(int j=y_diff; j<y_diff+8; j++) {
					data_values[i][j] = 111;
				}
			}
		
		}
		else if(x_diff == 4) {
			for(int i=0; i<(7-x); i++) {
				for(int j=0; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			int xxx = y_diff+7; 
			for(int i=(7-x); i<15; i++) {
				for(int j=0; j<y_diff; j++) {
					data_values[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(11); i--) {
				for(int j=y_diff; j<y_diff+8; j++) {
					data_values[i][j] = 111;
				}
			}
		
		}
		else if(x_diff == 3) {
			for(int i=0; i<(7-x); i++) {
				for(int j=0; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			int xxx = y_diff+7; 
			for(int i=(7-x); i<15; i++) {
				for(int j=0; j<y_diff; j++) {
					data_values[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(10); i--) {
				for(int j=y_diff; j<y_diff+8; j++) {
					data_values[i][j] = 111;
				}
			}
		
		}
		else if(x_diff == 2) {
			for(int i=0; i<(7-x); i++) {
				for(int j=0; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			int xxx = y_diff+7; 
			for(int i=(7-x); i<15; i++) {
				for(int j=0; j<y_diff; j++) {
					data_values[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(9); i--) {
				for(int j=y_diff; j<y_diff+8; j++) {
					data_values[i][j] = 111;
				}
			}
		
		}
		else if(x_diff == 1) {
			for(int i=0; i<(7-x); i++) {
				for(int j=0; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			int xxx = y_diff+7; 
			for(int i=(7-x); i<15; i++) {
				for(int j=0; j<y_diff; j++) {
					data_values[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(8); i--) {
				for(int j=y_diff; j<y_diff+8; j++) {
					data_values[i][j] = 111;
				}
			}
		
		}
		else if(x_diff == 0) {
			for(int i=0; i<(7-x); i++) {
				for(int j=0; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			int xxx = y_diff+7; 
			for(int i=(7-x); i<15; i++) {
				for(int j=0; j<y_diff; j++) {
					data_values[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(7); i--) {
				for(int j=y_diff; j<y_diff+8; j++) {
					data_values[i][j] = 111;
				}
			}
		
		}
		
// ------------------------------------------------------- 111 END for the first location -------------------------------------------------------
// ------------------------------------------------------- 111 START for the 2nd Location -------------------------------------------------------
		if( x_diff1 == 7) {
			for(int i=0; i<(7-x_1); i++) {
				for(int j=0; j<15; j++) {
					data_values_1[i][j] = 111;
				}
			}
			int xxx_1 = y_diff1+7;
			for(int i=7; i<15; i++) {
				for(int j=0; j<y_diff1; j++) {
					data_values_1[i][j] = 111;
				}
			}
			if((14 - xxx_1)>0) {
				for(int i= 7; i<15; i++) {
					for(int j=xxx_1; j<15; j++) {
						data_values_1[i][j] = 111;
					}
				}
			}
		}
		else if(x_diff1 == 6) {
			for(int i=0; i<(7-x_1); i++) {
				for(int j=0; j<15; j++) {
					data_values_1[i][j] = 111;
				}
			}
			int xxx = y_diff1+7; 
			for(int i=6; i<15; i++) {
				for(int j=0; j<y_diff1; j++) {
					data_values_1[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x_1); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values_1[i][j] = 111;
					}
				}
			}
			for(int i= 14; i<15; i++) {
				for(int j=y_diff1; j<y_diff1+8; j++) {
					data_values_1[i][j] = 111;
				}
			}
		}
		else if(x_diff1 == 5) {
			for(int i=0; i<(7-x_1); i++) {
				for(int j=0; j<15; j++) {
					data_values_1[i][j] = 111;
				}
			}
			int xxx = y_diff1+7; 
			for(int i=(7-x_1); i<15; i++) {
				for(int j=0; j<y_diff1; j++) {
					data_values_1[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x_1); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values_1[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>12; i--) {
				for(int j=y_diff1; j<y_diff1+8; j++) {
					data_values_1[i][j] = 111;
				}
			}
		
		}
		else if(x_diff1 == 4) {
			for(int i=0; i<(7-x_1); i++) {
				for(int j=0; j<15; j++) {
					data_values_1[i][j] = 111;
				}
			}
			int xxx = y_diff1+7; 
			for(int i=(7-x_1); i<15; i++) {
				for(int j=0; j<y_diff1; j++) {
					data_values_1[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x_1); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values_1[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(11); i--) {
				for(int j=y_diff1; j<y_diff1+8; j++) {
					data_values_1[i][j] = 111;
				}
			}
		
		}
		else if(x_diff1 == 3) {
			for(int i=0; i<(7-x_1); i++) {
				for(int j=0; j<15; j++) {
					data_values_1[i][j] = 111;
				}
			}
			int xxx = y_diff1+7; 
			for(int i=(7-x_1); i<15; i++) {
				for(int j=0; j<y_diff1; j++) {
					data_values_1[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x_1); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values_1[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(10); i--) {
				for(int j=y_diff1; j<y_diff1+8; j++) {
					data_values_1[i][j] = 111;
				}
			}
		
		}
		else if(x_diff1 == 2) {
			for(int i=0; i<(7-x_1); i++) {
				for(int j=0; j<15; j++) {
					data_values_1[i][j] = 111;
				}
			}
			int xxx = y_diff1+7; 
			for(int i=(7-x_1); i<15; i++) {
				for(int j=0; j<y_diff1; j++) {
					data_values_1[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x_1); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values_1[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(9); i--) {
				for(int j=y_diff1; j<y_diff1+8; j++) {
					data_values_1[i][j] = 111;
				}
			}
		
		}
		else if(x_diff1 == 1) {
			for(int i=0; i<(7-x_1); i++) {
				for(int j=0; j<15; j++) {
					data_values_1[i][j] = 111;
				}
			}
			int xxx = y_diff1+7; 
			for(int i=(7-x_1); i<15; i++) {
				for(int j=0; j<y_diff1; j++) {
					data_values_1[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x_1); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values_1[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(8); i--) {
				for(int j=y_diff1; j<y_diff1+8; j++) {
					data_values_1[i][j] = 111;
				}
			}
		
		}
		else if(x_diff1 == 0) {
			for(int i=0; i<(7-x_1); i++) {
				for(int j=0; j<15; j++) {
					data_values_1[i][j] = 111;
				}
			}
			int xxx = y_diff1+7; 
			for(int i=(7-x_1); i<15; i++) {
				for(int j=0; j<y_diff1; j++) {
					data_values_1[i][j] = 111;
				}
			}
			if((14 - xxx)>0) {
				for(int i= (7-x_1); i<15; i++) {
					for(int j=(xxx+1); j<15; j++) {
						data_values_1[i][j] = 111;
					}
				}
			}
			for(int i= 14; i>(7); i--) {
				for(int j=y_diff1; j<y_diff1+8; j++) {
					data_values_1[i][j] = 111;
				}
			}
		
		}

// ------------------------------------------------------- 111 END for the 2nd Location ---------------------------------------------------------			
	System.out.println("------------------------SUM is 7 points:----------------------");
		Map<String, Integer> count_dist = new HashMap<String, Integer>();
		Map<String, Integer> count_dist_1 = new HashMap<String, Integer>();
		int i_temp=0, j_temp=0, for_count=0;
		int i_temp_1=0, j_temp_1=0, for_count_1=0;
// ----------------------------------- count_dist START for 1st element 15*15 MATHI 8*8 TABLE MA 111 SIVAY NI VALUE LEVA --------------------------		
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				if(data_values[i][j]<100) {
					i_temp = i;
					j_temp = j;
					for_count = 1;
					count_dist.put(""+i+","+j, data_values[i][j]);
					System.out.println("Break happens at for 1st element: "+i+","+j); break;
				}
			}
			if(for_count == 1) break;
		}
// ----------------------------------- count_dist END for 1st element --------------------------------------------------------------------------
// ----------------------------------- count_dist START for 2nd element --------------------------------------------------------------------------
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				if(data_values_1[i][j]<100) {
					i_temp_1 = i;
					j_temp_1 = j;
					for_count_1 = 1;
					count_dist_1.put(""+i+","+j, data_values_1[i][j]);
					System.out.println("Break happens at for 2nd Element: "+i+","+j); break;
				}
			}
			if(for_count_1 == 1) break;
		}
// +++++++++++++++++++++++++++++++++++++++++ count_dist END for 2nd element +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
// ----------------------------------------- FINAL Array START for 1st ELEMENT ------------------------------------------------------------------		
		int c=0, d=0;
		Integer[][] data_8_values = new Integer[8][8];
		if(for_count == 1) {
			for(int i=i_temp; i<i_temp+8; i++) {
				for(int j=j_temp; j<j_temp+8; j++) {
					if(d<8) data_8_values[c][d] = data_values[i][j];
					++d;
				}
				++c; d=0;
			}
		}
		for(int i=0; i<8; i++){
			for(int j=0;j<8; j++) {
				System.out.print("||"+ data_8_values[i][j]);
			}
		}
// ++++++++++++++++++++++++++++++++++++++++ FINAL Array END for 1st ELEMENT ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	System.out.println("`````````````````````````````` Starting for the 2nd element ````````````````````````````````````````````````````");
// ----------------------------------------- FINAL Array START for 2nd ELEMENT -----------------------------------------------------------------------
		int c_1=0, d_1=0;
		Integer[][] data_8_values_1 = new Integer[8][8];
		if(for_count_1 == 1) {
			for(int i=i_temp_1; i<i_temp_1+8; i++) {
				for(int j=j_temp_1; j<j_temp_1+8; j++) {
					if(d_1<8) data_8_values_1[c_1][d_1] = data_values_1[i][j];
					++d_1;
				}
				++c_1; d_1=0;
			}
		}
		for(int i=0; i<8; i++){
			for(int j=0;j<8; j++) {
				System.out.print("||"+ data_8_values_1[i][j]);
			}
		}
// ++++++++++++++++++++++++++++++++++++++++ FINAL Array END for 2nd ELEMENT ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

// -------------------- START -------- COMBINE TWO ELEMENTS FOR THE SUM IS EQUAL TO GIVEN LENGTH ------------------------------------------------
		String[][] combine__x_y = new String[8][8];
		String[][] admissible__x_y = new String[8][8];
		java.util.List<Integer> dock_x = new ArrayList<Integer>();
		java.util.List<Integer> dock_y = new ArrayList<Integer>();
		
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(data_8_values_1[i][j] + data_8_values[i][j] == length) {
					combine__x_y[i][j] = ""+length;
					admissible__x_y[i][j] = " ";
				}
				else if (data_8_values_1[i][j] + data_8_values[i][j] == (length+1)){
					int dfd = length+1;
					admissible__x_y[i][j] = "DOCK::"+" of length."+ dfd;
					dock_x.add(i); dock_y.add(j);
					//lenght_from_start[lfs] = data_8_values[i][j];
					//++lfs;
				}
				else {
					combine__x_y[i][j] = " ";
					admissible__x_y[i][j] = " ";
				}
			}
		}
		combine__x_y[x][y] = "Start"; combine__x_y[x_1][y_1] = "End"; admissible__x_y[x][y] = "Start"; admissible__x_y[x_1][y_1] = "End";

// ###############   START  ################ ADMISSIBLE TRAJECTORIES GETTING DOCK ITEMS FROM DOCK ARRAY ######################################
	System.out.println(" *************************** CLEAR ABOVE ********************************** ");	
	Integer[][][] master_dock = new Integer[10][8][8]; 
	String[][] grand_dock = new String[8][8];
	/*for(Integer x1x: dock_x) {
		System.out.println("++++++++++++++" + x1x);
	}
	for(Integer y1y: dock_y) {
		System.out.println("---------------" + y1y);
	}*/
	/*
	for(int i=0; i<dock_x.size(); i++) { // DOCK NA BADHA J ELEMENTS MATE LOOP, EACH ITEM MATE AKHU SEARCH ---------------------------------
		int x_d = dock_x.get(i);
		int y_d = dock_y.get(i);
		for(int e=0; e<8; e++) { // CREATING ANOTHER 8*8 TABLE WITH DISTANCES FOR EACH OF THE DOCK ITEMS -------------------
			for(int f=0; f<8; f++) {
				int x_dis = ((x_d - e) == 0 ? 1 : 2);
				int y_dis = ((y_d - f) == 0 ? 1 : 2);
				int diag_x = ((x_d - e) < 0 ? -(x_d-e) : (x_d-e));
				int diag_y = ((y_d - f) < 0 ? -(y_d-f) : (y_d-f));
				
				if(x_dis == 1 || y_dis == 1) master_dock[i][e][f] = x_dis;
				else if (diag_x == diag_y) master_dock[i][e][f] = 1;
				else master_dock[i][e][f] = 2;
			}
		}
		/*for(int i=0; i<4; i++) {
			for(int j=0; j<8; j++) {
				for(int k=0; k<8; k++) {
					System.out.prtinln(""+j+","+k+"=="+master_dock[i][j][k] );
				}
			}// break;
		}*/
/*		master_dock[i][x_d][y_d]= 0;
		if (master_dock[i][x_1][y_1] == 1) {
			
		}
		else {
			for(int g=0; g<8; g++) { // COMPARING VALUES OF MASTER_DOCK AND TARGET AND TAKING ONLY THOSE WHO HAVE LENGTH VALUES -------------------
				for(int h=0; h<8; h++) {
					if(data_8_values_1[g][h] + master_dock[i][g][h] == length) {
						int llll = data_8_values[g][h];
						//System.out.println(" +++++++++ Matched !!! +++++++++++");
						if (grand_dock[g][h] == null) {grand_dock[g][h] = ""+i+"th dock, "+llll+" dis from start- "; }
						else {
							grand_dock[g][h] = ""+grand_dock[g][h]+":"+i+"th dock, "+llll+" dis from start- "; 
						}
					}
				}
			}
		}
		if(grand_dock[x_d][y_d] == null) grand_dock[x_d][y_d] = ""+"-DOCK-"+i;
		else grand_dock[x_d][y_d] = " "+grand_dock[x_d][y_d]+"-DOCK-"+i; 
		//break;
	}*/
	 grand_dock[x][y] = "Start"; grand_dock[x_1][y_1] = "End";

// ###############   END  ################ ADMISSIBLE TRAJECTORIES GETTING DOCK ITEMS FROM DOCK ARRAY ######################################

// ++++++++++++++++++++ END +++++++++ COMBINE TWO ELEMENTS FOR THE SUM IS EQUAL TO GIVEN LENGTH +++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
		System.out.println("----------------------------------------------");
		
		String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8"};
		final JTable table = new JTable(admissible__x_y, columnNames); // data_values, data_values_1, admissible__x_y , combine__x_y
		table.setPreferredScrollableViewportSize(new Dimension(950, 130));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
		// ------------------------------------------------------ SUPRIMPOSE 8*8 TABLE ON TO THE 15*15 TALBE ----------------------------------
		
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
	private static void createAndShowGUI(String piece, int x, int y, int x1, int y1, int length) {
        //Create and set up the content pane.
		String p =piece;
		int xx = x;
		int yy = y;
        ChessGameDemo_Queen_adm newContentPane = new ChessGameDemo_Queen_adm(p, xx, yy, x1, y1, length);
		
		//Create and set up the window.
        JFrame frame = new JFrame("ChessGameDemo_Queen_adm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

 
    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter piece name: -queen- :");
		final String piece = in.nextLine();
		System.out.println("Enter piece location X point(0 to 7):");
		final int x = Integer.parseInt(in.nextLine());
		System.out.println("Enter piece location Y point(0 to 7):");
		final int y = Integer.parseInt(in.nextLine());
		System.out.println("Enter piece location X1 point(0 to 7):");
		final int x_1 = Integer.parseInt(in.nextLine());
		System.out.println("Enter piece location Y1 point(0 to 7):");
		final int y_1 = Integer.parseInt(in.nextLine());
		System.out.println("Enter Length");
		final int length = Integer.parseInt(in.nextLine());
		
		System.out.println("" + piece + " - " + x + "- " + y );
		
		//ChessGameDemo_Queen_adm obj = new ChessGameDemo_Queen_adm(piece, x, y);
		if(x>7 || y>7 || x<0 || y<0 ) {
			System.out.println("Invalid Entry:: Told you to put numbers between 0 to 7.");
		} 
		else {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI(piece, x, y, x_1, y_1, length);
				}
			});
		}
        
     }
}


