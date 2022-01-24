import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Dictionnaire {
	public ArrayList<String> arr = new ArrayList<String>();
	
	//load dictionnary from text file into data structure
	public void readDictionnary(File selectedFile){
			try {
				BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
				String line;
				while ((line = reader.readLine()) !=null) {
					this.arr.add(line);
				}
				reader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e) {					// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//check if string is contained in dictionnary (while ignoring case)
	public boolean containsIgnoreCase(String s) {
		for (int i=0; i<this.arr.size();i++) {
			if (s.equalsIgnoreCase(this.arr.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	//suggest 5 words from the dictionnary to replace a word
	public String[] suggest(String s) {
		String[] suggestions = new String[this.arr.size()];
		suggestions = this.arr.toArray(suggestions);
		Arrays.sort(suggestions, new LevenshteinComparator(s));
		if (suggestions.length<6) {
			return suggestions;
		}
		return Arrays.copyOfRange(suggestions, 0, 5);

	}
}
