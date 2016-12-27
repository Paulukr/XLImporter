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
	List<Sentence> text;
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
		System.out.println(text.length());
		//clean
		text = text.replaceAll("[[^A-Za-z0-9]&&[^\\.?!,:;@#$ \"\'$]]", " ").replaceAll(" +", " ");
		System.out.println(text.length());
		
		//reveal punctuation
		text = text.replaceAll("([A-Za-z0-9])([\\.?!,:;@#$ \"$])", "$1 $2");
		//reveal punctuation
		text = text.replaceAll("([\\.?!,:;@#$ \"$])([A-Za-z0-9])", "$1 $2").replaceAll(" +", " ");
//		for (int i = 0; i < 30; i++) {
//			System.out.println(text.substring(i*30, (i+1)*30));
//		}
		List<List<String>> sentences = new ArrayList<>();
		String dot = ".";//intern dot
		String[] units = text.split(" ");
		List<String> sortedUnits = new ArrayList<>();
		for (String string : units) {
			sortedUnits.add(string.toLowerCase().intern());
		}

	
		sentences.add(new ArrayList<>());
		for (int i = 0; i < units.length; i++) {
			units[i] = units[i].intern();
			if(units[i] == dot){
				sentences.add(new ArrayList<>());
			}else {
				sentences.get(sentences.size()-1).add(units[i]);
			}
			if(sentences.size() == 20)
				break;
		}
		System.out.println(sentences.size());
		TreeSet<String> u = new TreeSet<>();
		
		sentences.stream().forEach(a -> {a.stream().forEach(b -> System.out.print(b + " ")); System.out.println(". ");});
		List<String> words = new ArrayList<>();
		Arrays.asList(units).parallelStream().distinct().sorted().forEach(words::add);
		System.out.println("Words count " + words.size());

//		String[] words = (String[]) Arrays.asList(units).parallelStream().distinct().sorted().toArray();
		// Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
//		System.out.println("Words count " + words.length);
		Arrays.sort(units);

//		sortedUnits.add(units[0]);
//		for (int i = 1; i < units.length; i++) {
//			if(units[i] == units[i-1])
//				continue;
//			sortedUnits.add(units[i]);
//			System.out.println(sortedUnits.get(i));
//			System.out.println(units[i].hashCode() + " " + units[i].hashCode());
//		}
		Collections.sort(sortedUnits);
//		for (int i = 1; i < 500; i++) {
//			System.out.println(i + sortedUnits.get(i));
//		}
		System.out.println(sortedUnits.size());
		System.out.println(sortedUnits.get(1));
		System.out.println(sortedUnits.get(1) + " " + sortedUnits.get(2) + " " + (sortedUnits.get(1)==sortedUnits.get(2)));
		
		/*
		 * abc.replaceAll("(([A-Za-z0-9])(\\.))" with...
		 * "&$0&$1&$2&$3" = ab&c.&c.&c&.
		 * "$2 $3" = abc .
		 * replaceAll("((?<=[A-Za-z0-9])(\\.))" with...
		 * "&$0&$1&$2" = abc&.&.&.
		 * "$1 $2" = abc. .
		 */

//
//		//intern
//		for (int i = 0; i < sentences.length; i++) {
//			sentences[i] = sentences[i].intern();
//			
//		}
////		String[] sentences = text.split("\\.");
//		//intern
//		for (int i = 0; i < sentences.length; i++) {
//			sentences[i] = sentences[i].intern();
			
//		}
		
		
	}
}
