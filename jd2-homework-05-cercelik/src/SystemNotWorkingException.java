
public class SystemNotWorkingException extends Exception {
	private final int text;

	public SystemNotWorkingException(String message, int text) {
		super(message);
		this.text = text;

	}

	public int getText() {
		return text;
	}
	

	
}
