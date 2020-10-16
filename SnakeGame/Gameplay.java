import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//
import javax.swing.ImageIcon;//
import javax.swing.JPanel; //
import javax.swing.Timer; //Important  Import



public class Gameplay extends JPanel implements KeyListener,ActionListener {
    
	
	private static final long serialVersionUID = 1L;
	//Snake   length  in x and y
	private int[] snakexlenght = new int[750];
    private int[] snakeylenght = new int[750];
    
    //Image Boolean
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon snakeimage;
    
    //Enemy
    private int[] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private ImageIcon enemyimage;
    private Random random = new Random();   //Random Class
    private int xpos = random.nextInt(34);  //Enemy array Lenght
    private int ypos = random.nextInt(23);  //Enemy array Lenght  
    
    //Timer
    private Timer timer;
    private int delay = 100; 
    
	//Length of Snake
    private int lenghtofsnake = 3;
    private int moves=0;
    
    private ImageIcon titleImage;
	
    //Fields
    private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	
	//Scoreboard
	public int scores=0;
	
	
	public Gameplay() {
	//Checked
		
		 addKeyListener(this);
		 setFocusable(true);
		 setFocusTraversalKeysEnabled(true);
		 timer = new Timer(delay,this);
		 timer.start();
		 
	}
	
	
	
	public void paint(Graphics g){
		
		if(moves==0) {
			
			snakexlenght[0] = 100;
			snakexlenght[1] = 75;
			snakexlenght[2] = 50;
			
			snakeylenght[0] = 100;
			snakeylenght[1] = 100;
			snakeylenght[2] = 100;
					
		}
		
		
		
		//border of title image
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//TitleImage
		
		titleImage = new ImageIcon("snaketitle.jpg");
	    titleImage.paintIcon(this, g, 25, 11); 
 		
		//border of gamble
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577); 
	    g.setColor(Color.black);
	    g.fillRect(25, 75, 850, 575);
	    
	    
	  //Drawing ScoreBoard
	    g.setColor(Color.WHITE);
	    g.setFont(new Font("arial",Font.PLAIN,14));   
		g.drawString("Score: " + scores ,780,30 );
		g.drawString("Lenght: " + lenghtofsnake ,780,50 );
		
	    
	    rightmouth=new ImageIcon("rightmouth.png");
	    rightmouth.paintIcon(this, g, snakexlenght[0],snakeylenght[0]); 
	    
	    
	    for(int a=0;a<lenghtofsnake;a++)
	    {
	    	 if(a==0 && right)
	    	 {
	    		 rightmouth=new ImageIcon("rightmouth.png");
	    		 rightmouth.paintIcon(this, g, snakexlenght[a],snakeylenght[a]); 
	    	 }
	    	 if(a==0 && left)
	    	 {
	    		 leftmouth=new ImageIcon("leftmouth.png");
	    		 leftmouth.paintIcon(this, g, snakexlenght[a],snakeylenght[a]); 
	    	 }
	    	
			if(a==0 && up)
	    	 {
	    		 upmouth=new ImageIcon("upmouth.png");
	    		 upmouth.paintIcon(this, g, snakexlenght[a],snakeylenght[a]); 
	    	 }
	    	 if(a==0 && down)
	    	 {
	    		 downmouth=new ImageIcon("downmouth.png");
	    		 downmouth.paintIcon(this, g, snakexlenght[a],snakeylenght[a]); 
	    	 }
	    	 
	    	 if(a!=0)
	    	 {
	    		 snakeimage =new ImageIcon("snakeimage.png");
	    		 snakeimage.paintIcon(this, g, snakexlenght[a],snakeylenght[a]); 
	    	 }
	    }
	    
	    
	    enemyimage = new ImageIcon("enemy.png");
	    enemyimage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);
	    
	    //Checking collision condition (head)
	    if(enemyxpos[xpos] == snakexlenght[0] && enemyypos[ypos] == snakeylenght[0])
	    {
	    	lenghtofsnake++;              //Increasing Snake Length (Feeding Snake)
	    	xpos = random.nextInt(34);    //Next Food
	    	ypos = random.nextInt(23);
	    	scores++;
	    }
	    
	    //Checking Self Collision
	    for(int b=1;b<lenghtofsnake;b++)
	    {
	    	if(snakexlenght[b] == snakexlenght[0] && snakeylenght[b] == snakeylenght[0])
	    	{
	    		right=false;
	    		left=false;
	    		up=false;
	    		down=false;
	    		
	    		g.setColor(Color.WHITE);
	    		g.setFont(new Font("arial",Font.BOLD,50));
	    		g.drawString("Game Over!",300,300);
	    		
	    		
	    		g.setFont(new Font("arial",Font.BOLD,20));
	    		g.drawString("SPACE to RESTART",350,340);
	    		
	    	}
	    }
	    
 		g.dispose();
     }



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
//For Right
		if(right)
		{
			for(int i=lenghtofsnake-1;i>=0;i--)               //For loop for head to tail
			{
				snakeylenght[i+1]=snakeylenght[i];
			}
			for(int i=lenghtofsnake-1;i>=0;i--)               //For loop for head to tail
			{
	        	if(i==0) {
	        		 snakexlenght[i]=snakexlenght[i]+25;
	        	}
	        	else
	        	{
	        		 snakexlenght[i]=snakexlenght[i-1];
	        	}
	        	if(snakexlenght[i]>850)                        //Resize
	        	{
	        	   snakexlenght[i]=25;	
	        	}
			}
			
			repaint();
		}
		
		
