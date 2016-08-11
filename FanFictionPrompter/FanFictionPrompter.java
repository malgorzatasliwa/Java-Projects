import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FanFictionPrompter extends JFrame implements ActionListener {
	
	// nie uzywaj notacji wegierskiej w nazywaniu zmiennych. Po to mamy IDE, by zobaczyc jaki jest typ zmiennej. Nawet w C++ powinno sie tego zakazac ;)
	private JButton bShowAnother;
	private JLabel lPrompt0, lPrompt1, lPrompt2, lPrompt3, lPrompt4, lPrompt5;
	private JLabel lShowPrompt1, lShowPrompt2, lShowPrompt3, lShowPrompt4, lShowPrompt5;
	
	// zmienne z malej litery. Zastanawiam sie, czy nazwa "Character" jest dobra i czy nie koliduje tutaj jakos myslowo z "char"
	private String[] Character1 = {"Tyler", "Zane", "Nicholas", "Kelly"};
	private String[] Character2 = {"Tyler", "Zane", "Nicholas", "Kelly"};
	private String[] Rating = {"General", "Teen", "Mature"};
	private String[] Location = {"Miami","Hospital","Afghanistan","Scotland"};
	private String[] Prompt = {"Holidays","Pie","Baseball","Brawl" };
	
	
	// za dlugi konstruktor. Metody powinny miec kilka, max kilkanascie linii. Puste linie idealnie tu wyznaczaja, gdzie moglaby sie zaczynac i konczyc metoda.
	// Takze wyekstraktuj do prywatnych metod
	public FanFictionPrompter() { 
		
		setSize(500,300);
		setTitle("FanFictionPromter");
		getContentPane().setBackground(Color.PINK); 
		setLayout(null); 		
						
		bShowAnother = new JButton("Another!"); 
		bShowAnother.setBounds(20, 200, 100, 20); 
		add(bShowAnother);						
		bShowAnother.addActionListener(this); 
		
		lPrompt0 = new JLabel("Stuck for fic ideas? Feel free to use the suggestions.");
		lPrompt0.setBounds(20, 20, 300, 20);
		lPrompt0.setForeground(Color.BLUE);
		add(lPrompt0);		
		
		lPrompt1 = new JLabel("Character 1:");
		lPrompt1.setBounds(20, 60, 100, 20);
		lPrompt1.setForeground(Color.BLUE);
		add(lPrompt1);
		
		lPrompt2 = new JLabel("Character 2:");
		lPrompt2.setBounds(20, 80, 100, 20);
		lPrompt2.setForeground(Color.BLUE);
		add(lPrompt2);
		
		lPrompt3 = new JLabel("Rating:");
		lPrompt3.setBounds(20, 100, 100, 20);
		lPrompt3.setForeground(Color.BLUE);
		add(lPrompt3);
		
		lPrompt4 = new JLabel("Location:");
		lPrompt4.setBounds(20, 120, 100, 20);
		lPrompt4.setForeground(Color.BLUE);
		add(lPrompt4);
		
		lPrompt5 = new JLabel("Prompt:");
		lPrompt5.setBounds(20, 140, 100, 20);
		lPrompt5.setForeground(Color.BLUE);
		add(lPrompt5);
		
		// te wszystkie randomy w tej klasie mozna oprzec o jednej instancji Random. I po prostu na niej wywolywac new nextInt
		// A moze w ogole warto zrobic osobna klase obslugujaca te tablice Stringow i majaca metody typu String randomLocation()?
		lShowPrompt1 = new JLabel(Character1[new Random().nextInt(Character1.length)]);
		lShowPrompt1.setBounds(200, 60, 100, 20);
		add(lShowPrompt1); 
		
		lShowPrompt2 = new JLabel(Character2[new Random().nextInt(Character2.length)]);
		lShowPrompt2.setBounds(200, 80, 100, 20);
		add(lShowPrompt2);
		
		pickRight();
		
		lShowPrompt3 = new JLabel(Rating[new Random().nextInt(Rating.length)]);
		lShowPrompt3.setBounds(200, 100, 100, 20);
		add(lShowPrompt3);
		
		lShowPrompt4 = new JLabel(Location[new Random().nextInt(Location.length)]);
		lShowPrompt4.setBounds(200, 120, 100, 20);
		add(lShowPrompt4);
		
		lShowPrompt5 = new JLabel(Prompt[new Random().nextInt(Prompt.length)]);
		lShowPrompt5.setBounds(200, 140, 100, 20);
		add(lShowPrompt5);
				
	}
	
	public static void main(String[] args) {
		// dobra praktyka jest oznaczac niemutowalne zmienne final. Czyli wstawiaj final tam, gdzie tylko sie da, np w ponizszej linijce.
		FanFictionPrompter myWindow = new FanFictionPrompter(); 
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		myWindow.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) { 
		// tutaj nie ma problemu, bo tablice sa male, ale dla ciezszych operacji, moze warto to uruchomic w osobnym watku?
		lShowPrompt1.setText(Character1[new Random().nextInt(Character1.length)]);
		lShowPrompt2.setText(Character2[new Random().nextInt(Character2.length)]);
		pickRight();
		lShowPrompt3.setText(Rating[new Random().nextInt(Rating.length)]);
		lShowPrompt4.setText(Location[new Random().nextInt(Location.length)]);
		lShowPrompt5.setText(Prompt[new Random().nextInt(Prompt.length)]);
		
		
	}

	private void pickRight() { 
		// Jesli porownujesz Stringi, to uzywaj equals(). == porownuje referencje.
		// Tutaj == dziala, bo odwoluje sie do tej samej referencji, ale gdzie indziej juz to moze nie zadzialac ;)
		while ((lShowPrompt1.getText()) == (lShowPrompt2.getText())) {
			lShowPrompt2.setText(Character2[new Random().nextInt(Character2.length)]);
		}
	}
	
}
