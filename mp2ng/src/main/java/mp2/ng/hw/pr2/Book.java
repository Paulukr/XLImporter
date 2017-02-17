package mp2.ng.hw.pr2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book {
	List<Sentence> theText = new ArrayList<>();
	List<String> lexicon = new ArrayList<>();

	public static void main(String[] args) {
		String path = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\src\\main\\java\\mp2\\ng\\hw\\pr2\\data\\Galaxy.txt";//Weir_-_The_Martian//Galaxy
		/*
		 * String path2 = "E:\\Galaxy.txt";
		 * 
		 * String set = "E:\\Galax232y.t     xt   ?!,:;@#$ \"\'"; String r =
		 * set.replaceAll("[[^A-Za-z0-9]&&[^\\.?!,:;@#$ \"\']]",
		 * "*").replaceAll(" +", " ");
		 * 
		 * System.out.println(r);
		 */
		Book book = new Book();
		book.init2(path);

		LexemPool lexemPool;

		Pattern marks = Pattern.compile("[\\W]+");
		Pattern endings = Pattern.compile("[\\.\\?!\"]+");

		Matcher marksMatcher = marks.matcher("?\"");
		Matcher endingsMatcher = endings.matcher("?\"");

		System.out.println("mark Matches " + marksMatcher.matches());
		System.out.println("endings Matches " + endingsMatcher.matches());
		
		book.First() ;
	}

	public void First() {
		HashMap<Lexeme, Integer> words = new HashMap<>();
		List<Sentence> withFrequentWord = new ArrayList<>();

		for (Sentence sentence : theText) {
			sentence.lexemas.stream().distinct().forEach(a -> {
				// int hash = a.hashCode();
				Integer old = words.get(a);
				words.put(a, (old == null ? 0 : old) + 1);
			});
		}

		// words.entrySet().stream().mapToInt(Entry::getValue).sorted().forEachOrdered(a
		// -> System.out.println(a + " "));
		OptionalInt max = words.entrySet().stream().mapToInt(Entry::getValue).max();

		Entry<Lexeme, Integer> entry = (Entry<Lexeme, Integer>) words.entrySet().toArray()[0];

		Integer maxValue = 0;
		Lexeme maxI = words.entrySet().stream().reduce(entry, (a, b) -> a.getValue() > b.getValue() ? a : b).getKey();
		System.out.println("Max word = " + words.get(maxI));
		System.out.println(maxI);

		Object[] array = words.entrySet().stream().sorted((a,b) -> a.getValue().compareTo(b.getValue())).toArray();
		for (int i = array.length - 1; i > array.length - 21; i--) {
			Entry<Lexeme, Integer> 	 object = (Entry<Lexeme, Integer>)array[i];
			System.out.println(object.getKey() + " " + object.getValue());
		}
	}
	
	protected String addText(String path) {
		String text = "";
		try {
			text = new Scanner(new File(path)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		if (text.length() == 0) {
			System.out.println("There was no text");
			return null;
		}
		return text;
	}

	protected void init(String path) {
		String text = addText(path);
		if (text == null)
			System.exit(0);
		text = clean(text);

		System.out.println("text.size() " + text.length());
		System.out.println("rawSentences.size() " + text.substring(0, 1000));

		String[] rawSentences = text.split("\\.");
		System.out.println("rawSentences.size() " + rawSentences.length);
		for (String rawSentence : rawSentences) {
			theText.add(new Sentence(rawSentence));
		}
		System.out.println("theText.size() " + theText.size());

		System.out.println("Lexeme.lexemPool.lexems.size() " + Lexeme.lexemPool.lexems.size());

		for (int i = 0; i < 10; i++)
			System.out.println(i + "  " + theText.get(i));

		for (int i = 0; i < 300; i++)
			System.out.println(i + "  " + Lexeme.lexemPool.lexems.get(i));
	}

	protected void init2(String path) {
		String text = addText(path);
		if (text == null)
			System.exit(0);
		text = clean(text);

		System.out.println("text.size() " + text.length());
		System.out.println("rawSentences.size() " + text.substring(0, 1000));

		String[] units = text.split(" ");
		System.out.println("units.size() " + units.length);
		Sentence sentence = new Sentence();
		theText.add(sentence);
		boolean CapitalWord = true;

		for (String unit : units) {
			

			if (unit.length() == 0)
				continue;
			if (CapitalWord)
				unit = unit.toLowerCase();
			Lexeme lexeme = Lexeme.lexemeFactory(unit);
			sentence.add(lexeme);
			if (!lexeme.mark)
				CapitalWord = false;
			if (lexeme.isEnding()) {// ending
				sentence = new Sentence();
				theText.add(sentence);
				CapitalWord = true;
			}
			/*
			 * regexes for splitting text into sentences (sentence-tokenizing)
			 * (?<=(?<!Mr)\.|\?|!|!!!)\s(?=[A-Z])
			 * (?<!\w\.\w.)(?<![A-Z][a-z]\.)(?<=\.|\?|!|!!!)\s
			 */
		}
		System.out.println("theText.size() " + theText.size());

		System.out.println("Lexeme.lexemPool.lexems.size() " + Lexeme.lexemPool.lexems.size());

//		System.out.println("Sentences");
//		for (int i = 0; i < 10; i++)
//			System.out.println(i + "  " + theText.get(i));
//
//		System.out.println("Words");
//		for (int i = 500; i < 800; i++)
//			System.out.println(i + "  " + Lexeme.lexemPool.lexems.get(i));
	}


/*
//	String[] units = text.split(" ");
////sentences.stream().forEach(a -> {a.stream().forEach(b -> System.out.print(b + " ")); System.out.println(". ");});
//
//Arrays.asList(units).stream().map(String::toLowerCase).distinct().sorted().forEach(lexicon::add);
//System.out.println("Words count " + lexicon.size());
//for (int i = 0; i < 500; i++) {
//	System.out.println(i+"  " +lexicon.get(i));
//}
/*
 *  string text
 *  split
 *  string[] sen
 *  for(sen)
 *  	new sentence
 *  	split
 *  	string[] lexems
 *  	for(lexems)
 *  		ordered lexem = lexicon(add new lexem)
 *  		sentence.add ordered lexem
 *  		
 *  		
 *  
 */
/*
String[] units_ = new String[units.length];
LexemPool lexemPoolBook = new LexemPool();
Outer:
for (int i = 0; i < units.length; i++) {	
	String string = units[i];
	units_[i] = lexemPoolBook.add(string).text;
//	System.out.println(string);

	
}

System.out.println(lexemPoolBook.lexems.size());
System.out.println("units");
for (int i = 100; i < 140; i++) {
System.out.print("  " +units[i]);
}
System.out.println("\nunits_");
for (int i = 100; i < 140; i++) {
System.out.print("  " +units_[i]);
}
String outText = "";
outText = Arrays.asList(units_).stream().reduce((a,b) -> {return a + " " + b;}).toString();
System.out.println("Result " + text.equals(outText));
//Outer:
//for (int i = 0; i < units.length; i++) {	
//	String string = units[i];
//	for (String lexem : lexicon) {
//		if(lexem.equals(string)){
//			units_[i] = lexem;
//			continue Outer;
//		}
//
//	}
//	System.out.println("error \"" + string + "\" " + i);
//	break Outer;
//}
System.out.println("\n" + lexicon.get(30).equals(","));
//lexicon.stream().map(() -> {}).fo
//System.out.println(lexicon.);
//text to lexems



//String[] rawSentences = text.split(".");



//sentences.stream().forEach(a -> {a.stream().forEach(b -> System.out.print(b + " ")); System.out.println(". ");});
*/


	public static String clean(String text) {
		/*
		 * abc.replaceAll("(([A-Za-z0-9])(\\.))" with...
		 * "&$0&$1&$2&$3" = ab&c.&c.&c&.
		 * "$2 $3" = abc .
		 * replaceAll("((?<=[A-Za-z0-9])(\\.))" with...
		 * "&$0&$1&$2" = abc&.&.&.
		 * "$1 $2" = abc. .
		 */
		//remove unknown characters
		text = text.replaceAll("[[^A-Za-z0-9]&&[^\\.?!,:;@#$ \"\'$]]", " ");
		//reveal punctuation after
		text = text.replaceAll("([\'A-Za-z0-9])([^A-Za-z0-9])", "$1 $2");
		//reveal punctuation before
		text = text.replaceAll("([^A-Za-z0-9])([A-Za-z0-9\'])", "$1 $2");
		
		//reveal quotes
		text = text.replaceAll("([\"])([\\S])", "$1 $2");
		text = text.replaceAll("([\\S])([\"])", "$1 $2");
		
//		System.out.println("Test " + "etc., a.".replaceAll(regex, replacement));
		//reveal "short" dots
		text = text.replaceAll("([\\.])([\\S])", "$1 $2").replaceAll(" +", " ");

		return text;
	}
}

