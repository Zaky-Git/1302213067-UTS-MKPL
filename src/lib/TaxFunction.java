package lib;

public class TaxFunction {
  
	private static final int BASE_DEDUCTION = 54000000;
	private static final int SPOUSE_DEDUCTION = 4500000;
	private static final int CHILD_DEDUCTION_PER_CHILD = 1500000;
	private static final double TAX_RATE = 0.05;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible,
			boolean isMarried, int numberOfChildren) {
		if (numberOfMonthsWorked > 12) {
			throw new IllegalArgumentException("Number of months worked exceeds 12 per year");
		}

		int baseIncome = calculateBaseIncome(monthlySalary, otherMonthlyIncome, numberOfMonthsWorked);
		int taxDeduction = calculateTaxDeduction(deductible, isMarried, numberOfChildren);

		int tax = calculateTaxAmount(baseIncome, taxDeduction);

		return Math.max(tax, 0);
	}

	private static int calculateBaseIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked) {
		return (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked;
	}

	private static int calculateTaxDeduction(int deductible, boolean isMarried, int numberOfChildren) {
		int taxDeduction = deductible + BASE_DEDUCTION;

		taxDeduction += Math.min(numberOfChildren, 3) * CHILD_DEDUCTION_PER_CHILD;

		if (isMarried) {
			taxDeduction += SPOUSE_DEDUCTION;
		}

		return taxDeduction;
	}

	private static int calculateTaxAmount(int baseIncome, int taxDeduction) {
		return (int) Math.round(TAX_RATE * (baseIncome - taxDeduction));
	}
}
