package mp2.ng.hw.pr2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexeme implements Comparable<Lexeme>{
	static LexemPool lexemPool;
	
	 static Pattern marks = Pattern.compile("[\\W]");
	 static Pattern endings = Pattern.compile("[\\.+\\?!]");

	 static Matcher marksMatcher = marks.matcher("");
	 static Matcher endingsMatcher = endings.matcher("");
	 

	 
	boolean mark;
	static{
		if (lexemPool == null) 
			lexemPool = new LexemPool();
	}
	
	String text;
	public Lexeme(String text) {
		this.text = text;
	}
	public static Lexeme lexemeFactory(String rawLexem) {
		if (rawLexem.equals(".,")) {
			System.out.println("Symbol .,");
		}
		if (rawLexem.equals("But")) {//rawLexem.toLowerCase())
			System.out.println("Symbol A");
		}
		return lexemPool.add(rawLexem);
	}

	public boolean isEnding() {
		if(!mark)	
			return false;

		endingsMatcher.reset(text);
		return endingsMatcher.matches();
	}
	
	@Override
	public int compareTo(Lexeme other) {
		return text.compareTo(other.text);
	}
	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public String toString() {
		return text;
	}
	
	
}