//For Left
				if(left)
				{
					for(int i=lenghtofsnake-1;i>=0;i--)               //For loop for head to tail
					{
						snakeylenght[i+1]=snakeylenght[i];
					}
					for(int i=lenghtofsnake-1;i>=0;i--)               //For loop for head to tail
					{
			        	if(i==0) {
			        		 snakexlenght[i]=snakexlenght[i]-25;      // '-'
			        	}
			        	else
			        	{
			        		 snakexlenght[i]=snakexlenght[i-1];
			        	}
			        	if(snakexlenght[i]<25)                        //Change
			        	{
			        	   snakexlenght[i]=850;	                      //Change
			        	}
					}
					
					repaint();
				}	
				
				
//For Up :   y to x  && x to y
				if(up)
				{
					for(int i=lenghtofsnake-1;i>=0;i--)               //For loop for head to tail
					{
						snakexlenght[i+1]=snakexlenght[i];
					}
					for(int i=lenghtofsnake-1;i>=0;i--)               //For loop for head to tail
					{
			        	if(i==0) {
			        		 snakeylenght[i]=snakeylenght[i]-25;      // '-'
			        	}
			        	else
			        	{
			        		 snakeylenght[i]=snakeylenght[i-1];
			        	}
			        	if(snakeylenght[i]<75)                        //Change
			        	{
			        	   snakeylenght[i]=625;	                      //Change
			        	}
					}
					
					repaint();
				}	
				
				
				
				
//For Down :  
				if(down)
				{
					for(int i=lenghtofsnake-1;i>=0;i--)               //For loop for head to tail
					{
						snakexlenght[i+1]=snakexlenght[i];
					}
					for(int i=lenghtofsnake-1;i>=0;i--)               //For loop for head to tail
					{
			        	if(i==0) {
			        		 snakeylenght[i]=snakeylenght[i]+ 25;      // '+'
			        	}
			        	else
			        	{
			        		 snakeylenght[i]=snakeylenght[i-1];
			        	}
			        	if(snakeylenght[i]>625)                        //Change
			        	{
			        	   snakeylenght[i]=75;	                      //Change
			        	}
					}
					
					repaint();
				}	
				
				
		
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override //Important
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		

		//Checked
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			
			moves++; 
			if(!left)                         //If Snake is not taking full reverse 
			{
				right=true;	
			}
			else                              //If taking full reverse  
			{
				right=false;
				left=true;
			}
			
			
			up=false;
			down=false;
		}
		
		//Checked
          if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			
			moves++; 
			if(!right)                         //If Snake is not taking full reverse 
			{
				left=true;	
			}
			else                              //If taking full reverse  
			{
				left=false;
				right=true;
			}
			
			up=false;
			down=false;
		}

    //Checked      
          if(e.getKeyCode()==KeyEvent.VK_UP) {
  			
  			moves++; 
  			if(!down)                         //If Snake is not taking full reverse 
  			{
  				up=true;	
  			}
  			else                              //If taking full reverse  
  			{
  				up=false;
  				down=true;
  			}
  			
  			
  			right=false;
  			left=false;
  		} 
	
	//Checked
          if(e.getKeyCode()==KeyEvent.VK_DOWN) {
  			
  			moves++; 
  			if(!up)                         //If Snake is not taking full reverse 
  			{
  				down=true;	
  			}
  			else                              //If taking full reverse  
  			{
  				down=false;
  				up=true;
  			}
  			
  			left=false;
  			right=false; 
  		}
	
   //Checked (GAME RESTART)
          if(e.getKeyCode() == KeyEvent.VK_SPACE) {
        	  
        	  scores=0;
        	  moves=0;
        	  lenghtofsnake=3;
        	  repaint();
          }
		
}


///////////////////////////////////////////////////////////////////////////////////
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}



