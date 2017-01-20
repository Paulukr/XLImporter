package mp2.ng.hw.pr2;
	
public class Lexeme implements Comparable<Lexeme>{
	static LexemPool lexemPool;
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
		return lexemPool.add(rawLexem);
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
	public String toString() {
		return text;
	}
	
	
}
