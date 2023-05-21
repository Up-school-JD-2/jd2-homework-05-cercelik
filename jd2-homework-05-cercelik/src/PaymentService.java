import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PaymentService {

	private boolean isValidCardExpiryDate = false;
	private int count;

	/**
	 * @return the isValidCardExpiryDate
	 */
	public boolean isValidCardExpiryDate() {
		return isValidCardExpiryDate;
	}

	// payment value methods
	public void checkPaymentValue(String paymentValue) {

		try {

			if (!isValidPaymentValue(paymentValue)) {

				throw new PaymentValueInvalidException(
						"Ödeme tutarı geçersizdir.Ödeme tutarı virgül içeremez ve negatif değer olamaz.", paymentValue);
			}

		}
		catch (PaymentValueInvalidException e) {
			System.out.printf("%s hatalı alan: %s\n", e.getMessage(), e.getText());

		}

	}

	public boolean isValidPaymentValue(String str) {
		if (str.contains(",")) {
			return false;
		}
		if (str.startsWith("-")) {
			return false;
		}

		return true;
	}

	// card number methods
	public void checkCardNumber(String cardNumber) {

		try {

			if (!isValidCardNumber(cardNumber) | !isDigit(cardNumber)) {
				throw new CardNumberInvalidException(
						"Kart numarası geçersizdir. Kart numarası 16 haneli olmalı ve rakamlardan oluşmalıdır.",
						cardNumber);
			}

		} catch (CardNumberInvalidException e) {
			System.out.printf("%s hatalı alan: %s", e.getMessage(), e.getText());
			System.out.println();
		}
	}

	public boolean isValidCardNumber(String str) {

		if (str.length() != 16) {
			return false;
		}

		return true;

	}

	public boolean isDigit(String str) {

		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	// cvv methods
	public void checkCvv(String cvv) {
		try {

			if (!isValidCvv(cvv) | !isDigit(cvv)) {
				throw new CvvNumberInvalidException(
						"Cvv numarası geçersizdir. Cvv numarası 3 haneli olmalı ve rakamlardan oluşmalıdır.", cvv);
			}
		} catch (CvvNumberInvalidException e) {
			System.out.printf("%s hatalı alan: %s", e.getMessage(), e.getText());
			System.out.println();
		}
	}

	public boolean isValidCvv(String str) {

		if (str.length() != 3) {
			return false;
		}
		return true;
	}

	public void checkCardExpiryDate(String creditCardExpiryDateString) {

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
			simpleDateFormat.setLenient(false);
			Date expiry = simpleDateFormat.parse(creditCardExpiryDateString);
			boolean expired = expiry.before(new Date());
			if (expired) {
				throw new ExpiryDateInvalidException("Kartın son geçerlilik tarihi dolmuştur. ",
						creditCardExpiryDateString);
			}

			else {
				isValidCardExpiryDate = true;
			}

		} catch (ParseException dtpe) {

			System.out.println("Kart son kullanma tarihi hatalıdır:(MM/YY) " + creditCardExpiryDateString);
		}

		catch (ExpiryDateInvalidException e) {

			System.out.printf("%s hatalı alan: %s\n", e.getMessage(), e.getText());
		}

	}

	public void pay() throws SystemNotWorkingException {
		Random r = new Random();

		int randomNumber = r.nextInt(100) + 1;
		System.out.println(randomNumber);

		if (randomNumber > 75 && count < 2) {
			count++;
			throw new SystemNotWorkingException("system not working", randomNumber);
		}

	}

	public void checkPayment() {
		try {

			pay();
		}

		catch (SystemNotWorkingException s) {

			System.out.printf("%s hatalı alan: %s", s.getMessage(), s.getText());
			System.out.println();

			try {
				pay();
			} catch (SystemNotWorkingException e) {
				System.out.printf("%s hatalı alan: %s", s.getMessage(), s.getText());
			}

		}
	}

}
