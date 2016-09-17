
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.File;
	import java.io.FileInputStream;
import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;
	import javax.swing.JFrame;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


	public class GameMenuComponent extends JComponent {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/**
	     * @param args
	     */

	    static FileInputStream in = null;
	    static FileOutputStream out = null;
	    static String sout = null;
	    static int c;
	    public JFrame jf;
	    public BallWorld game;
	    
	    public GameMenuComponent(JFrame jf, BallWorld game){
	    	this.jf =jf;
	    	this.game = game;
	    }
	    public void gameMenu() {
	   	 // TODO Auto-generated method stub
	   	 //JFrame jf = new JFrame("The Rolling-Balls Game");
	   	 final JTextArea jta = new JTextArea();
	   	 jf.getContentPane().add(jta);

	   	 JMenuBar menubar = new JMenuBar();
	   	 JMenu newGameMenu = new JMenu("Play-New-Game");
	   	JMenu firstPlayerMenu = new JMenu("First-Time-Player");
	   	JMenu historyMenu = new JMenu("History");
	   	JMenu helpMenu = new JMenu("Help");
	   	JMenu exitMenu = new JMenu("Exit");

	   	 JMenuItem currentPlayers= new JMenuItem("Current Players");
	   	 currentPlayers.setToolTipText("starts game with current players");

	   	 	currentPlayers.addActionListener(new ActionListener() {
	   		 
	   		 public void actionPerformed(ActionEvent event) {
	   			 if(!(new File("players.txt").exists())){
	   	 		try {
					Player.setNewPlayer(jf);
					Player.setNewPlayer(jf);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	   	 	}
	   			 if(BallWorld.game.p1User.equals("") || BallWorld.game.p2User.equals("")){
	   				File f = new File("players.txt");
		   			ArrayList<String> text = new ArrayList<String>();
		   			if(f.exists() && !f.isDirectory()) { 
		   			Scanner in;
					
						try {
							in = new Scanner(f);
						
					
		   			while(in.hasNextLine()){
		   				String s = in.nextLine();
		   				text.add(s);
		   				//System.out.println(s);
		   			}
		   			in.close();
		   			String[] users = new String[text.size()/4];
		   			int j = 0;
		   			for(int i=0;i<text.size();i++){
		   				if(i%4==0){
		   				users[j] = text.get(i);
		   					j++;
		   				}
		   			}

		   			String p1 = (String) (JOptionPane.showInputDialog(
		                    BallWorld.game.canvas,
		                    "Select Player 1",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    users,
		                    users[0]));
		   			String p2 = (String) (JOptionPane.showInputDialog(
		                    BallWorld.game.canvas,
		                    "Select Player 2",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    users,
		                    users[0]));
		   			BallWorld.game.p1User=p1;
		   			BallWorld.game.p2User=p2;
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}}
	   			 }
	   			
	   			 BallWorld.p1Score=0;
	   			 BallWorld.p2Score=0;
	   			 BallWorld.p1Distance=1500;
	   			 BallWorld.p2Distance=1500;
	   			 BallWorld.running = true;
	   			 game.runShow(game.getWidth(), game.getWidth());
	   			 BallWorld.game.runShow(800, 700);
	   			 
				
				}
			});

	   	 

	   	 JMenuItem selectNewPlayers= new JMenuItem("Select New Players");
	   	 selectNewPlayers.setToolTipText("select new players for game");

	   	 selectNewPlayers.addActionListener(new ActionListener() {
	   		 
	   		 public void actionPerformed(ActionEvent event) {
	   			 
	   			File f = new File("players.txt");
	   			ArrayList<String> text = new ArrayList<String>();
	   			if(f.exists() && !f.isDirectory()) { 
	   			Scanner in;
				
					try {
						in = new Scanner(f);
					
				
	   			while(in.hasNextLine()){
	   				String s = in.nextLine();
	   				text.add(s);
	   				//System.out.println(s);
	   			}
	   			in.close();
	   			String[] users = new String[text.size()/4];
	   			int j = 0;
	   			for(int i=0;i<text.size();i++){
	   				if(i%4==0){
	   				users[j] = text.get(i);
	   					j++;
	   				}
	   			}

	   			String p1 = (String) (JOptionPane.showInputDialog(
	                    BallWorld.game.canvas,
	                    "Select Player 1",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    users,
	                    users[0]));
	   			String p2 = (String) (JOptionPane.showInputDialog(
	                    BallWorld.game.canvas,
	                    "Select Player 2",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    users,
	                    users[0]));
	   			BallWorld.game.p1User=p1;
	   			BallWorld.game.p2User=p2;
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				

	   		 }}
	   	 });
	   	 
	   	 JMenuItem registerPlayer= new JMenuItem("Register New Player");
	   	 registerPlayer.setToolTipText("Register a New Player");

	   	 registerPlayer.addActionListener(new ActionListener() {
	   		 
	   		 public void actionPerformed(ActionEvent event) {

	  
	   				try {
						Player.setNewPlayer(jf);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
			
	   			}
	   		}
	   	 });
	   	 
	   	 JMenuItem highScore= new JMenuItem("Show High Scores");
	   	 highScore.setToolTipText("View Highs Scores on the Game");

	   	 highScore.addActionListener(new ActionListener() {
	   		 
	   		 public void actionPerformed(ActionEvent event) {
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
	   			System.out.println("***********By SCORE************");

	   			int[] score = History.byScore(text);
	   			for(int i=0; i<score.length;i++){
	   				//for(int j=0;j<5; j++)
	   		
	   			 System.out.println("Player1: " + text.get(score[i]*5));
	   			 System.out.println("Player2: " + text.get(score[i]*5+1));
	   			 System.out.println("Player1 Score: " + text.get(score[i]*5+2));
	   			System.out.println("Player2 Score: " + text.get(score[i]*5+3));
	   			System.out.println("Date Played: " + text.get(score[i]*5+4));
	   			}
	   			System.out.println("***********By Name************");

	   			int[] score1 = History.byName(text);

	   			for(int i=0; i<score1.length;i++){
	   				//for(int j=0;j<5; j++)
	   			 System.out.println("Player1: " + text.get(score1[i]*5));
	   			 System.out.println("Player2: " + text.get(score1[i]*5+1));
	   			 System.out.println("Player1 Score: " + text.get(score1[i]*5+2));
	   			System.out.println("Player2 Score: " + text.get(score1[i]*5+3));
	   			System.out.println("Date Played: " + text.get(score1[i]*5+4));
	   			}
	   			System.out.println("***********By DATE************");
	   			int[] score2 = History.byDate(text);
	   			for(int i=0; i<score2.length;i++){
	   				//for(int j=0;j<5; j++)
	   			 System.out.println("Player1: " + text.get(score2[i]*5));
	   			 System.out.println("Player2: " + text.get(score2[i]*5+1));
	   			 System.out.println("Player1 Score: " + text.get(score2[i]*5+2));
	   			System.out.println("Player2 Score: " + text.get(score2[i]*5+3));
	   			System.out.println("Date Played: " + text.get(score2[i]*5+4));
	   			}
	   			} catch (FileNotFoundException e) {
	   			// TODO Auto-generated catch block
					e.printStackTrace();
	   		 }
	   	 }});
	   	 
	   	 JMenuItem gamesPlayed= new JMenuItem("All Games Played");
	   	 gamesPlayed.setToolTipText("View List of Games Played");

	   	 gamesPlayed.addActionListener(new ActionListener() {
	   		 
	   		 public void actionPerformed(ActionEvent event) {

	   			 sout = jta.getText();
	   			 char[] stringToCharArray = sout.toCharArray();
	   			 try {
	   				 for (char output : stringToCharArray) {
	   					 out.write((int) output);
	   				 }
	   			 } catch (IOException e) {
	   				 // TODO Auto-generated catch block
	   				 e.printStackTrace();
	   			 } finally {

	   				 if (out != null) {
	   					 try {
	   						 out.close();
	   					 } catch (IOException e) {
	   						 // TODO Auto-generated catch block
	   						 e.printStackTrace();
	   					 }
	   				 }
	   			 }

	   		 }
	   	 });
	   	 
	   	 JMenuItem help= new JMenuItem("How To Play");
	   	 help.setToolTipText("General Description and Instrucions");

	   	 help.addActionListener(new ActionListener() {
	   		 
	   		 public void actionPerformed(ActionEvent event) {

	   			 sout = jta.getText();
	   			 char[] stringToCharArray = sout.toCharArray();
	   			 try {
	   				 for (char output : stringToCharArray) {
	   					 out.write((int) output);
	   				 }
	   			 } catch (IOException e) {
	   				 // TODO Auto-generated catch block
	   				 e.printStackTrace();
	   			 } finally {

	   				 if (out != null) {
	   					 try {
	   						 out.close();
	   					 } catch (IOException e) {
	   						 // TODO Auto-generated catch block
	   						 e.printStackTrace();
	   					 }
	   				 }
	   			 }

	   		 }
	   	 });

	   	 JMenuItem exitM = new JMenuItem("Exit");

	   	 exitM.setToolTipText("Exit application");

	   	 exitM.addActionListener(new ActionListener() {
	   		 
	   		 public void actionPerformed(ActionEvent event) {
	   			 System.exit(1);
	   		 }
	   	 });

	     newGameMenu.add(currentPlayers);	 
	   	 newGameMenu.add(selectNewPlayers);
	   	 firstPlayerMenu.add(registerPlayer);
	   	 historyMenu.add(highScore);
	   	 historyMenu.add(gamesPlayed);
	   	 helpMenu.add(help);
	   	 exitMenu.add(exitM);
	   	 menubar.add(newGameMenu);
	   	 menubar.add(firstPlayerMenu);
	   	 menubar.add(historyMenu);
	   	 menubar.add(helpMenu);
	   	 menubar.add(exitMenu);

	   	 jf.setJMenuBar(menubar);


	    }

	}

	
	
