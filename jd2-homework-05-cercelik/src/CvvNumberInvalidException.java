
public class CvvNumberInvalidException extends Exception {
	private final String text;

	public CvvNumberInvalidException(String message, String text) {
		super(message);
		this.text = text;

	}

	public String getText() {
		return text;
	}
}
