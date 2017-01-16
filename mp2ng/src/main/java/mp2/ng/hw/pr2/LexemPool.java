package mp2.ng.hw.pr2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LexemPool {
	List<Lexeme> lexems = new ArrayList<>();
	Lexeme add(String in){
		int inPosition;
		inPosition = Collections.binarySearch(lexems, in);
		if(inPosition < 0){
			inPosition = -1 - inPosition;
			lexems.add(inPosition, new Lexeme(in));
		}

		return lexems.get(inPosition);
	}
}

