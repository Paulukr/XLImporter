package mp2.ng.hw.pr2;
	
public class Lexeme implements Comparable<Lexeme>{
	static LexemPool lexemPool;
	static{
		if (lexemPool == null) 
			lexemPool = new LexemPool();
	}
	
	String text;
	public static Lexeme lexemeFactory(String rawLexem) {
		return lexemPool.add(rawLexem);
	}
	public Lexeme(String text) {
		text = this.text;
	}
	boolean mark;
	String content;
	
	@Override
	public int compareTo(Lexeme o) {
		return text.compareTo(o.text);
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
}
