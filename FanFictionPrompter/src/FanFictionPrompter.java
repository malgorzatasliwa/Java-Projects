import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FanFictionPrompter extends JFrame implements ActionListener {
	
	private JButton showAnother;
	private JLabel prompt0, prompt1, prompt2, prompt3, prompt4, prompt5;
	private JLabel showPrompt1, showPrompt2, showPrompt3, showPrompt4, showPrompt5;
	
	private String[] hero1 = {"Tyler", "Zane", "Nicholas", "Kelly"};
	private String[] hero2 = {"Tyler", "Zane", "Nicholas", "Kelly"};
	private String[] rating = {"General", "Teen", "Mature"};
	private String[] location = {"Miami","Hospital","Afghanistan","Scotland"};
	private String[] prompt = {"Holidays","Pie","Baseball","Brawl" };
	
	
	public FanFictionPrompter() { 
		
		frameSet(); 		
						
		buttonSet(); 
		
		titleSet();		
		
		hero1Set();
		
		hero2Set();
		
		ratingSet();
		
		locationSet();
		
		promptSet();
		
		hero1Show(); 
		
		hero2Show();
		
		pickRight();
		
		ratingShow();
		
		locationShow();
		
		promptShow();
				
	}

	private void promptShow() {
		showPrompt5 = new JLabel(prompt[new Random().nextInt(prompt.length)]);
		showPrompt5.setBounds(200, 140, 100, 20);
		add(showPrompt5);
	}

	private void locationShow() {
		showPrompt4 = new JLabel(location[new Random().nextInt(location.length)]);
		showPrompt4.setBounds(200, 120, 100, 20);
		add(showPrompt4);
	}

	private void ratingShow() {
		showPrompt3 = new JLabel(rating[new Random().nextInt(rating.length)]);
		showPrompt3.setBounds(200, 100, 100, 20);
		add(showPrompt3);
	}

	private void hero2Show() {
		showPrompt2 = new JLabel(hero2[new Random().nextInt(hero2.length)]);
		showPrompt2.setBounds(200, 80, 100, 20);
		add(showPrompt2);
	}

	private void hero1Show() {
		showPrompt1 = new JLabel(hero1[new Random().nextInt(hero1.length)]);
		showPrompt1.setBounds(200, 60, 100, 20);
		add(showPrompt1);
	}

	private void promptSet() {
		prompt5 = new JLabel("Prompt:");
		prompt5.setBounds(20, 140, 100, 20);
		prompt5.setForeground(Color.BLUE);
		add(prompt5);
	}

	private void locationSet() {
		prompt4 = new JLabel("Location:");
		prompt4.setBounds(20, 120, 100, 20);
		prompt4.setForeground(Color.BLUE);
		add(prompt4);
	}

	private void ratingSet() {
		prompt3 = new JLabel("Rating:");
		prompt3.setBounds(20, 100, 100, 20);
		prompt3.setForeground(Color.BLUE);
		add(prompt3);
	}

	private void hero2Set() {
		prompt2 = new JLabel("Hero 2:");
		prompt2.setBounds(20, 80, 100, 20);
		prompt2.setForeground(Color.BLUE);
		add(prompt2);
	}

	private void hero1Set() {
		prompt1 = new JLabel("Hero 1:");
		prompt1.setBounds(20, 60, 100, 20);
		prompt1.setForeground(Color.BLUE);
		add(prompt1);
	}

	private void titleSet() {
		prompt0 = new JLabel("Stuck for fic ideas? Feel free to use the suggestions.");
		prompt0.setBounds(20, 20, 300, 20);
		prompt0.setForeground(Color.BLUE);
		add(prompt0);
	}

	private void frameSet() {
		setSize(500,300);
		setTitle("FanFictionPromter");
		getContentPane().setBackground(Color.PINK); 
		setLayout(null);
	}

	private void buttonSet() {
		showAnother = new JButton("Another!"); 
		showAnother.setBounds(20, 200, 100, 20); 
		add(showAnother);						
		showAnother.addActionListener(this);
	}
	
	public static void main(String[] args) {
		
		final FanFictionPrompter myWindow = new FanFictionPrompter(); 
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		myWindow.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) { 
		
		showPrompt1.setText(hero1[new Random().nextInt(hero1.length)]);
		showPrompt2.setText(hero2[new Random().nextInt(hero2.length)]);
		pickRight();
		showPrompt3.setText(rating[new Random().nextInt(rating.length)]);
		showPrompt4.setText(location[new Random().nextInt(location.length)]);
		showPrompt5.setText(prompt[new Random().nextInt(prompt.length)]);
		
		
	}

	private void pickRight() { 
		while ((showPrompt1.getText()).equals(showPrompt2.getText())) {
			showPrompt2.setText(hero2[new Random().nextInt(hero2.length)]);
		}
	}
	
}
