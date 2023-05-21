
public class CardNumberInvalidException extends Exception {

	private final String text;

	public CardNumberInvalidException(String message, String text) {
		super(message);
		this.text = text;

	}

	public String getText() {
		return text;
	}
}
