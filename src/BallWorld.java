import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;

/**
 * The control logic and main display panel for game.
 * 
 */
public class BallWorld extends JPanel implements MouseListener {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private static final int UPDATE_RATE = 30;    // Frames per second (fps)
   private static final float EPSILON_TIME = 1e-2f;  // Threshold for zero time
   
   public static boolean running = false;
   
   public static BallWorld game =new BallWorld(800, 700);
   
   //Score
   public static int p1Score;
   public static int p2Score;
   public static int p1Distance;
   public static int p2Distance;
   public  String p1User="";
   public  String p2User="";
   public static DateFormat dateFormat;// = new SimpleDateFormat("yyyyMMdd");
   public static Date date;// = new Date();
   //System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
   
   // Balls
   private static final int MAX_BALLS = 40; // Max number allowed 
   private int currentNumBalls;             // Number currently active
   private ArrayList<Ball> balls = new ArrayList<Ball>();

   private ContainerBox box;     // The container rectangular box
   public  DrawCanvas canvas;    // The Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;

   //private ControlPanel control; // The control panel of buttons and sliders.  
   private boolean paused = false;  // Flag for pause/resume control
	private ArrayList<Integer> cX = new ArrayList<Integer>();
	private ArrayList<Integer> cY = new ArrayList<Integer>();
   
   /**
    * Constructor to create the UI components and init the game objects.
    * Set the drawing canvas to fill the screen (given its width and height).
    * 
    * @param width : screen width
    * @param height : screen height
    */
   public BallWorld(int width, int height) {
	 
	   //runShow(width, height);
      final int controlHeight = 30;    
      canvasWidth = width;
      canvasHeight = height - controlHeight;  // Leave space for the control panel

      // Init the Container Box to fill the screen
      box = new ContainerBox(25, 50, 400, 400, Color.WHITE, Color.BLACK);

      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      
      canvas.addMouseListener(this);
     
      // Init the control panel
      //control = new ControlPanel();
   
      // Layout the drawing panel and control panel
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      //this.add(control, BorderLayout.SOUTH);
      
      // Handling window resize. Adjust container box to fill the screen.
      this.addComponentListener(new ComponentAdapter() {
         // Called back for first display and subsequent window resize.
         @Override
         public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
            Dimension dim = c.getSize();
            canvasWidth = dim.width;
            canvasHeight = dim.height - controlHeight; // Leave space for control panel
            // Need to resize all components that is sensitive to the screen size.
            box.set(25, 50, 400, 400);
         }
      });
       
