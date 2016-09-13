package javaCoding.StringArray;

import java.io.IOException;
import java.util.Stack;

public class EvaluateReversePolishNotation {
	public static void main(String[] args) throws IOException {
		String[] tokens = new String[] { "2", "1", "+", "3", "*" };
		System.out.println(evalRPN(tokens));
	}

	public static int evalRPN(String[] tokens) {
		int returnValue = 0;
		int tempRetValue = 0;
		String operators = "+-*/";

		Stack<String> stack = new Stack<String>();

		for (String t : tokens) {
			if (!operators.contains(t)) 
			{
				// push to stack if it is a number
				stack.push(t);
			} 
			else 
			{
				// pop numbers from stack if it is an operator
				int a = Integer.valueOf(stack.pop());
				int b = Integer.valueOf(stack.pop());
				switch (t) {
				case "+":
					tempRetValue = a + b;
					stack.push(String.valueOf(tempRetValue));
					System.out.println(a+"+"+b+"="+tempRetValue);
					break;
				case "-":
					tempRetValue = b - a;
					stack.push(String.valueOf(tempRetValue));
					System.out.println(b+"-"+ a +"="+tempRetValue);
					break;
				case "*":
					tempRetValue = a * b;
					stack.push(String.valueOf(tempRetValue));
					System.out.println(a+"*"+b+"="+tempRetValue);
					break;
				case "/":
					tempRetValue = b / a;
					stack.push(String.valueOf(tempRetValue));
					System.out.println(b+"/"+a+"="+tempRetValue);
					break;
				}
			}
		}

		returnValue = Integer.valueOf(stack.pop());

		return returnValue;
	}
}
