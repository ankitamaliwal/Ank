package Dictionary;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class DictionaryTest {

	public static void main(String[] args) {
		DictionaryUtil dictionary = new DictionaryUtil();
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		
			//check if string has spaces
			if (str.contains(" "))
				{str = str.replace(" ", "");
				System.out.println(str);
				}
			
			//check if string is empty or null
			if ((str.length() == 0) || (str == null)) {
				System.out.println("No english words from the entered string");
			} 
			
			else
			{
				//convert the string to lowercase
				str = str.toLowerCase();
				
				// collecting all possible strings in a hash set
				HashSet<String> possibleWords = new HashSet<String>();
				
				// collecting all english words from the string in a hash set
				HashSet<String> englishWords = new HashSet<String>();
				possibleWords = dictionary.allPossibleWords(str);
				Iterator<String> itr = possibleWords.iterator();
				while (itr.hasNext()) {
					String check = itr.next();
					if (dictionary.isEnglishWord(check)) {
						englishWords.add(check);
					}
				}
				System.out.println("All strings derived from '" + str + "' are: " + possibleWords);
				System.out.println("All english words of '" + str + "' are: " + englishWords);
			}
		}
	}


