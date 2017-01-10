package mp2.ng.hw.hw4.t20;

enum FiguresType{
	I, O, T, S, Z, J, L
}

public class TetrisGame {
	public Figure makeFigureI(){
		return new FigureI();
	}
	public Figure getFigure(){
		FiguresType figureType = FiguresType.I;
		return getFigure(figureType);
	}
	public Figure getFigure(FiguresType figureType){
		switch (figureType) {
		case I:
			break;
		case J:
			break;
		case L:
			break;
		case O:
			break;
		case S:
			break;
		case T:
			break;
		case Z:
			break;
		default:
			break;

		}
		return new FigureI();
	}
}
