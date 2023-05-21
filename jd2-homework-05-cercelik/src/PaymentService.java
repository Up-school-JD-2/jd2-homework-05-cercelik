import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PaymentService {

	private int count;

	public void checkPaymentValue(String paymentValue) throws PaymentValueInvalidException {
		if (!isValidPaymentValue(paymentValue)) {
			throw new PaymentValueInvalidException(
					"Ödeme tutarı geçersizdir.Ödeme tutarı virgül içeremez ve negatif değer olamaz.", paymentValue);
		}
	}

	private boolean isValidPaymentValue(String str) {
		if (str.contains(",")) {
			return false;
		}
		if (str.startsWith("-")) {
			return false;
		}

		return true;
	}

	// card number methods
	public void checkCardNumber(String cardNumber) throws CardNumberInvalidException {

		if (!isValidCardNumber(cardNumber) | !isDigit(cardNumber)) {
			throw new CardNumberInvalidException(
					"Kart numarası geçersizdir. Kart numarası 16 haneli olmalı ve rakamlardan oluşmalıdır.",
					cardNumber);
		}
	}

	private boolean isValidCardNumber(String str) {

		if (str.length() != 16) {
			return false;
		}

		return true;

	}

	private boolean isDigit(String str) {

		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	// cvv methods
	public void checkCvv(String cvv) throws CvvNumberInvalidException {

		if (!isValidCvv(cvv) | !isDigit(cvv)) {
			throw new CvvNumberInvalidException(
					"Cvv numarası geçersizdir. Cvv numarası 3 haneli olmalı ve rakamlardan oluşmalıdır.", cvv);
		}

	}

	private boolean isValidCvv(String str) {

		if (str.length() != 3) {
			return false;
		}
		return true;
	}

	public void checkCardExpiryDate(String creditCardExpiryDateString)
			throws ExpiryDateInvalidException, ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
		simpleDateFormat.setLenient(false);
		Date expiry = simpleDateFormat.parse(creditCardExpiryDateString);
		boolean expired = expiry.before(new Date());
		if (expired) {
			throw new ExpiryDateInvalidException("Kartın son geçerlilik tarihi dolmuştur. ",
					creditCardExpiryDateString);
		}

	}

	public void pay() throws SystemNotWorkingException {
		Random r = new Random();

		int randomNumber = r.nextInt(100) + 1;
		System.out.println("Random number değeri: " + randomNumber);

		if (randomNumber > 75 && count < 2) {
			count++;
			throw new SystemNotWorkingException("system not working", randomNumber);
		}

	}

}
