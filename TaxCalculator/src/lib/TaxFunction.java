package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	 public static int calculateTax(TaxData parameters) {
		int taxableIncome = calculateTaxableIncome(parameters);
		int tax = (int) Math.round(0.05 * taxableIncome);
		return Math.max(tax, 0);
	}
	
	private static int calculateTaxableIncome(TaxData parameters){
		int totalMonthlyIncome = (parameters.getMonthlySalary() + parameters.getOtherMonthlyIncome()) * parameters.getNumberOfMonthWorking();
		int nonTaxableIncome = calculateNonTaxableIncome(parameters.isMarried(), parameters.getNumberOfChildren());
		return totalMonthlyIncome - parameters.getDeductible() - nonTaxableIncome;

	}
	
	private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
		int nonTaxableIncome = 54000000;
		if (isMarried) {
			nonTaxableIncome += 4500000;
		}
		nonTaxableIncome += Math.min(numberOfChildren, 3) * 1500000;
		return nonTaxableIncome;
	}
	
	
}
