

public abstract class RPNOperator {
	RPNStack stack;
	public RPNOperator(RPNStack s) {
		stack = s;
	}
	void operate() {}
}

class RPNOperatorAdd extends RPNOperator {
	public RPNOperatorAdd(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem(num1 + num2));
	}
}

class RPNOperatorSubtract extends RPNOperator {
	public RPNOperatorSubtract(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem(num2 - num1));
	}
}

class RPNOperatorMultiply extends RPNOperator {
	public RPNOperatorMultiply(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem(num1 * num2));
	}
}

class RPNOperatorDivide extends RPNOperator {
	public RPNOperatorDivide(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem(num2 / num1));
	}
}

class RPNOperatorPower extends RPNOperator {
	public RPNOperatorPower(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		stack.push(new RPNStackItem((float)(Math.pow(num2, num1))));
	}
}

class RPNOperatorQuit extends RPNOperator {
	public RPNOperatorQuit(RPNStack s) {
		super(s);
	}
	void operate() {
		RPNMain.run = false;
	}
}

class RPNOperatorClear extends RPNOperator {
	public RPNOperatorClear(RPNStack s) {
		super(s);
	}
	void operate() {
		stack.clear();
		RPNMain.varMap.clear();
		System.out.println("Stack and variables reset!");
	}
}

class RPNOperatorFactorial extends RPNOperator {
	public RPNOperatorFactorial(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		float total = 1;
		if (a < 0 || a % 1 != 0) {
			stack.push(new RPNStackItem(a));
			System.out.println("Factorial can only take positive integers!");
			return;
		}
		for (int i = Math.round(a); i > 0; i--) {
			total *= i;
		}
		if (total == Float.POSITIVE_INFINITY) {
			System.out.println("Output overflow! Factorial output too large a number.");
		} else {
			stack.push(new RPNStackItem(total));
		}
	}
}

class RPNOperatorModulo extends RPNOperator {
	public RPNOperatorModulo(RPNStack s) {
		super(s);
	}
	void operate() {
		float a = stack.pop().toFloat();
		float b = stack.pop().toFloat();
		if (a < 0) {
			stack.push(new RPNStackItem(b));
			stack.push(new RPNStackItem(a));
			System.out.println("Modulo can only take positive numbers!");
			return;
		}
		stack.push(new RPNStackItem(b % a));
	}
}

class RPNOperatorGreater extends RPNOperator {
	public RPNOperatorGreater(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		
		if (num2 > num1) {
			stack.push(new RPNStackItem(1f));;
		} else {
			stack.push(new RPNStackItem(0f));
		}
	}
}

class RPNOperatorLess extends RPNOperator {
	public RPNOperatorLess(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		
		if (num2 < num1) {
			stack.push(new RPNStackItem(1f));;
		} else {
			stack.push(new RPNStackItem(0f));
		}
	}
}

class RPNOperatorEqual extends RPNOperator {
	public RPNOperatorEqual(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		
		if (num2 == num1) {
			stack.push(new RPNStackItem(1f));;
		} else {
			stack.push(new RPNStackItem(0f));
		}
	}
}

class RPNOperatorGreaterEqual extends RPNOperator {
	public RPNOperatorGreaterEqual(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		
		if (num2 >= num1) {
			stack.push(new RPNStackItem(1f));;
		} else {
			stack.push(new RPNStackItem(0f));
		}
	}
}

class RPNOperatorLessEqual extends RPNOperator {
	public RPNOperatorLessEqual(RPNStack s) {
		super(s);
	}
	void operate() {
		float num1 = stack.pop().toFloat();
		float num2 = stack.pop().toFloat();
		
		if (num2 <= num1) {
			stack.push(new RPNStackItem(1f));;
		} else {
			stack.push(new RPNStackItem(0f));
		}
	}
}

class RPNOperatorStore extends RPNOperator {
	public RPNOperatorStore(RPNStack s) {
		super(s);
	}
	void operate() {
		RPNStackItem var = stack.pop();
		RPNStackItem num1 = stack.pop();
		
		if (var.type.equals("float")) {
			System.out.println("Error, tried to store variable to non-variable.");
			return;
		} else if (num1.type.equals("var")) {
			System.out.println("Warning: stored value of variable to other variable, NOT a reference back to the original variable");
		} else {
			RPNMain.varMap.put(var.getVar(),  num1.toFloat());
		}
		
	}
}