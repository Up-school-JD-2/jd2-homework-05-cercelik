
public class ExpiryDateInvalidException extends Exception {
	private final String text;

	public ExpiryDateInvalidException(String message, String text) {
		super(message);
		this.text = text;

	}

	public String getText() {
		return text;
	}
}
