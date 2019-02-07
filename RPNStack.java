
import java.util.ArrayList;

public class RPNStack {
	public ArrayList<RPNStackItem> stack = new ArrayList<RPNStackItem>();

	public RPNStackItem push(RPNStackItem item) {
		stack.add(0, item);
		return item;
	}
	
	public RPNStackItem pop() {
		return stack.remove(0);
	}
	
	public void printStack() {
		System.out.println("Stack:");
		for (RPNStackItem i : stack) {
			System.out.println(i.toFloat());
			
			// This might or might not do anything, it might not actually push RPNStackItems with type var to the stack
			if (i.type.equals("var")) {
				System.out.print(" (" + i.getVar() + ")");
			}
		}
	}
	
	public void clear() {
		stack.clear();
	}
}
