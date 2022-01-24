import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class View extends JFrame {
	
	private Model model;
	private Controller controller;
	
	
	JPanel panel = (JPanel) this.getContentPane();
	JLabel label = new JLabel("Text to correct :");
	JLabel label2 = new JLabel("");
	JScrollPane pane;
	JTextArea text = new JTextArea();
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File"); 	 
	JMenu dictMenu = new JMenu("Dictionnary");
	JMenu checkMenu = new JMenu("Verify");
	JMenuItem openItem = new JMenuItem("Open");
	JMenuItem saveItem = new JMenuItem("Save");
	JMenuItem dictItem = new JMenuItem("Load");
	JMenuItem checkItem = new JMenuItem("Execute");
	JComboBox wordList = new JComboBox();
	JButton changeButton = new JButton("Change");
	
	View(){
		
		model=new Model(this);
		controller = new Controller(model);
		
		openItem.addActionListener(controller.new OpenClass());
		saveItem.addActionListener(controller.new SaveClass());
		dictItem.addActionListener(controller.new DictClass());
		checkItem.addActionListener(controller.new CheckClass());
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		dictMenu.add(dictItem);
		checkMenu.add(checkItem);
		menuBar.add(fileMenu);
		menuBar.add(dictMenu);
		menuBar.add(checkMenu);
		
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setFont(new Font("Ariel", Font.PLAIN,20));
		text.addMouseListener(controller.new CorrectClass());
		text.getDocument().addDocumentListener(controller.new HighlightFlag());
		
		pane =new JScrollPane(text);
		pane.setPreferredSize(new Dimension(450,350));
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		label2.setPreferredSize(new Dimension (450,20));
		
		wordList.setPreferredSize(new Dimension (100, 20));
		
		changeButton.setPreferredSize(new Dimension(100,20));
		changeButton.addActionListener(controller.new ChangeClass());

		panel.add(menuBar);
		panel.add(label);
		panel.add(pane);
		panel.add(label2);
		panel.add(wordList);
		panel.add(changeButton);
		
		this.setJMenuBar(menuBar);
		this.setTitle("Spelling Corrector ");
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		
		
		this.setVisible(true);
	}
}