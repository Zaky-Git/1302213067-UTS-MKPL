package lib;

public class TaxFunction {

	private static final int BASE_DEDUCTION = 54000000;
	private static final int SPOUSE_DEDUCTION = 4500000;
	private static final int CHILD_DEDUCTION_PER_CHILD = 1500000;
	private static final double TAX_RATE = 0.05;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
			boolean isMarried, int numberOfChildren) {

		int tax = 0;

		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}

		if (isMarried) {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible
					- (54000000 + 4500000 + (numberOfChildren * 1500000))));
		} else {
			tax = (int) Math.round(
					0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
		}

		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}

	}

}
