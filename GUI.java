import javax.swing.*;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GUI extends JFrame{

	private JButton[] buttons;
	private JFrame jFrame = new JFrame();
	private JPanel buttonsPanel;
	private JPanel displayPanel;
	private JTextArea displayOperations;
	private HashMap<Integer, Integer> ladder, snake;
	private JButton btn;
	private Color[] ladderColors;
	private Color[] snakeColors;
	private int ladderColorCount, snakeColorCount;
	JButton ladderBottomBtnTemp;
	JButton ladderTopBtnTemp;
	JButton snakeMouthTempBtn;
	JButton snakeTailTempBtn;
	
	//default constructor ==============================================
	public GUI(){
		
		//colors array
		ladderColors = new Color[]
				{
				    Color.decode("#e67e22"), Color.BLUE, Color.decode("#e74c3c"), Color.GREEN,
				    Color.BLACK, Color.PINK, Color.decode("#7f8c8d")
				};
		snakeColors = new Color[]
				{
				    Color.CYAN, Color.GRAY, Color.MAGENTA, Color.decode("#1abc9c"),
				    Color.decode("#3498db"), Color.decode("#9b59b6"), Color.decode("#34495e")
				};
		
		//initialize ladders and snakes arrays
		ladder = new HashMap<Integer, Integer>();
		
		ladder.put(3, 21); 
		ladder.put(8, 30);
		ladder.put(28, 77);  
		ladder.put(58, 84); 
		ladder.put(75, 86);
		ladder.put(80, 91); 
		ladder.put(90, 100);
		
		snake = new HashMap<Integer, Integer>();
		snake.put(17, 13);
		snake.put(62, 22);
		snake.put(52, 29);
		snake.put(57, 40);
		snake.put(95, 51);
		snake.put(97, 79);
		snake.put(88, 34);
		
		
		buttons = new JButton[100];
		buttonsPanel = new JPanel();
		displayPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(5, 4, 10, 20));//set the layout of buttonsPanel
		
		
		displayPanel.setLayout(new FlowLayout());
		displayOperations = new JTextArea("Hello", 10, 100);
		displayOperations.setEditable(false);
		displayPanel.add(displayOperations);
		
		//create buttons
		for(int i = buttons.length; i > 0; i--)
	    {
			
			
			//first check if button is already made by inner if conditions
			if((buttons[i-1] == null)){
				btn =  new JButton("" + i); //with default text
				buttons[i-1]  = btn; //add button to buttons array
				
			}
			
			//if ladder bottom ========================================================
			if(i == 3 || i== 8 || i == 28 || i == 58 || i == 75 || i == 80 || i == 90){
				
				//change button text to ladder bottom
				if(!(buttons[i-1] == null)){
					ladderBottomBtnTemp = buttons[i-1]; //get current button from buttons array
				}
				
				//make button if not there
				else{
					ladderBottomBtnTemp = new JButton();
					buttons[i-1] = ladderBottomBtnTemp;
				}
				
				ladderBottomBtnTemp.setText("LB" + i);
				ladderBottomBtnTemp.setForeground(Color.white);
				ladderBottomBtnTemp.setBackground(ladderColors[ladderColorCount]);
				
				//change button text to ladder top
				//get corresponding value from hashmap
				
				if(!(buttons[(ladder.get(i))-1] == null)){
					ladderTopBtnTemp = buttons[(ladder.get(i))-1]; //get current button from buttons array
				}
				
				//make button if not there
				else{
					ladderTopBtnTemp = new JButton();
					buttons[(ladder.get(i))-1] = ladderTopBtnTemp;
				}
				
				ladderTopBtnTemp.setText("LT" + ladder.get(i));//change text
				ladderTopBtnTemp.setForeground(Color.white);
				ladderTopBtnTemp.setBackground(ladderColors[ladderColorCount]); //change bg color
				
				ladderColorCount++;
			}
			
			//if snake mouth ===========================================================
			if(i == 17 || i == 52 || i == 57 || i == 62 || i == 88 || i == 95 || i == 97){
				
				//if button is created already
				if(!(buttons[i-1] == null)){
					snakeMouthTempBtn = buttons[i-1];
				}
				
				//create the button otherwise
				else{
					snakeMouthTempBtn = new JButton();
					buttons[i-1] = snakeMouthTempBtn;
				}
				
				
				snakeMouthTempBtn.setText("SM" + i);
				snakeMouthTempBtn.setForeground(Color.white);
				snakeMouthTempBtn.setBackground(snakeColors[snakeColorCount]);
				
				//do the same with snake tail ==================================================
				//for 97 it will check 78(79-1)
				if(!(buttons[(snake.get(i))-1] == null)){
					snakeTailTempBtn = buttons[(snake.get(i))-1];
					
				}
				else{
					//for 97 it will check 78(79-1)
					snakeTailTempBtn = new JButton();
					buttons[(snake.get(i))-1] = snakeTailTempBtn;
				}
				
				snakeTailTempBtn.setText("ST" + snake.get(i));
				snakeTailTempBtn.setForeground(Color.white);
				snakeTailTempBtn.setBackground(snakeColors[snakeColorCount]);
				
				snakeColorCount++;
				
			}
			
			//add button to buttonsPanel
			buttonsPanel.add(buttons[i-1]);
			
	    }
        
		//set the frame layout
		setLayout(new BorderLayout());
		//add to frame ==================================
		add(displayPanel, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.CENTER);
		
		
		//set frame properties ============================================================
		setTitle("Snakes and Ladders"); // "this" Frame sets title
	    setExtendedState(JFrame.MAXIMIZED_BOTH);     // "this" Frame sets initial size
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);       // "this" Frame shows
	}
	
}
