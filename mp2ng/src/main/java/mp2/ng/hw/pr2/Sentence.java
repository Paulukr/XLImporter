package mp2.ng.hw.pr2;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
	List<Lexeme> lexemas = new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void init(String rawSentence) {
		String[] rawLexems = rawSentence.split(" ");
		init(rawLexems);
	}
	public void init(String[] rawLexems) {
		
		for (String rawLexem : rawLexems) {
//			String orderedLexem = "";
//			orderedLexem = lexemPool1.add(rawLexem);
			Lexeme orderedLexem = new Lexeme(rawLexem);
			lexemas.add(orderedLexem);
		}
	}
}
