
public class DateTimeParseException extends Exception {
	private final String text;
	public DateTimeParseException(String message, String text) {
		super(message);
		this.text = text;

	}

	public String getText() {
		return text;
	}

}
