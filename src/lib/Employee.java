package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;

	private boolean isForeigner;
	private boolean isMale;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private Spouse spouse;
	private Child child;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.isMale = gender;
		child = new Child();
	}

	private static final int GRADE_1_SALARY = 3_000_000;
	private static final int GRADE_2_SALARY = 5_000_000;
	private static final int GRADE_3_SALARY = 7_000_000;
	private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;

	public void setMonthlySalary(int grade) {
		int[] gradeSalaries = { GRADE_1_SALARY, GRADE_2_SALARY, GRADE_3_SALARY };
		monthlySalary = gradeSalaries[Math.min(grade, gradeSalaries.length) - 1];

		if (isForeigner) {
			monthlySalary *= FOREIGNER_SALARY_MULTIPLIER;
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouse = new Spouse(spouseName, spouseIdNumber);
	}

	public void addChild(String childName, String childIdNumber) {
		child.addChild(childName, childIdNumber);
	}

	public int getAnnualIncomeTax() {
		LocalDate date = LocalDate.now();
		int monthWorkingInYear = date.getYear() == yearJoined ? date.getMonthValue() - monthJoined : 12;

		boolean hasSpouseIdNumber = spouse != null && !spouse.getSpouseIdNumber().isEmpty();
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				!hasSpouseIdNumber, child.getChildIdNumbers().size());
	}
}
