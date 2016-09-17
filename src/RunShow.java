import javax.swing.JFrame;
import javax.swing.JPanel;


public class RunShow extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public RunShow(JFrame frame){
		this.frame = frame;
	}
	
	public void run(){
		frame.setContentPane(new BallWorld(800, 700));
	}
	
}