      // Start the ball bouncing
      gameStart();
   }
   
   public void setUsers(String[] users){
	   
	   p1User  =   (String) (JOptionPane.showInputDialog(
                canvas,
                "Select Player 1",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                users,
                users[0]));

	   p2User  =   (String) (JOptionPane.showInputDialog(
                canvas,
                "Select Player 2",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                users,
                users[0]));
   }
   
   public  void runShow(int width, int height){
	   final int controlHeight = 30;    
	      canvasWidth = width;
	      canvasHeight = height - controlHeight;  // Leave space for the control panel
	      
	   
	      BallWorld.dateFormat = new SimpleDateFormat("yyyyMMdd");
	      BallWorld.date = new Date();
	      currentNumBalls = 25;
			for(int i=0; i<25;i++){
				int xPos = P3Utils.randomNumber(26, 405);
				int yPos = P3Utils.randomNumber(51, 430);
				for(int j=0;j<cX.size();j++){
					while((int)(Math.sqrt(Math.pow((xPos-cX.get(j)),2)+Math.pow(yPos-cY.get(j),2)))<30){
						xPos =P3Utils.randomNumber(26, 405);
						yPos =P3Utils.randomNumber(51, 430);
						j=0;
					}
				}
				cX.add(xPos);
				cY.add(yPos);
				if(i<5){
		    	  balls.add(new Ball((float)cX.get(i), (float)cY.get(i), 10, 0, 34, Color.GREEN));
				}
				if(i>=5 && i<10){
			      balls.add(new Ball((float)cX.get(i), (float)cY.get(i), 10, 0, 34, Color.BLUE));
				}
			      if(i>=10&&i<15){
				   balls.add(new Ball((float)cX.get(i), (float)cY.get(i), 10, 0, 34, Color.BLACK));
			      }
			      if(i>=15 &&i<20){
				   balls.add(new Ball((float)cX.get(i), (float)cY.get(i), 10, 0, 34, Color.RED));

			      }
			      if(i>=20){
					  balls.add(new Ball((float)cX.get(i), (float)cY.get(i), 10, 0, 34, Color.YELLOW));

				      }
				}

	      // Init the Container Box to fill the screen
	      box = new ContainerBox(25, 50, 400, 400, Color.WHITE, Color.BLACK);

	      // Init the custom drawing panel for drawing the game
	      canvas = new DrawCanvas();
	      
	      canvas.addMouseListener(this);
	      
	      // Init the control panel
	     // control = new ControlPanel();
	   
	      // Layout the drawing panel and control panel
	      this.setLayout(new BorderLayout());
	      this.add(canvas, BorderLayout.CENTER);
	     // this.add(control, BorderLayout.SOUTH);
	  
	      // Handling window resize. Adjust container box to fill the screen.
	      this.addComponentListener(new ComponentAdapter() {
	         // Called back for first display and subsequent window resize.
	         @Override
	         public void componentResized(ComponentEvent e) {
	            Component c = (Component)e.getSource();
	            Dimension dim = c.getSize();
	            canvasWidth = dim.width;
	            canvasHeight = dim.height; 
	            // Need to resize all components that is sensitive to the screen size.
	            box.set(25, 50, 400, 400);
	         }
	      });
	       
	      // Start the ball bouncing
	      gameStart();
	   
   }
   
   public ArrayList<Ball> getBalls(){
	   return balls;
   }
   
   /** Start the ball bouncing. */
   public void gameStart() {
      // Run the game logic in its own thread.
      Thread gameThread = new Thread() {
         public void run() {
            while (true) {
               long beginTimeMillis, timeTakenMillis, timeLeftMillis;
               beginTimeMillis = System.currentTimeMillis();
               
               if (!paused) {
                  // Execute one game step
                  gameUpdate();
                  // Refresh the display
                  repaint();
               }
               
               // Provide the necessary delay to meet the target rate
               timeTakenMillis = System.currentTimeMillis() - beginTimeMillis;
               timeLeftMillis = 1000L / UPDATE_RATE - timeTakenMillis;
               if (timeLeftMillis < 5) timeLeftMillis = 5; // Set a minimum
               
               // Delay and give other thread a chance
               try {
                  Thread.sleep(timeLeftMillis);
               } catch (InterruptedException ex) {}
            }
         }
      };
      gameThread.start();  // Invoke GaemThread.run()
      if(!BallWorld.running){
      gameThread.interrupt();
      }
   }
   
   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
      float timeLeft = 1.0f;  // One time-step to begin with
      
      // Repeat until the one time-step is up 
      do {
         // Find the earliest collision up to timeLeft among all objects
         float tMin = timeLeft;
         
         // Check collision between two balls
         for (int i = 0; i < balls.size(); i++) {
            for (int j = 0; j < balls.size(); j++) {
               if (i < j) {
                  balls.get(i).intersect(balls.get(j), tMin);
                  if (balls.get(i).earliestCollisionResponse.t < tMin) {
                     tMin = balls.get(i).earliestCollisionResponse.t;
                     if((balls.get(i).getColor().equals(Color.GREEN) && balls.get(j).getColor().equals(Color.GREEN))||(balls.get(i).getColor().equals(Color.BLUE) && balls.get(j).getColor().equals(Color.BLUE))){
                 	 	balls.get(j).setDistance(balls.get(i).getDistance()/2);
                	 	balls.get(j).speedX=4;//balls.get(i).speedX;
                	 	balls.get(j).speedY=4;//balls.get(i).speedY;

                    	balls.get(i).setDistance(balls.get(i).getDistance()/2);	
                     }
                     
                     if((balls.get(i).getColor().equals(Color.YELLOW) && balls.get(j).getColor().equals(Color.BLUE))||(balls.get(i).getColor().equals(Color.BLUE) && balls.get(j).getColor().equals(Color.YELLOW))||(balls.get(i).getColor().equals(Color.YELLOW) && balls.get(j).getColor().equals(Color.GREEN))||(balls.get(i).getColor().equals(Color.GREEN) && balls.get(j).getColor().equals(Color.YELLOW))||(balls.get(i).getColor().equals(Color.GREEN) && balls.get(j).getColor().equals(Color.BLUE))||(balls.get(i).getColor().equals(Color.BLUE) && balls.get(j).getColor().equals(Color.GREEN))){
                    	 if(balls.get(i).getDistance()>0){
                    		 if(!(balls.get(j).getColor().equals(balls.get(i).getColor()))){
                        		 balls.remove(j);
                    		 }
                    		 //if(!(balls.get(j).getColor().equals(Color.BLUE))){
                        	//	 balls.remove(j);
                    		// }
                    		 //balls.remove(j);
                    	 }
                    	 if(balls.get(j).getDistance()>0){
                    		 if(balls.get(i).getColor().equals(Color.GREEN)){
                    			 BallWorld.p1Score-=100;
                    		 }
                    		 if(balls.get(i).getColor().equals(Color.BLUE)){
                    			 BallWorld.p2Score-=100;
                    		 }
                    	 balls.remove(i);
                    	 }
                     }
                     if(balls.get(i).getColor().equals(Color.RED) || balls.get(j).getColor().equals(Color.RED)){
                    	 int xPos = (int)balls.get(i).x;
                    	 int yPos = (int)balls.get(i).y;
                    	 balls.remove(i);
                    	 balls.remove(j-1);
                    	 for(int m=0;m<balls.size();m++){
                    		 if((int)(Math.sqrt(Math.pow((xPos-balls.get(m).x),2)+Math.pow((yPos-balls.get(m).y),2)))<25){
                    			 if(!balls.get(m).getColor().equals(Color.BLACK)){
                    			 balls.remove(m);
                    			 }
                    			 if(balls.size()>2){
                    				// i--;
                    				 //j--;
                    			 }

                    		 }
                    	 }
                    

                     }
                  }                	  

               }
            }
         }
         // Check collision between the balls and the box
         for (int i = 0; i < balls.size(); i++) {
            balls.get(i).intersect(box, tMin);
            if (balls.get(i).earliestCollisionResponse.t < tMin) {
               tMin = balls.get(i).earliestCollisionResponse.t;
            }
         }
   
         // Update all the balls up to the detected earliest collision time tMin,
         // or timeLeft if there is no collision.
         for (int i = 0; i < balls.size(); i++) {
            balls.get(i).update(tMin, balls, i);
         }
   
         timeLeft -= tMin;                // Subtract the time consumed and repeat
      } while (timeLeft > EPSILON_TIME);  // Ignore remaining time less than threshold
   }
   
   /** The custom drawing panel for the bouncing ball (inner class). */
   class DrawCanvas extends JPanel {
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Custom drawing codes */
	   
		   @Override
	  
      public void paintComponent(Graphics g) {
			   if((!(BallWorld.p1Distance >0)|| !(BallWorld.p2Distance >0))&&BallWorld.running){
				     balls.clear();
				     cY.clear();
				     cX.clear();
				     this.remove(canvas);
				     //this.remove(control);
				
					 BallWorld.running= false;
					 try { 
				   			File f = new File("history.txt");
				   			ArrayList<String> text = new ArrayList<String>();
				   			if(f.exists() && !f.isDirectory()) { 
				   			//System.out.println(f.exists() +" " + f.isDirectory());
				   			Scanner in;
							
								in = new Scanner(f);
							
				   			while(in.hasNextLine()){
				   				String s = in.nextLine();
				   				text.add(s);
				   				//System.out.println(s);
				   			}
				   			in.close();
				   			}
				   			PrintWriter out = new PrintWriter("history.txt");
				   			for(int i=0; i<text.size(); i++){ 
				   				out.println(text.get(i));
				   				System.out.println(text.get(i));
				   			}
				   			out.println(BallWorld.game.p1User);
				   			out.println(BallWorld.game.p2User);
				   			out.println(BallWorld.p1Distance);
				   			out.println(BallWorld.p2Distance);
				   			out.println(BallWorld.dateFormat.format(date));
				   			out.close();
					 }
					 catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
		   			}
			   }
					 
			   
    	  if(BallWorld.running){
         super.paintComponent(g);    // Paint background
         // Draw the balls and box
         box.draw(g);
         for (int i = 0; i < balls.size(); i++) {
            balls.get(i).draw(g);
         }
         // Display balls' information
         g.setColor(Color.BLACK);
         g.setFont(new Font("Courier New", Font.PLAIN, 12));
         for (int i = 0; i < balls.size(); i++) {
            //g.drawString("Ball " + (i+1) + " " + balls.get(i).toString(), 20, 30 + i*20);
         }
         g.setColor(Color.BLACK);
			g.drawRect(25, 50, 400,400);


			g.setColor(Color.pink);
			g.fillRect(450, 70, 300, 50);
			g.setColor(Color.BLACK);
			g.setFont(new Font("ComicSansMS", Font.BOLD, 15));
			g.drawString("PLAYER", 470, 100);
			g.drawString("SCORE", 565, 100);
			g.drawString("DISTANCE", 650, 100);

			g.setColor(Color.green);
			g.fillRect(450, 120, 300, 50);
			g.setColor(Color.BLACK);
			g.drawString("PLAYER1", 460, 150);
			g.drawString(String.valueOf(BallWorld.p1Score), 580, 150); 
			g.drawString(String.valueOf(BallWorld.p1Distance), 660, 150); 

			g.setColor(Color.blue);
			g.fillRect(450, 170, 300, 50);
			g.setColor(Color.BLACK);
			g.drawString("PLAYER2", 460, 200);
			g.drawString(String.valueOf(BallWorld.p2Score), 580, 200); 
			g.drawString(String.valueOf(BallWorld.p2Distance), 660, 200); 

			g.setFont(new Font("ComicSansMS", Font.BOLD, 14));
			g.setColor(Color.green);
			g.fillRect(25, 525, 90, 40);
			g.setColor(Color.BLACK);
			g.drawString("SHOOT", 40, 550);
			g.setColor(Color.blue);
			g.fillRect(250, 525, 90, 40);
			g.setColor(Color.BLACK);
			g.drawString("SHOOT", 265, 550);
			g.setColor(Color.yellow);
			g.fillRect(605, 525, 120, 40);
			g.setColor(Color.BLACK);
			g.drawString("ABORT GAME", 615, 550);

			g.drawRect(605, 525, 120, 40);
			//g2.drawRect(250, 525, 90, 40);

			g.drawRect(450, 70, 300, 50);
			g.drawRect(450, 120, 300, 50);
			g.drawRect(450, 170, 300, 50);
			g.drawLine(545, 70, 545, 220);
			g.drawLine(635,70,635,220);

      }
      
      }

      /** Called back to get the preferred size of the component. */
      @Override
      public Dimension getPreferredSize() {
         return (new Dimension(canvasWidth, canvasHeight));
      }
   }
   
   /** The control panel (inner class). */
   

