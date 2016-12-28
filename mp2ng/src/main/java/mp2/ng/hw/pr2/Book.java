package mp2.ng.hw.pr2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Book {
	List<Sentence> theText;
	List<String> lexicon = new ArrayList<>();
	List<List<Integer>> content = new ArrayList<>();
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
//		List<List<String>> rawSentences = new ArrayList<>();

		String[] units = text.split(" ");
//		sentences.stream().forEach(a -> {a.stream().forEach(b -> System.out.print(b + " ")); System.out.println(". ");});
		
		Arrays.asList(units).stream().map(String::toLowerCase).distinct().sorted().forEach(lexicon::add);
		System.out.println("Words count " + lexicon.size());
//		for (int i = 0; i < 500; i++) {
//			System.out.println(i+"  " +lexicon.get(i));
//		}
		
		String[] rawSentences = text.split(".");
		content.add(new ArrayList<>());

		for (String string : units) {
			if((".").equals(string)){
				content.add(new ArrayList<>());
				System.out.println("dsasd");
			}else{
				int i = Collections.binarySearch(lexicon, string);
				if(i<0)
					System.out.println("Lexicon error");
				content.get(content.size()-1).add(i);
			}
		}
//		sentences.stream().forEach(a -> {a.stream().forEach(b -> System.out.print(b + " ")); System.out.println(". ");});

		for (List sen : content) {
			for(Object w : sen)
				System.out.print(lexicon.get((Integer)w)+ " ");
			System.out.println();
		}
	}


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
