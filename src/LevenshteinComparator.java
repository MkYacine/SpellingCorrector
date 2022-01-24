import java.util.Comparator;

//réf:
//https://www.codejava.net/java-core/collections/sorting-arrays-examples-with-comparable-and-comparator


//an instance of this class can be used to sort an array of strings by levenshtein distance
//for a chosen string s


public class LevenshteinComparator implements Comparator<String>{
	
	String s;
	
	LevenshteinComparator(String s){
		this.s=s;
	}
	
	//calculates levenshtein distance between two strings s1 and s2
	public int levenshtein(String s1, String s2) {
		int edits[][]=new int[s1.length()+1][s2.length()+1];
	     for(int i=0;i<=s1.length();i++)
	         edits[i][0]=i;
	     for(int j=1;j<=s2.length();j++)
	         edits[0][j]=j;
	     for(int i=1;i<=s1.length();i++){
	         for(int j=1;j<=s2.length();j++){
	             int u=(s1.charAt(i-1)==s2.charAt(j-1)?0:1);
	             edits[i][j]=Math.min(
	                             edits[i-1][j]+1,
	                             Math.min(
	                                edits[i][j-1]+1,
	                                edits[i-1][j-1]+u
	                             )
	                         );
	         }
	     }
	     return edits[s1.length()][s2.length()];
	}
	
	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return levenshtein(s,o1)-levenshtein(s,o2);
	}

}
