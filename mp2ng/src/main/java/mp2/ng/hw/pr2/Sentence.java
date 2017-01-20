package mp2.ng.hw.pr2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sentence {
	List<Lexeme> lexemas = new ArrayList<>();
	static int counter = 0;
	public Sentence(String rawSentence) {
		init(rawSentence);
	}
	public Sentence(String[] rawSentence) {
		init(rawSentence);
	}
	public void init(String rawSentence) {
		counter++;
//		if(counter < 100) System.out.println(rawSentence);
		rawSentence = rawSentence.replaceFirst("^ ", "");
		String[] rawLexems = rawSentence.split(" ");

//		System.out.println(rawLexems[0]);
//		if(counter < 100){
//			System.out.println(rawSentence);
//			System.out.println(Arrays.deepToString(rawLexems));
//		}
		init(rawLexems);
	}
	public void init(String[] rawLexems) {
		if (rawLexems.length != 0) {
			rawLexems[0] = rawLexems[0].toLowerCase();
		}
		if(counter < 100) System.out.println(Arrays.deepToString(rawLexems));
		for (String rawLexem : rawLexems) {
			
			if ("".equals(rawLexem))
				continue;
			if (!rawLexem.toLowerCase().equals(rawLexem)&&counter < 100) {
				System.out.println(rawLexem);
				
			}
			lexemas.add(Lexeme.lexemeFactory(rawLexem));
		}
	}
	@Override
	public String toString() {
		if ((lexemas.isEmpty())||(lexemas.get(1).text.length() == 0)) {
			return " Empty!";
		}
		if (lexemas.size() == 1) {
			return " One! <" + lexemas.get(1) + ">";
		}

		String result = lexemas.stream().map(Lexeme::toString).reduce("", (a,b) -> a + " " + b);
		result = result.substring(0, 1).toUpperCase() + result.substring(1) + ".";

		return result;
	}

}
//public static void main(String[] args) {
//	// TODO Auto-generated method stub
//
//}