@Override
public void mouseClicked(MouseEvent click) {
	// TODO Auto-generated method stub
	int clickX = click.getX();
	int clickY = click.getY();
	if(clickX>=605 && clickX <= 725 && clickY>=525 && clickY<=565){

		     balls.clear();
		     cY.clear();
		     cX.clear();
		     this.remove(canvas);
			 BallWorld.running= false;

	}
	for(int i=0; i<balls.size();i++){
		if(!balls.get(i).getColor().equals(Color.BLACK) && !balls.get(i).getColor().equals(Color.RED) && !balls.get(i).getColor().equals(Color.YELLOW)){
			int xPos = (int)balls.get(i).x;
			int yPos = (int)balls.get(i).y;
			if((int)(Math.sqrt(Math.pow((xPos-clickX),2)+Math.pow((yPos-clickY),2)))<21){
				int maxD =0;
				if(balls.get(i).getColor().equals(Color.GREEN)){
					maxD = BallWorld.p1Distance;
				}
				if(balls.get(i).getColor().equals(Color.BLUE)){
					maxD = BallWorld.p2Distance;
				}
				Integer[] distanceArray = P3Utils.distanceArray(maxD);
				Integer[] angleArray = P3Utils.distanceArray(359);
				int distance =   (Integer) (JOptionPane.showInputDialog(
	                    canvas,
	                    "Select Distance",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    distanceArray,
	                    "ham"));

				int angle= (Integer) (JOptionPane.showInputDialog(
	                    canvas,
	                    "Select Angle",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    angleArray,
	                    "ham"));
				Ball newB = new Ball(balls.get(i).x, balls.get(i).y, balls.get(i).radius, 3, angle, balls.get(i).getColor());

				newB.setDistance(distance);
				balls.set(i, newB);
				if(balls.get(i).getColor().equals(Color.GREEN)){
					BallWorld.p1Distance-=distance;
				}
				if(balls.get(i).getColor().equals(Color.BLUE)){
					BallWorld.p2Distance-=distance;
				}
				repaint();
			}
		}
	}
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
}
