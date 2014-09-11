import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.Scanner;
 
public class ChessGameDemo_pawn extends JPanel implements MouseListener, MouseMotionListener {
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
	
	
    public ChessGameDemo_pawn(String pi, int x, int y){
		super(new GridLayout(1,0));
		
		int x_loc =0;
		java.util.List<String> moves = new ArrayList<String>();
		int x_15 = 8;
		int y_15 = 8;
		int stop = 0;
		//java.util.List<String> moves_15 = new ArrayList<String>();
		//Map<String, Integer> moves_15 = new HashMap<String, Integer>();
		int count = 1;
		
		if( pi.equals("pawn") ) {
			while (moves_15.size() < 8) {
			
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
						
					}
				}
				else {
					for(int i=1; i<2; i++) {
						if(count<2) y1 = y_15 + i;
						if (y1<16) 
							moves_15.put(""+x_15+","+y1, count);
					}
					
				}
				// ------------------------------ Vertical Line -------------------------------
				
				// ------------------------------ Horizontal Line ------------------------------
				// ------------------------------ Horizontal Line ------------------------------
				
				for(Map.Entry<String, Integer> merge:moves_temp.entrySet()) {
					moves_15.put(merge.getKey(), merge.getValue());
				}
				
				moves_15.put(""+8+","+8, 0);
				
				System.out.println("now size: "+ moves_15.size());			
				count++;
				
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
		
				int dd = 0;
				for(int j=7; j>=0; j--) {
					data_values[j][7] = dd;
					++dd;
				}
		
		
		
			for(int i=0; i<15; i++) {
				for(int j=0; j<7; j++) {
					data_values[i][j] = 111;
				}
			}
			
			for(int i=7; i<15; i++) {
				for(int j=8; j<15; j++) {
					data_values[i][j] = 111;
				}
			}
			
			for(int i=8; i<15; i++) {
				data_values[i][7] = 111;	
			}
			for(int i=0; i<8; i++) {
				for(int j=8; j<15; j++) {
					data_values[i][j] = 111;
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
        ChessGameDemo_pawn newContentPane = new ChessGameDemo_pawn(p, xx, yy);
		
		//Create and set up the window.
        JFrame frame = new JFrame("ChessGameDemo_pawn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

 
    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter piece name: -pawn- :");
		final String piece = in.nextLine();
		System.out.println("Enter piece location X point(0 to 7):");
		final int x = Integer.parseInt(in.nextLine());
		System.out.println("Enter piece location Y point(0 to 7):");
		final int y = Integer.parseInt(in.nextLine());
		
		System.out.println("" + piece + " - " + x + "- " + y );
		
		//ChessGameDemo_pawn obj = new ChessGameDemo_pawn(piece, x, y);
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

/*	    JFrame frame = new ChessGameDemo_pawn(piece, x, y);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true); 
*/
     }
}


