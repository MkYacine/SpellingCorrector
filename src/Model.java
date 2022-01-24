import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class Model {
	private View view;
	private Dictionnaire myDict = new Dictionnaire();
	private boolean highlight = false; //reflects if word highlighting has been done or not
	private String wordToChange; //stores the word to correct 
	
	Model(View view){
		this.view=view;
	}
	
	
	
	
	//open a text file into the textarea
	public void open() {
		highlight = false; //set highlight to false when a new text is inserted to textfield
		String textSheet = "";
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				FileReader reader = new FileReader(selectedFile);
				int data = reader.read();
				while (data!=-1) {
					String s = Character.toString((char)data);
					textSheet+=s;
					data=reader.read();
				}
				view.text.setText(textSheet);
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {					// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.label2.setText("File opened");
		}
	}
	
	//save textarea content into a file
	public void save() {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				FileWriter writer = new FileWriter(selectedFile);
				System.out.println(view.text.getText());
				writer.write(view.text.getText());
				writer.close();
			}catch (IOException e) {					// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.label2.setText("File saved");
		}
	}
	
	//load a dictionnary text file into a dictionnary data structure
	public void dict() {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
		 myDict.readDictionnary(selectedFile);
		}
		view.label2.setText("Dictionnary loaded");
	}
	
	//highlights textarea words that are not in the dictionnary
	public void check() {
		//réf : https://stackoverflow.com/questions/20341719/how-to-highlight-a-single-word-in-a-jtextarea
		if (view.text.getText().length()==0) {
			view.label2.setText("You must input text");
			return;
		}
		if (myDict.arr.size()==0){
			view.label2.setText("You must select a dictionnary");
			return;
		}
		Highlighter highlighter = view.text.getHighlighter();
		HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
		String text=view.text.getText();
		String word="";
		
		//go through textfield while checking if each word is in the dict
		for (int i=0; i<text.length(); i++) {
				if (Character.isLetter(text.charAt(i))) { 
					word+=text.charAt(i);
				} else {
					if (!(myDict.containsIgnoreCase(word)) && word.length()>0) {
						try {
							highlighter.addHighlight(i-word.length(), i, painter);
						} catch (BadLocationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					word ="";
				}
			}
		//check last word
		if (!(myDict.containsIgnoreCase(word))) {
			try {
				highlighter.addHighlight(text.length()-word.length(), text.length(), painter);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		view.label2.setText("Verification done");
		highlight=true; //highlighting has been done
	}
	public void correct() {
		int cursorPos = view.text.getCaretPosition() ;
		int leftLimit = cursorPos;
		int rightLimit = cursorPos;
		String text = view.text.getText();
		String word;
		String[] suggestions = new String[5];
		
		//reset label2 and wordlist everytime user clicks
		view.label2.setText("");
		view.wordList.removeAllItems();
		
		//check if textarea and dictionnary have been set
		//check if highlighting has been done
		if (text.length()==0 || myDict.arr.size()==0 || !highlight) {
			return;
		}
		
		//return if user clicks on end of textfield
		if (cursorPos==text.length()) {
			return;
		}
			
		
			//check if user clicked on a word
			if (!(Character.isAlphabetic(text.charAt(cursorPos)))) {
				return;
			}
			
			
			//find word that user clicked on
			while (leftLimit>0 && Character.isLetter(text.charAt(leftLimit))) {
				leftLimit--;
			}
			if (!(Character.isLetter(text.charAt(leftLimit)))) {
				leftLimit++;
			}
			while (rightLimit<text.length()-1 && Character.isLetter(text.charAt(rightLimit))) {
				rightLimit++;
			}
			if (!(Character.isLetter(text.charAt(rightLimit)))) {
				rightLimit--;
			}
			word = text.substring(leftLimit, rightLimit+1);
			
			//check if word is in dict
			if (myDict.containsIgnoreCase(word)) {
				return;	
			}
			
			view.label2.setText("The words suggested for "+word+" are :");
			suggestions=myDict.suggest(word);
			for (int i=0; i<suggestions.length; i++) {
				view.wordList.addItem(suggestions[i]);
			}
			wordToChange = word;
		
	}
	
	//replace highlighted word with user-selected word 
	public void change() {
		Highlighter highlighter = view.text.getHighlighter();
		String textContent = view.text.getText();
		String wordToInsert = (String) view.wordList.getSelectedItem();
		
		if (view.wordList.getItemCount()==0) {
			view.label2.setText("You must first input text, load a dictionnary, then verify");
			return;
		}
		
		while (textContent.contains(wordToChange)) {
			int i = textContent.indexOf(wordToChange);
			view.text.replaceRange(wordToInsert, i, i+wordToChange.length());
			highlighter.removeAllHighlights();
			check();
			textContent = view.text.getText();
		}
		view.label2.setText("Word corrected!");
		
	}
	
	//set highlight to false when text has been modified without calling check()
	public void flag() {
		highlight=false;
	}
}
