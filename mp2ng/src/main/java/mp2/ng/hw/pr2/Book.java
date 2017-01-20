package mp2.ng.hw.pr2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Book {
	List<Sentence> theText = new ArrayList<>();
	List<String> lexicon = new ArrayList<>();
	public static void main(String[] args) {
		String path = "E:\\Android\\ngdwp\\ngGitHome\\mp2ng\\src\\main\\java\\mp2\\ng\\hw\\pr2\\data\\Galaxy.txt";
		String path2 = "E:\\Galaxy.txt";
		
		String set = "E:\\Galax232y.t     xt   ?!,:;@#$ \"\'";
		String r = set.replaceAll("[[^A-Za-z0-9]&&[^\\.?!,:;@#$ \"\']]", "*").replaceAll(" +", " ");
		
		System.out.println(r);
		
		Book book = new Book();
		book.init(path);

	}
	protected String addText(String path){
		String text = "";
		try {
			text = new Scanner(new File(path)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		if(text.length() == 0){
			System.out.println("There was no text");
			return null;
		}
		return text;
	}
	protected void init(String path){
		String text = addText(path);
		if(text == null)
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
		
		
		
		
		

//		String[] units = text.split(" ");
////		sentences.stream().forEach(a -> {a.stream().forEach(b -> System.out.print(b + " ")); System.out.println(". ");});
//		
//		Arrays.asList(units).stream().map(String::toLowerCase).distinct().sorted().forEach(lexicon::add);
//		System.out.println("Words count " + lexicon.size());
//		for (int i = 0; i < 500; i++) {
//			System.out.println(i+"  " +lexicon.get(i));
//		}
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
//		String[] units_ = new String[units.length];
//		LexemPool lexemPoolBook = new LexemPool();
//		Outer:
//		for (int i = 0; i < units.length; i++) {	
//			String string = units[i];
//			units_[i] = lexemPoolBook.add(string).text;
////			System.out.println(string);
//
//			
//		}
//		
//		System.out.println(lexemPoolBook.lexems.size());
//		System.out.println("units");
//		for (int i = 100; i < 140; i++) {
//		System.out.print("  " +units[i]);
//	}
//		System.out.println("\nunits_");
//		for (int i = 100; i < 140; i++) {
//		System.out.print("  " +units_[i]);
//	}
//		String outText = "";
//		outText = Arrays.asList(units_).stream().reduce((a,b) -> {return a + " " + b;}).toString();
//		System.out.println("Result " + text.equals(outText));
////		Outer:
////		for (int i = 0; i < units.length; i++) {	
////			String string = units[i];
////			for (String lexem : lexicon) {
////				if(lexem.equals(string)){
////					units_[i] = lexem;
////					continue Outer;
////				}
////
////			}
////			System.out.println("error \"" + string + "\" " + i);
////			break Outer;
////		}
//		System.out.println("\n" + lexicon.get(30).equals(","));
////		lexicon.stream().map(() -> {}).fo
////		System.out.println(lexicon.);
//		//text to lexems
//		
//		
//		
////		String[] rawSentences = text.split(".");
//
//
//
////		sentences.stream().forEach(a -> {a.stream().forEach(b -> System.out.print(b + " ")); System.out.println(". ");});





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
		text = text.replaceAll("[[^A-Za-z0-9]&&[^\\.?!,:;@#$ \"\'$]]", " ").replaceAll(" +", " ");
		//reveal punctuation after
		text = text.replaceAll("([\'A-Za-z0-9])([^A-Za-z0-9])", "$1 $2");
		//reveal punctuation before
		text = text.replaceAll("([^A-Za-z0-9])([A-Za-z0-9\'])", "$1 $2").replaceAll(" +", " ");
		return text;
	}
}
