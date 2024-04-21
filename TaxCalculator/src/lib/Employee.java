package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
    private PersonalInfo personalInfo; 
    private TaxData taxData; 
	
	private int monthWorkingInYear;
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    private List<String> childNames;
    private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender, TaxData taxData) {
        this.employeeId = employeeId;
        this.personalInfo = new PersonalInfo(firstName, lastName, idNumber, address, yearJoined, monthJoined, dayJoined, isForeigner, gender);
        this.taxData = taxData;
        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	 public String getFirstName() {
        return personalInfo.getFirstName();
    }

	public String getLastName() {
        return personalInfo.getLastName();
    }

	public void setMonthlySalary(int grade) {	
		int baseSalary;

    switch (grade) {
        case 1:
            baseSalary = 3000000;
            break;
        case 2:
            baseSalary = 5000000;
            break;
        case 3:
            baseSalary = 7000000;
            break;
        default:
            baseSalary = 0;
            break;
    }

    if (personalInfo.isForeigner()) {
        baseSalary *= 1.5;
    }

    monthlySalary = baseSalary;
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.personalInfo.setSpouseName(spouseName);
        this.personalInfo.setSpouseIdNumber(spouseIdNumber);
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == personalInfo.getYearJoined()) {
			monthWorkingInYear = date.getMonthValue() - personalInfo.getMonthJoined();
		}else {
			monthWorkingInYear = 12;
		}
		
        return TaxFunction.calculateTax(taxData);
	}
}
