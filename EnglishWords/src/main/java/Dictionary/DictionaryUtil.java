package Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class DictionaryUtil {

	/**
	 * @param: any given string to validate from dictionary
	 * @return: boolean if the word is found in the available dictionary
	 */
	public boolean isEnglishWord(String word) 
	{
		boolean match = false;
		try {
			// create a reader instance
			BufferedReader br = new BufferedReader(
					new FileReader("C:\\Users\\maliw\\eclipse-workspace\\EnglishWords\\MockDictionary.txt"));
			// read until end of file
			String line;
			while ((line = br.readLine()) != null) {
				// when string found
				if (line.equals(word)) {
					match = true;
					break;
				}
			}
			// close the reader
			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return match;
	}

	/**
	 * Find all possible subsequences of a given string
	 * 
	 * @param input any given string
	 * @return String list with all possible sub-sequences of the given input e.g.,
	 *         subsequences of abc would be 'a', 'b', 'c', 'ab', 'ac', 'bc', 'abc'
	 */
	public static HashSet<String> getSubsequences(String str) {

		// Declare a word list
		HashSet<String> words = new HashSet();
		int len = str.length();

		// Total number of non-empty subsequence in any String is 2^length-1
		int limit = (int) Math.pow(2, len);

		// i=0, corresponds to empty subsequence
		for (int i = 1; i <= limit - 1; i++) {
			// Subsequence for binary pattern i

			String sub = "";
			for (int j = 0; j < str.length(); j++) {
				// Check if jth bit in binary is 1
				if ((i & (1 << j)) != 0) {
					// If jth bit is 1, include it in subsequence
					sub += str.charAt(j);
				}
			}
			words.add(sub);
		}
		return words;
	}

	/**
	 * Find all permutation words in a given string considering permutation of number 'n' will be n!
	 * factorial of n, i.e., n! = n*(n-1)*(n-2)*(n-3)...*1 e.g., 4! = 4*3*2*1
	 * @param input any given string
	 * @return String list with all possible permutations of the given input * e.g.,
	 *         subsequences of abc would be 'abc', 'acb', 'bac', 'bca', 'cab', 'cba;
	 */
	public static HashSet<String> getPermutations(String str) {

		// create a set to avoid duplicate permutation

		HashSet<String> permutation = new HashSet<String>();
		int[] factorials = new int[str.length() + 1];
		
		//storing factorial values
		factorials[0] = 1;		
		for (int i = 1; i <= str.length(); i++) {
			factorials[i] = factorials[i - 1] * i;
		}

		for (int n = 0; n < factorials[str.length()]; n++) {
			String permStr = "";
			String restOfStr = str;
			int pos = n;
			for (int j = str.length()-1; j >= 0; j--) {
				int selected = pos / factorials[j]; 
				permStr = permStr + restOfStr.charAt(selected);
				pos = pos % factorials[j];
				restOfStr = restOfStr.substring(0, selected) + restOfStr.substring(selected + 1);
			}
			permutation.add(permStr);
		}
		return permutation;
	}

	public static HashSet<String> allPossibleWords(String str) {
		HashSet<String> subSequences = new HashSet<String>();
		HashSet<String> permutations = new HashSet<String>();
		HashSet<String> allWords = new HashSet<String>();
		subSequences = getSubsequences(str);

		// print all permutations from the sub-sequence list
		Iterator<String> itr = subSequences.iterator();
		while (itr.hasNext()) {
			String subs = itr.next();
			permutations = getPermutations(subs);
			Iterator<String> itr1 = permutations.iterator();
			while (itr1.hasNext()) {
				String perms = itr1.next();
				allWords.add(perms);
			}
		}
		return allWords;
	}

}