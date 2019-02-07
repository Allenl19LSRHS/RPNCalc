
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.lang.Character;

public class RPNMain {
	static Scanner scan = new Scanner(System.in); 
	
	static Map<String, RPNOperator> opMap = new HashMap<String, RPNOperator>();
	static Map<String, Float> varMap = new HashMap<String, Float>();
	static boolean run = true;

	public static void main(String[] args) {
		RPNStack myStack = new RPNStack();
		
		// Basic Math
		opMap.put("+", new RPNOperatorAdd(myStack));
		opMap.put("-", new RPNOperatorSubtract(myStack));
		opMap.put("*", new RPNOperatorMultiply(myStack));
		opMap.put("/", new RPNOperatorDivide(myStack));
		opMap.put("^", new RPNOperatorPower(myStack));
		
		// 2 special commands
		opMap.put("q", new RPNOperatorQuit(myStack));
		opMap.put("c", new RPNOperatorClear(myStack));
		
		// Other
		opMap.put("!", new RPNOperatorFactorial(myStack));
		opMap.put("%", new RPNOperatorModulo(myStack));
		
		// Boolean Operators
		opMap.put(">", new RPNOperatorGreater(myStack));
		opMap.put("<", new RPNOperatorLess(myStack));
		opMap.put("==", new RPNOperatorEqual(myStack));
		opMap.put(">=", new RPNOperatorGreaterEqual(myStack));
		opMap.put("<=", new RPNOperatorLessEqual(myStack));
		
		// variable store
		opMap.put("->", new RPNOperatorStore(myStack));
		
		String input = "";
		String[] tokens;
		do {
			input = scan.nextLine();
			tokens = input.split(" ");
			for (String s : tokens) {
				if (opMap.containsKey(s)) {
					try {
						opMap.get(s).operate();
					} catch (Exception e) {
						System.out.println("Error! Most likely not enough numbers were inputted.");
					}
				} else if (s.equals("")) {
					System.out.println("Please do not start your input with a space.");
				} else if (Character.isLetter(s.charAt(0))) {
					if (varMap.containsKey(s)) {
						myStack.push(new RPNStackItem(varMap.get(s)));
					} else {
						myStack.push(new RPNStackItem(s));
					}
				} else {
					try {
						myStack.push(new RPNStackItem(Float.parseFloat(s)));
					} catch (Exception e) {
						System.out.println("Error! You probably input an invalid operator.");
					}
				}
			}
			
			myStack.printStack();
		} while (run);
		
		//myStack.push(new RPNStackItem("a"));
		//myStack.push(new RPNStackItem("b"));
		//myStack.push(new RPNStackItem("c"));
		//System.out.println(myStack.pop());
		//myStack.push(new RPNStackItem("d"));
		//myStack.printStack();
	}

}
