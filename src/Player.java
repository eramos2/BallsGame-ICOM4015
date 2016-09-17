import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Player {

	
	public Player(){
		
	}
	
	public static void setNewPlayer(JFrame jf/**String userName, String passWord*/) throws FileNotFoundException{
		File f = new File("players.txt");
		ArrayList<String> text = new ArrayList<String>();
		if(f.exists() && !f.isDirectory()) { 
		//System.out.println(f.exists() +" " + f.isDirectory());
		Scanner in = new Scanner(f);
		while(in.hasNextLine()){
			String s = in.nextLine();
			text.add(s);
			//System.out.println(s);
		}
		in.close();
		}
		String userName=null;
	    String passWord=null;

	    while((userName == null) || (userName.length() == 0) || (passWord.length() !=4)){
	    	userName = (String)JOptionPane.showInputDialog(jf,"Please enter a new UserName:\n",
					"Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,null,null);
			passWord = (String)JOptionPane.showInputDialog(jf,"Please enter the 4 char PassWord:\n",
					"Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,null,null);
	    	for(int i=0; i<text.size(); i++){
	    		if(text.get(i).equalsIgnoreCase(userName)){
	    			userName = (String)JOptionPane.showInputDialog(jf,"Invalid User Name\n" + "Please enter a new UserName:\n",
	    					"Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,null,null);
	    			passWord = (String)JOptionPane.showInputDialog(jf,"Please enter the 4 char PassWord:\n",
	    					"Customized Dialog",JOptionPane.PLAIN_MESSAGE,null,null,null);
	    			i=0;
	    		}
	    	}
		PrintWriter out = new PrintWriter("players.txt");
		for(int i=0; i<text.size(); i++){ 
			out.println(text.get(i));
			System.out.println(text.get(i));
		}
		out.println(userName);
		out.println(passWord);
		out.println("");
		out.println("");
		
		out.close();
	    }
	}
}
