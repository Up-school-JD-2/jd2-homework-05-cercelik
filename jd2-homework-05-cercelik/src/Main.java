import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaymentService paymentService = new PaymentService();
		Scanner s = new Scanner(System.in);
		String paymentValue, cardNumber, cvv;

		do {
			
			System.out.print("Ödeme tutarı: ");
			paymentValue = s.nextLine();
			paymentService.checkPaymentValue(paymentValue);
		} while (!paymentService.isValidPaymentValue(paymentValue));

		do {
			System.out.println("Kart numarası: ");
			cardNumber = s.nextLine();
			paymentService.checkCardNumber(cardNumber);
		} while (!paymentService.isValidCardNumber(cardNumber) | !paymentService.isDigit(cardNumber));

		do {
			System.out.println("Güvenlik numarası: ");
			cvv = s.nextLine();
			paymentService.checkCvv(cvv);
		} while (!paymentService.isValidCvv(cvv) | !paymentService.isDigit(cvv));

		do {
			System.out.println("Kart geçerlilik tarihi: ");
			String creditCardExpiryDateString = s.nextLine();
			paymentService.checkCardExpiryDate(creditCardExpiryDateString);

		} while (!paymentService.isValidCardExpiryDate());

		paymentService.checkPayment();
	}

}
