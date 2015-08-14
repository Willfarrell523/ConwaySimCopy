import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;


public class WindowClass extends JFrame {
	
	private JButton[][] buttons;
	private int size;
	lifeBoard board;
	
	private JSlider slider1;
	
	public WindowClass(String title, int size, lifeBoard board) {
		//super(title);
		setTitle(title);
		buttons = new JButton[size][size];
		this.size = size;
		this.board = board;
		
		//this.setSize(600, 600);
		this.init();
		this.setVisible(true);
		this.setLayout(new GridLayout(size, size));
		
		
		slider1 = new JSlider(JSlider.HORIZONTAL,500,5000,1000);
//		this.add(slider1);
		slider1.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println(slider1.getValue());
			}
			
		});
		
		
		//JButton button = null;
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				buttons[i][j] = new JButton("");
				buttons[i][j].addActionListener(new ButtonListener1());
//				buttons[i][j].setEnabled(false);
				this.add(buttons[i][j]);
			}
		} 
		
		//pack();
		setSize(1000, 700);
	}
	
	public void setDisplay()
	{
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(board.getSpot(i, j) != 0)
				{
					//buttons[i][j].setText("" +((char)(64 + board.getSpot(i, j))));
					buttons[i][j].setBackground(Color.GREEN);
				}
				else
				{
					//buttons[i][j].setText("");
					buttons[i][j].setBackground(Color.BLACK);
				}
				
				//buttons[i][j].setText("" + board.getSpot(i, j));
			}
		}
	}
	
	public void init() {
//		JLabel welcomeL = new JLabel("Welcome to Conway's Simulation of Life");
		
		//JButton button1 = new JButton("Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//button1.addActionListener(new ButtonListener1());
//		JPanel panel1 = new JPanel();
//		panel1.add(welcomeL);
		//panel1.add(button1);
		
//		this.add(panel1);
	}
	
	class ButtonListener1 implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			for(int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
				{
					if(event.getSource() == buttons[i][j])
					{
						if(board.getSpot(i, j) > 0)
						{
							board.kill(i, j);
							buttons[i][j].setBackground(Color.BLACK);
						}
						else
						{
							board.incrementLife(i, j);
							buttons[i][j].setBackground(Color.GREEN);
						}
					}
				}
			}
		}
	}

}
