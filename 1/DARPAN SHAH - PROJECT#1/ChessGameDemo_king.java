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
	
	
    public ChessGameDemo_king(String pi, int x, int y){
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
		
				for(Map.Entry<Integer, Integer> data_dd: sor_map.entrySet()) {
					//System.out.println("***" +data.getKey()+ "---"+ data.getValue()); 
					data_values[in_x][in_y] = data_dd.getValue();
					if(in_y == 14) {
						++in_x;
						in_y = 0;
					}else ++in_y;
					if(in_x == 15) break;
				}
		
        String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8","9", "10", "11", "12", "13", "14", "15"};
/*
        final JTable table = new JTable(data_values, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(600, 180));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
	*/
		// ------------------------------------------------------ SUPRIMPOSE 8*8 TABLE ON TO THE 15*15 TALBE ----------------------------------
		int x_diff = (7-x);  int y_diff = (7-y); 
		System.out.println("XDIFF :" + x_diff+ " YDIFF: " + y_diff);
		Integer[][] sub_data = new Integer[8][8]; int a=0,b=0;
		System.out.println("***" + data_values[7][7]);
		System.out.println("+++" + data_values[7][8]);
			/*for(int i=0; i<15; i++) {
				for(int j=0; j<15; j++) {
					String s = ""+i+""+j;
					int sd = Integer.parseInt(s);
					System.out.println(s);
					data_values[i][j] = j;
				}
			}*/
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
		
		
	//	String[] columnNames = {"1", "2", "3", "4", "5", "6", "7", "8"};
		final JTable table = new JTable(data_values, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(600, 250));
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
	private static void createAndShowGUI(String piece, int x, int y) {
        //Create and set up the content pane.
		String p =piece;
		int xx = x;
		int yy = y;
        ChessGameDemo_king newContentPane = new ChessGameDemo_king(p, xx, yy);
		
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
		
		System.out.println("" + piece + " - " + x + "- " + y );
		
		//ChessGameDemo_king obj = new ChessGameDemo_king(piece, x, y);
		
        if(x>7 || y>7 || x<0 || y<0) {
			System.out.println("Invalid Entry:: Told you to put numbers between 0 to 7.");
		} 
		else {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI(piece, x, y);
				}
			});
		}
     }
}


