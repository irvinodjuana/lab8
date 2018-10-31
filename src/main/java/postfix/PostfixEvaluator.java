package postfix;

import java.util.Stack;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains a method to evaluate an arithmetic expression
 * that is in Postfix notation (or Reverse Polish Notation).
 * See <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia</a>
 * for details on the notation.
 *
 */
public class PostfixEvaluator {
	
	private String arithmeticExpr;
	
	/**
	 * This is the only constructor for this class.
	 * It takes a string that represents an arithmetic expression
	 * as input argument.
	 * 
	 * @param expr is a string that represents an arithmetic expression 
	 * <strong>in Postfix notation</strong>.
	 */
	public PostfixEvaluator( String expr ) {
		arithmeticExpr = expr;
	}
	
	/**
	 * This method evaluates the arithmetic expression that 
	 * was passed as a string to the constructor for this class.
	 * 
	 * @return the value of the arithmetic expression
	 * @throws MalformedExpressionException if the provided expression is not
	 * 	a valid expression in Postfix notation
	 */
	double eval( ) throws MalformedExpressionException {
		// TODO: Implement this method.
		// The code provided here is for illustration only, and
		// can be deleted when you write your implementation.

		// Using a stack makes it very simple to evaluate the
		// arithmetic expression.
		// See http://docs.oracle.com/javase/8/docs/api/java/util/Stack.html

		// Use the Scanner to get the elements (tokens) in the
		// arithmetic expression.
		Stack numberStack = new Stack();
		Scanner scanner = new Scanner(arithmeticExpr);
		Token currToken;

		do {
			currToken = scanner.getToken();
			if (currToken.isDouble()) {
				numberStack.push(currToken.getValue());
			} else if (currToken.isVariable()) {
				String operation = currToken.getName();
				double op1, op2, result = 0;
				if (numberStack.size() < 2) {
					throw new MalformedExpressionException();
				}
				switch (operation) {
					case "*":
						op2 = (double) numberStack.pop();
						op1 = (double) numberStack.pop();
						result = op1 * op2;
						break;
					case "/":
						op2 = (double) numberStack.pop();
						op1 = (double) numberStack.pop();
						result = op1 / op2;
						break;
					case "+":
						op2 = (double) numberStack.pop();
						op1 = (double) numberStack.pop();
						result = op1 + op2;
						break;
					case "-":
						op2 = (double) numberStack.pop();
						op1 = (double) numberStack.pop();
						result = op1 - op2;
						break;
				}

				numberStack.push(result);
			} else {
				throw new MalformedExpressionException();
			}

			scanner.eatToken();

		} while (!scanner.isEmpty());

		return (double) numberStack.peek();
	}


}


