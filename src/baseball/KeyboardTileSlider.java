package baseball;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class KeyboardTileSlider extends JFrame implements KeyListener{

	private BufferedImage image;
	private PicPanel[][] allPanels;
	int moves;
	private int totRow;		// location of Totoro
	private int totCol;

	public KeyboardTileSlider(){
		moves=0;
		setSize(375,375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Tile Slider");
		getContentPane().setBackground(Color.black);
		this.addKeyListener(this);
		allPanels = new PicPanel[4][4];
		setLayout(new GridLayout(4,4,2,2));
		setBackground(Color.black);

		ArrayList<Integer> nums=new ArrayList<Integer>();
		for(int i=1;i<=15;i++)
			nums.add(i);
		nums.add(-1);
		/*for(int r=0;r<allPanels.length;r++)
		{
			for(int c=0;c<allPanels[0].length;c++)
			{
				allPanels[r][c]=new PicPanel();
				add(allPanels[r][c]);
			}
		}
		allPanels[0][0].setNumber(1);
		allPanels[0][1].setNumber(2);
		allPanels[0][2].setNumber(3);
		allPanels[0][3].setNumber(4);
		allPanels[1][0].setNumber(5);
		allPanels[1][1].setNumber(6);
		allPanels[1][2].setNumber(7);
		allPanels[1][3].setNumber(8);
		allPanels[2][0].setNumber(9);
		allPanels[2][1].setNumber(10);
		allPanels[2][2].setNumber(11);
		allPanels[2][3].setNumber(12);
		allPanels[3][0].setNumber(14);
		allPanels[3][1].setNumber(13);
		allPanels[3][2].setNumber(-1);
		allPanels[3][2].removeNumber();
		allPanels[3][3].setNumber(15);
		totRow=3;
		totCol=2;*/
		for(int r=0;r<allPanels.length;r++){
			for(int c=0;c<allPanels[0].length;c++){
				allPanels[r][c]=new PicPanel();
				int index=(int)(Math.random()*nums.size());
				int number=nums.get(index);
				
				allPanels[r][c].setNumber(number);
				if(nums.get(index)==-1)
				{
					totRow=r;
					totCol=c;
				}
				nums.remove(index);
				add(allPanels[r][c]);
			}
		}
		allPanels[totRow][totCol].removeNumber();

	try {
		image = ImageIO.read(new File("totoro.jpg"));


	} catch (IOException ioe) {
		JOptionPane.showMessageDialog(null, "Could not read in the pic");
		System.exit(0);
	}	


	
	setVisible(true);
}

public void keyPressed(KeyEvent arg0) {
	System.out.println(arg0.getKeyCode());
	if(arg0.getKeyCode()==KeyEvent.VK_LEFT&&checkBounds(totRow,totCol-1))
	{
		System.out.println("Asd");
		int number=allPanels[totRow][totCol-1].number;
		allPanels[totRow][totCol-1].removeNumber();
		allPanels[totRow][totCol-1].setNumber(-1);
	
		allPanels[totRow][totCol].setNumber(number);
		totCol--;
	}
	else if(arg0.getKeyCode()==KeyEvent.VK_UP&&checkBounds(totRow-1,totCol))
	{
		int number=allPanels[totRow-1][totCol].number;
		allPanels[totRow-1][totCol].removeNumber();
		allPanels[totRow-1][totCol].setNumber(-1);
		allPanels[totRow][totCol].setNumber(number);
		totRow--;
	}
	else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT&&checkBounds(totRow,totCol+1))
	{
		int number=allPanels[totRow][totCol+1].number;
		allPanels[totRow][totCol+1].removeNumber();
		allPanels[totRow][totCol+1].setNumber(-1);
		
		allPanels[totRow][totCol].setNumber(number);
		totCol++;
	}
	else if(arg0.getKeyCode()==KeyEvent.VK_DOWN&&checkBounds(totRow+1,totCol))
	{
		int number=allPanels[totRow+1][totCol].number;
		allPanels[totRow+1][totCol].removeNumber();
		allPanels[totRow+1][totCol].setNumber(-1);
	
		allPanels[totRow][totCol].setNumber(number);
		totRow++;
	}
	allPanels[totRow][totCol].removeNumber();
	moves++;
	if(checkWin())
	{
		JOptionPane.showMessageDialog(null,"You win with "+ moves+ " moves");
	}
}

private boolean checkBounds(int r,int c)
{
	return r<allPanels.length&&r>=0&&c<allPanels[0].length&&c>=0;
}
private boolean checkWin()
{
	int i=1;
	for(int r=0;r<allPanels.length;r++)
	{
		for(int c=0;c<allPanels[0].length;c++){
			System.out.println(i+" "+allPanels[r][c].number);
			if(i!=allPanels[r][c].number)
				if((i!=16&&allPanels[r][c].number!=-1))
				return false;
			i++;
		}
	}
	return true;
}
//leave this empty
public void keyReleased(KeyEvent arg0) {


}

//leave this empty	
public void keyTyped(KeyEvent arg0) {

}

class PicPanel extends JPanel{


	private int width = 76;
	private int height = 80;	//dimensions of the Panel 

	private int number=-1;		// -1 when Totoro is at that position.
	private JLabel text;

	public PicPanel(){

		setBackground(Color.white);
		setLayout(null);

	}		

	//changes the panel to have the given number
	public void setNumber(int num){	

		number = num;
		text = new JLabel(""+number,SwingConstants.CENTER);
		text.setFont(new Font("Calibri",Font.PLAIN,55));
		text.setBounds(0,35,70,50);
		this.add(text);

		repaint();
	}

	//replaces the number with Totoro
	public void removeNumber(){
		this.remove(text);
		number = -1;
		repaint();
	}

	public Dimension getPreferredSize() {
		return new Dimension(width,height);
	}

	//this will draw the image or the number
	// called by repaint and when the panel is initially drawn
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if(number == -1)
			g.drawImage(image,8,0,this);
	}

}



public static void main(String[] args){
	//new KeyboardTileSlider();
}



}
