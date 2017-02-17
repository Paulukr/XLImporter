package mp2.ng.hw.pr2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

public class LexemPool {
	List<Lexeme> lexems = new ArrayList<>();
	Lexeme add(String in){
		if (in.equals("Drink")) {
			System.out.println(in);
			
		}
		Lexeme unknownLexeme =  new Lexeme(in);
		if (lexems.isEmpty()) {
			synchronized (Lexeme.marksMatcher) {
				Lexeme.marksMatcher.reset(in);
				unknownLexeme.mark = Lexeme.marksMatcher.matches();
			}
			lexems.add(unknownLexeme);
			return lexems.get(0);
		}
		
		int inPosition;
		inPosition = Collections.binarySearch(lexems, unknownLexeme);
		if(inPosition < 0){
			inPosition = -1 - inPosition;
			synchronized (Lexeme.marksMatcher) {
				Lexeme.marksMatcher.reset(in);
				unknownLexeme.mark = Lexeme.marksMatcher.matches();
			}
			lexems.add(inPosition, unknownLexeme);
		}

		return lexems.get(inPosition);
	}
}

