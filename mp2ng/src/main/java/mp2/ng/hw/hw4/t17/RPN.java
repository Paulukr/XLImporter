package mp2.ng.hw.hw4.t17;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
 
public class RPN{
public   static String infixToPostfix(String infix) {
        final String ops = "-+/*^";
        StringBuilder sb = new StringBuilder();
        Deque<Integer> s = new ArrayDeque<>();
 
        for (String token : infix.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);
 
            // check for operator
            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);
 
                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(idx);
                }
            } 
            else if (c == '(') {
                s.push(-2); // -2 stands for '('
            } 
            else if (c == ')') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop())).append(' ');
        return sb.toString();
    }
	public static double evalRPN(String expr){

		String cleanExpr = cleanExpr(expr);
		LinkedList<Double> stack = new LinkedList<Double>();
	
		for(String token:cleanExpr.split("\\s")){
			Double tokenNum = null;
			try{
				tokenNum = Double.parseDouble(token);
			}catch(NumberFormatException e){}
			if(tokenNum != null){
				stack.push(Double.parseDouble(token+""));
			}else if(token.equals("*")){
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(firstOperand * secondOperand);
			}else if(token.equals("/")){
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(firstOperand / secondOperand);
			}else if(token.equals("-")){
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(firstOperand - secondOperand);
			}else if(token.equals("+")){
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(firstOperand + secondOperand);
			}else if(token.equals("^")){
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				stack.push(Math.pow(firstOperand, secondOperand));
			}else{//just in case
				System.out.println("Error");
			}
		}
		return stack.pop();
	}
 
	private static String cleanExpr(String expr){
		//remove all non-operators, non-whitespace, and non digit chars
		return expr.replaceAll("[^\\^\\*\\+\\-\\d/\\s]", "");
	}
	public static double eval(String expr){
		return evalRPN(infixToPostfix(expr));
	}
	public static void main(String[] args){
		System.out.println(eval("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"));
	}
}