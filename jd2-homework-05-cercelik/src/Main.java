import java.text.ParseException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaymentService paymentService = new PaymentService();
		Scanner s = new Scanner(System.in);
		String paymentValue, cardNumber, cvv;

		boolean isInvalidPaymentValue = true;
		boolean isInvalidCardNumber = true;
		boolean isInvalidCvv = true;
		boolean isInvalidExpiryDate = true;

		do {
			System.out.print("Ödeme tutarı: ");
			paymentValue = s.nextLine();
			try {
				paymentService.checkPaymentValue(paymentValue);
				isInvalidPaymentValue = false;
			} catch (PaymentValueInvalidException e) {
				System.out.printf("%s hatalı alan: %s\n", e.getMessage(), e.getText());
			}
		} while (isInvalidPaymentValue);

		do {
			System.out.println("Kart numarası: ");
			cardNumber = s.nextLine();
			try {
				paymentService.checkCardNumber(cardNumber);
				isInvalidCardNumber = false;
			}

			catch (CardNumberInvalidException e) {
				System.out.printf("%s hatalı alan: %s", e.getMessage(), e.getText());
				System.out.println();
			}

		} while (isInvalidCardNumber);

		do {
			System.out.println("Güvenlik numarası: ");
			cvv = s.nextLine();
			try {
				paymentService.checkCvv(cvv);
				isInvalidCvv = false;
			}

			catch (CvvNumberInvalidException e) {
				System.out.printf("%s hatalı alan: %s", e.getMessage(), e.getText());
				System.out.println();
			}
		} while (isInvalidCvv);

		do {
			try {
				System.out.println("Kart geçerlilik tarihi: ");
				String creditCardExpiryDateString = s.nextLine();
				try {
					paymentService.checkCardExpiryDate(creditCardExpiryDateString);
					isInvalidExpiryDate = false;
				} catch (ParseException e) {
					System.out.println("Hatalı alan: " + creditCardExpiryDateString);
				}
				
			}
			
			catch (ExpiryDateInvalidException e) {
				System.out.printf("%s hatalı alan: %s", e.getMessage(), e.getText());
				System.out.println();
			}
			
			

		} while (isInvalidExpiryDate);

		try {
			paymentService.pay();
		}

		catch (SystemNotWorkingException e) {
			System.out.printf("%s hatalı alan: %s", e.getMessage(), e.getText());
			System.out.println();

			try {
				paymentService.pay();
			} catch (SystemNotWorkingException sw) {
				System.out.printf("%s hatalı alan: %s", sw.getMessage(), sw.getText());
			}
		}

	}

}
