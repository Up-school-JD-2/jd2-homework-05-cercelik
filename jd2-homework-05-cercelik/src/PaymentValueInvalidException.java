
public class PaymentValueInvalidException extends Exception {

	private final String text;

	public PaymentValueInvalidException(String message, String text) {
		super(message);
		this.text = text;

	}

	public String getText() {
		return text;
	}

}
