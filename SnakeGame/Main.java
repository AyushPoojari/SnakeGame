import java.awt.Color;



import java.awt.Color;


import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		   JFrame f = new JFrame(); 
           f.setTitle("Snake Game");		  
	       f.setBounds(10,10,905,700);
	       f.setResizable(false);
	       f.setVisible(true);
	      
           f.setBackground(Color.DARK_GRAY);	
	        
           Gameplay gameplay=new Gameplay();
           f.add(gameplay);
	}

}
