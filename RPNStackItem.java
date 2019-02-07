

public class RPNStackItem {
	float data;
	String var;
	String type;
	public RPNStackItem(float s) {
		data = s;
		type = "float";
	}
	
	public RPNStackItem(String s) {
		var = s;
		type = "var";
	}
	
	public float toFloat() {return data;}
	
	public String getVar() {return var;}
}
