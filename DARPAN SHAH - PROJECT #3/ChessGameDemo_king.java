import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Scanner;
 
public class ChessGameDemo_king extends JPanel implements MouseListener, MouseMotionListener {
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
	
	
    public ChessGameDemo_king(String pi, int x, int y, int x_1, int y_1, int length){
		super(new GridLayout(1,0));
		
		int x_loc =0;
		java.util.List<String> moves = new ArrayList<String>();
		int x_15 = 8;
		int y_15 = 8;
		int stop = 0;
		//java.util.List<String> moves_15 = new ArrayList<String>();
		//Map<String, Integer> moves_15 = new HashMap<String, Integer>();
		int count = 1;
		
		if( pi.equals("king") ) {
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
						for(int i=1; i<2; i++) {
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
						for(int j=1; j<2; j++) {
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
					for(int i=1; i<2; i++) {
						if(count<2) y1 = y_15 + i;
						if (y1<16) 
							moves_15.put(""+x_15+","+y1, count);
					}
					for(int j=1; j<2; j++) {
						if(count<2) y11 = y_15 - j;
						if(y11 >0 )
							moves_15.put(""+x_15+","+y11, count);
					}
				}
				// ------------------------------ Vertical Line -------------------------------
				// ------------------------------ Horizontal Line ------------------------------
				if (count > 1) {
					
					for(Map.Entry<String, Integer> me1:moves_15.entrySet()) {
						String key = me1.getKey();
						String[] keys = key.split(",");
						int x_key = Integer.parseInt(keys[0]);
						int y_key = Integer.parseInt(keys[1]);
						for(int i=1; i<2; i++) {
							x_15 = x_key + i;
							y1 = y_key ;
							String key_temp = ""+x_15+","+y1;
							if (x_15<16 && x_15>0) { 
								for(Map.Entry<String, Integer> me11:moves_15.entrySet()) {
									if(me11.getKey().equals(key_temp)) {
										stop = 1;
									} 
								}
								if( stop == 1) {stop = 0;} else {moves_temp.put(""+x_15+","+y1, count); stop = 0;};
							}
						}
						for(int j=1; j<2; j++) {
							x_15 = x_key - j;
							y11 = y_key ;
							String key_temp = ""+x_15+","+y11;
							if(x_15>0 && x_15<16 ) {
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
					for(int i=1; i<2; i++) {
						if(count<2) x1 = x_15 + i;
						if (x1<16) 
							moves_15.put(""+x1+","+y_15, count);
					}
					for(int j=1; j<2; j++) {
						if(count<2) x11 = x_15 - j;
						if(x11 >0 )
							moves_15.put(""+x11+","+y_15,count);
					}
				}
				// ------------------------------ Horizontal Line ------------------------------
				// ------------------------------ Diagonal Line1 --------------------------------
				//moves_15.add("---");
				if (count > 1) {
					
					for(Map.Entry<String, Integer> me1:moves_15.entrySet()) {
						String key = me1.getKey();
						String[] keys = key.split(",");
						int x_key = Integer.parseInt(keys[0]);
						int y_key = Integer.parseInt(keys[1]);
						for(int i=1; i<2; i++) {
							x_15 = x_key + i;
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
						for(int j=1; j<2; j++) {
							x_15 = x_key - j;
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
					for(int i=1; i<2; i++) {
						if(count<2) {
							x1 = x_15 + i;
							y1 = y_15 + i;
						}
						if (y1<16) 
							moves_15.put(""+x1+","+y1, count);
					}
					for(int j=1; j<2; j++) {
						if(count<2) {
							x11 = x_15 - j;
							y11 = y_15 - j;
						}
						if(y11 >0 )
							moves_15.put(""+x11+","+y11,count);
					}
				}				
				// ------------------------------ Diagonal Line1 --------------------------------
				// ------------------------------ Diagonal Line2 --------------------------------
				//moves_15.add("-*-");
				if (count > 1) {
					
					for(Map.Entry<String, Integer> me1:moves_15.entrySet()) {
						String key = me1.getKey();
						String[] keys = key.split(",");
						int x_key = Integer.parseInt(keys[0]);
						int y_key = Integer.parseInt(keys[1]);
						for(int i=1; i<2; i++) {
							x_15 = x_key + i;
							y1 = y_key - i;
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
						for(int j=1; j<2; j++) {
							x_15 = x_key - j;
							y11 = y_key + j;
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
					for(int i=1; i<2; i++) {
						if(count<2) {
							x1 = x_15 + i;
							y1 = y_15 - i;
						}
						if (y1<16) 
							moves_15.put(""+x1+","+y1, count);
					}
					for(int j=1; j<2; j++) {
						if(count<2) {
							x11 = x_15 - j;
							y11 = y_15 + j;
						}
						if(y11 >0 )
							moves_15.put(""+x11+","+y11,count);
					}
				}
				
				// ------------------------------ Diagonal Line2 --------------------------------
				for(Map.Entry<String, Integer> merge:moves_temp.entrySet()) {
					moves_15.put(merge.getKey(), merge.getValue());
				}
				// ------------------------------ Diagonal Line2 --------------------------------
				moves_15.put(""+8+","+8, 0);
				/*for(Map.Entry<String, Integer> me:moves_15.entrySet()) {
					if( me.getValue() == 0) {
						//System.out.println(me.getKey() + "-" + me.getValue());
					}
				}*/
				//System.out.println("SIZE IS::: "+ moves_15.size());			
				count++;
				//if (count == 3)break;
			}
		}
	/*
		for(Map.Entry<String, Integer> me:moves_15.entrySet()) {
			System.out.println(me.getKey() + " :-:" + me.getValue());	
		}
	*/
		System.out.println("SIZE IS::: "+ moves_15.size());
		// --------------------------------------- Match Arrays to GUI Points Using Map ------------------------
		// -----------------------------------------------------------------------------------------------------
		for(int k=1; k<=15; k++) {
			for(int l=1; l<=15; l++) {
				link.put(""+l+","+k, index_map);
				++index_map;
			}
		}
		/*
		for(Map.Entry<String, Integer> mov:link.entrySet()) {
			System.out.println(mov.getKey() + " <>" + mov.getValue());
		}
		*/
		System.out.println("--------------------" + moves_15.size());
		for(Map.Entry<String, Integer> move:moves_15.entrySet()) {
			String key = move.getKey();
			//System.out.println(key);
			for(Map.Entry<String, Integer> move_link: link.entrySet()) {
				if(key.equals(move_link.getKey())) {
					//System.out.println("MATCHED");
					real_map.put(move_link.getValue(), move.getValue());
				}
			}
			/*String[] keys = key.split(",");
			int x_key = Integer.parseInt(keys[0]);
			int y_key = Integer.parseInt(keys[1]);	*/
		}
	/*	for(Map.Entry<Integer, Integer> move_d:real_map.entrySet()) {
			System.out.println(move_d.getKey() + "-" + move_d.getValue());
		}
	*/
		Map<Integer, Integer> sor_map = new TreeMap<Integer, Integer>(real_map);
		//System.out.println(sor_map);
	/*	for(Map.Entry<Integer, Integer> move_dd:sor_map.entrySet()) {
			System.out.println(move_dd.getKey() + "-" + move_dd.getValue());
		}
	*/
		// ---------------------------- JPANEL START - CREATING TABLE --------------------------------------------------------------
		
		Vector v = new Vector(); int in_x = 0, in_y = 0;
		Integer[][] data_values = new Integer[15][15];
		Integer[][] data_values_1 = new Integer[15][15];
		
				for(Map.Entry<Integer, Integer> data_dd: sor_map.entrySet()) {
					//System.out.println("***" +data.getKey()+ "---"+ data.getValue()); 
					data_values[in_x][in_y] = data_dd.getValue();
					data_values_1[in_x][in_y] = data_dd.getValue();
					if(in_y == 14) {
						++in_x;
						in_y = 0;
					}else ++in_y;
					if(in_x == 15) break;
				}
		
    //    String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8","9", "10", "11", "12", "13", "14", "15"};

		// ------------------------------------------------------ SUPRIMPOSE 8*8 TABLE ON TO THE 15*15 TALBE ----------------------------------
		int x_diff = (7-x);  int y_diff = (7-y);
		int x_diff1 = (7-x_1); int y_diff1 = (7-y_1);
		System.out.println("XDIFF :" + x_diff+ " YDIFF: " + y_diff+ "XDIFF1 :"+ x_diff1 + "YDIFF :"+ y_diff1);
		Integer[][] sub_data = new Integer[8][8]; int a=0,b=0;
		System.out.println("***" + data_values[7][7]);
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
		//String[][] admissible__x_y = new String[8][8];
		//java.util.List<String> dock_x = new ArrayList<String>();
		//java.util.List<Integer> dock_y = new ArrayList<Integer>();
		//Integer lenght_from_start = new Integer[8]; int lfs = 0;
		
		//String length_1 = Integer.toString(length);
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(data_8_values_1[i][j] + data_8_values[i][j] == length) {
					combine__x_y[i][j] = ""+length;
					//admissible__x_y[i][j] = " ";
				}
				/*else if (data_8_values_1[i][j] + data_8_values[i][j] == (length+1)){
					int dfd = length+1;
					admissible__x_y[i][j] = ""+dfd;
					dock_x.add(i); dock_y.add(j);
					lenght_from_start[lfs] = data_8_values[i][j];
					++lfs;
				}*/
				else {
					combine__x_y[i][j] = " ";
					//admissible__x_y[i][j] = " ";
				}
			}
		}
		combine__x_y[x][y] = "Start"; combine__x_y[x_1][y_1] = "End"; //admissible__x_y[x][y] = "Start"; admissible__x_y[x_1][y_1] = "End";
// ###############   START  ################ ADMISSIBLE TRAJECTORIES GETTING DOCK ITEMS FROM DOCK ARRAY ######################################
/*
	for(int i=0; i<dock_x.size(); i++) { // DOCK NA BADHA J ELEMENTS MATE LOOP, EACH ITEM MATE AKHU SEARCH ---------------------------------
		for(int e=0; e<8; e++) {
			for(int f=0; f<8; f++) {
				if(e.equals(dock_x.get(i)) && f.equals(dock_y.get(i))) {
					int lll = data_8_values[e][f];
					for(int g=0; g<8; g++) {
						for(h=0; h<8; h++){
							if((data_8_values[g][h] + data_8_values_1[g][h] == length) && (data_8_values[g][h] - lll == 0) ) {
								master_result.add(i+"-"+ g+" , "+ h);
							}
							if((data_8_values[g][h] + data_8_values_1[g][h] == length+1) && (data_8_values_1[g][h] - lll == 0) ) {
								master_result.add(i+"-"+ g+" , "+ h);
							}
						}
					}
					
				}
			}
		}
	}
*/
// ###############   END  ################ ADMISSIBLE TRAJECTORIES GETTING DOCK ITEMS FROM DOCK ARRAY ######################################

// ++++++++++++++++++++ END +++++++++ COMBINE TWO ELEMENTS FOR THE SUM IS EQUAL TO GIVEN LENGTH +++++++++++++++++++++++++++++++++++++++++++++++++++++++
/*
		for(Map.Entry<String, Integer> count_dis: count_dist.entrySet()) {
			//System.out.println("***" +data.getKey()+ "---"+ data.getValue()); 
			System.out.println("Key:" + count_dis.getKey()+ "##Value:"+ count_dis.getValue());
		}
*/		
		System.out.println("----------------------------------------------");
		
		String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8"};
		final JTable table = new JTable(combine__x_y, columnNames);   // data_values, data_values_1
		table.setPreferredScrollableViewportSize(new Dimension(500, 130));
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
        ChessGameDemo_king newContentPane = new ChessGameDemo_king(p, xx, yy, x1, y1, length);
		
		//Create and set up the window.
        JFrame frame = new JFrame("ChessGameDemo_king");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

 
    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter piece name: -king-: ");
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
		
		//ChessGameDemo_king obj = new ChessGameDemo_king(piece, x, y);
		
        if(x>7 || y>7 || x<0 || y<0) {
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


