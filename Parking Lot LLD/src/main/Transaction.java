package main;

public class Transaction {
	private final Double id;
    private final PaymentMode mode;

    public Transaction(PaymentMode mode) {
        this.id = Math.random();
        this.mode = mode;
    }
}
