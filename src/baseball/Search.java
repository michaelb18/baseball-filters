package baseball;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Search extends JFrame implements ActionListener, ListSelectionListener{
	private JButton search;
	private JLabel display;
	private JTextField player;
	private League l;
	private JList list;
	private JScrollPane jscp;
	private DefaultListModel<Player> listModel;
	public Search()
	{
		setTitle("Baseball");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		getContentPane().setBackground(Color.gray);
		
		player = new JTextField(20);
		search=new JButton("Search");
		display= new JLabel();
		
		listModel=new DefaultListModel();
		setLocation(500,300);
		list=new JList(listModel);
		list.addListSelectionListener(this);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		search.addActionListener(this);
		jscp=new JScrollPane(list);
		jscp.setBounds(150,150,200,300);
		
	
		setLayout(null);
		player.setBounds(150, 50, 200, 25);
		search.setBounds(200, 90, 100, 25);
		display.setBounds(50, 100, 400, 50);
		jscp.setVisible(false);
		player.addActionListener(this);
		add(jscp);
		add(search);
		add(player);
		add(display);
		
		l=new League("players.txt");
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		ArrayList<Player> aL=l.getPlayers();
		ArrayList<Player> good=l.findGoodBatters();
		for(int i=0;i<aL.size();i++)
		{
			if(player.getText().trim().equalsIgnoreCase(aL.get(i).getName().trim()))
			{
				if(aL.get(i) instanceof Batter){
					System.out.println("asd1");
					Batter bat=(Batter)aL.get(i);
					display.setText(aL.get(i).getName()+String.format("HR:%d RBI:%d BA:%.3f SB:%d",  bat.getHR(),bat.getRBIS(), bat.getBA(), bat.getSB()));
				}
				else
				{
					System.out.println("asd");
					Pitcher pitch=(Pitcher)aL.get(i);
					display.setText(aL.get(i).getName()+String.format("K:%d ERA:%.2f W:%d",  pitch.getk(),pitch.getERA(), pitch.getWins()));
				}
				if(good.contains(aL.get(i)))
				{
					
					getContentPane().setBackground(Color.green);
				}
				else
				{
					getContentPane().setBackground(Color.gray);
				}
				jscp.setVisible(false);
				return;
			}
			
		}
		//NOTE: List works for now, but it mokes more sense to have a table
		getContentPane().setBackground(Color.gray);
		display.setText("Player Not Found. Did you want:");
		jscp.setVisible(true);
		listModel.clear();


		ArrayList<Player> hits=l.getHits(player.getText());
		System.out.println(hits);
		for(int i=0;i<hits.size();i++)
		{
			listModel.add(i,hits.get(i));
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index=list.getSelectedIndex();
		
		if(index!=-1)
		{
			if(listModel.getElementAt(index) instanceof Batter){
				Batter bat=(Batter)listModel.getElementAt(index);
				display.setText(listModel.getElementAt(index).getName()+String.format("HR:%d RBI:%d BA:%.3f SB:%d",  bat.getHR(),bat.getRBIS(), bat.getBA(), bat.getSB()));
			}
			else
			{
				Pitcher pitch=(Pitcher)listModel.getElementAt(index);
				display.setText(listModel.getElementAt(index).getName()+String.format("K:%d ERA:%.2f W:%d",  pitch.getk(),pitch.getERA(), pitch.getWins()));
			}
		}
		else 
			return;
		///ArrayList<Player> aL=l.getPlayers();
		if(l.findGoodBatters().contains(listModel.getElementAt(index)))
		{
			getContentPane().setBackground(Color.green);
		}
		else
		{
			getContentPane().setBackground(Color.gray);
		}
			
	}
}
