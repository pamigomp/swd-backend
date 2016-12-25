package mixprob;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;

public class InputData {

	private int age;
	private char gender;
	private PhysicalActivity physicalActivity;
	private ArrayList<ObjectiveFunction> objectiveFunctions;
	private double dailyDemandVitaminA;
	private double dailyDemandVitaminD;
	private double dailyDemandVitaminE;
	private double dailyDemandVitaminK;
	private double dailyDemandVitaminB1;
	private double dailyDemandVitaminB2;
	private double dailyDemandVitaminB6;
	private double dailyDemandVitaminB12;
	private double dailyDemandVitaminC;
	private double dailyDemandVitaminPP;
	private double maxDailyDemandVitaminA;
	private double maxDailyDemandVitaminD;
	private double maxDailyDemandVitaminE;
	private double maxDailyDemandVitaminB6;
	private double maxDailyDemandVitaminC;
	private double maxDailyDemandVitaminPP;

	public double getDailyDemandVitaminD() {
		return dailyDemandVitaminD;
	}

	public void setDailyDemandVitaminD(double dailyDemandVitaminD) {
		this.dailyDemandVitaminD = dailyDemandVitaminD;
	}

	public double getDailyDemandVitaminK() {
		return dailyDemandVitaminK;
	}

	public void setDailyDemandVitaminK(double dailyDemandVitaminK) {
		this.dailyDemandVitaminK = dailyDemandVitaminK;
	}

	public double getDailyDemandVitaminB1() {
		return dailyDemandVitaminB1;
	}

	public void setDailyDemandVitaminB1(double dailyDemandVitaminB1) {
		this.dailyDemandVitaminB1 = dailyDemandVitaminB1;
	}

	public double getDailyDemandVitaminB2() {
		return dailyDemandVitaminB2;
	}

	public void setDailyDemandVitaminB2(double dailyDemandVitaminB2) {
		this.dailyDemandVitaminB2 = dailyDemandVitaminB2;
	}

	public double getDailyDemandVitaminB12() {
		return dailyDemandVitaminB12;
	}

	public void setDailyDemandVitaminB12(double dailyDemandVitaminB12) {
		this.dailyDemandVitaminB12 = dailyDemandVitaminB12;
	}

	public double getDailyDemandVitaminPP() {
		return dailyDemandVitaminPP;
	}

	public void setDailyDemandVitaminPP(double dailyDemandVitaminPP) {
		this.dailyDemandVitaminPP = dailyDemandVitaminPP;
	}

	public double getMaxDailyDemandVitaminD() {
		return maxDailyDemandVitaminD;
	}

	public void setMaxDailyDemandVitaminD(double maxDailyDemandVitaminD) {
		this.maxDailyDemandVitaminD = maxDailyDemandVitaminD;
	}

	public double getMaxDailyDemandVitaminPP() {
		return maxDailyDemandVitaminPP;
	}

	public void setMaxDailyDemandVitaminPP(double maxDailyDemandVitaminPP) {
		this.maxDailyDemandVitaminPP = maxDailyDemandVitaminPP;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public PhysicalActivity getPhysicalActivity() {
		return physicalActivity;
	}

	public void setPhysicalActivity(PhysicalActivity physicalActivity) {
		this.physicalActivity = physicalActivity;
	}

	public double getDailyDemandVitaminA() {
		return dailyDemandVitaminA;
	}

	public void setDailyDemandVitaminA(double dailyDemandVitaminA) {
		this.dailyDemandVitaminA = dailyDemandVitaminA;
	}

	public double getDailyDemandVitaminB6() {
		return dailyDemandVitaminB6;
	}

	public void setDailyDemandVitaminB6(double dailyDemandVitaminB6) {
		this.dailyDemandVitaminB6 = dailyDemandVitaminB6;
	}

	public double getDailyDemandVitaminC() {
		return dailyDemandVitaminC;
	}

	public void setDailyDemandVitaminC(double dailyDemandVitaminC) {
		this.dailyDemandVitaminC = dailyDemandVitaminC;
	}

	public double getDailyDemandVitaminE() {
		return dailyDemandVitaminE;
	}

	public void setDailyDemandVitaminE(double dailyDemandVitaminE) {
		this.dailyDemandVitaminE = dailyDemandVitaminE;
	}

	public double getMaxDailyDemandVitaminA() {
		return maxDailyDemandVitaminA;
	}

	public void setMaxDailyDemandVitaminA(double maxDailyDemandVitaminA) {
		this.maxDailyDemandVitaminA = maxDailyDemandVitaminA;
	}

	public double getMaxDailyDemandVitaminB6() {
		return maxDailyDemandVitaminB6;
	}

	public void setMaxDailyDemandVitaminB6(double maxDailyDemandVitaminB6) {
		this.maxDailyDemandVitaminB6 = maxDailyDemandVitaminB6;
	}

	public double getMaxDailyDemandVitaminC() {
		return maxDailyDemandVitaminC;
	}

	public void setMaxDailyDemandVitaminC(double maxDailyDemandVitaminC) {
		this.maxDailyDemandVitaminC = maxDailyDemandVitaminC;
	}

	public double getMaxDailyDemandVitaminE() {
		return maxDailyDemandVitaminE;
	}

	public void setMaxDailyDemandVitaminE(double maxDailyDemandVitaminE) {
		this.maxDailyDemandVitaminE = maxDailyDemandVitaminE;
	}

	public ArrayList<ObjectiveFunction> getObjectiveFunctions() {
		return objectiveFunctions;
	}

	public void setObjectiveFunctions(ArrayList<ObjectiveFunction> objectiveFunctions) {
		this.objectiveFunctions = objectiveFunctions;
	}

	@Override
	public String toString() {
		return "InputData: [age=" + age + ", gender=" + gender + ", physicalActivity=" + physicalActivity
				+ ", objectiveFunctions=" + objectiveFunctions.toString() + "]";
	}

	/**
	 * Method for getting daily demand for vitamins based on age and gender of
	 * person from csv file.
	 * 
	 * @param inputData
	 *            - the set of input data to update
	 * @return set of input data updated by daily demand for vitamins (min and
	 *         max values)
	 * @throws IOException
	 */
	public void getDailyDemandForVitamins(InputData inputData) throws IOException {
		List<String[]> amounOfVitamins = getDataFromCSVFile("./dane.csv");
		char male = 'M';
		char female = 'K';

		if (inputData.getGender() == male || inputData.getGender() == female) {
			if (isBetween(inputData.getAge(), 1, 3)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("1") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 4, 8)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("4") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 9, 13)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("9") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 14, 18)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("14") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 19, 30)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("19") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 31, 50)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("31") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 51, 70)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("51") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else if (isBetween(inputData.getAge(), 71, 125)) {
				for (int i = 0; i < amounOfVitamins.size(); i++) {
					String[] row = amounOfVitamins.get(i);
					if (row[0].equals("71") && row[2].equals(String.valueOf(inputData.getGender()))) {
						setDailyDemandForVitamins(inputData, row);
						break;
					}
				}
			} else {
				throw new IllegalArgumentException("Wiek poza zakresem!");
			}
		} else {
			throw new IllegalArgumentException("Nieprawidlowa plec!");
		}
	}

	/**
	 * Method for reading csv file.
	 * 
	 * @return parsed csv file
	 * @throws IOException
	 */
	private static List<String[]> getDataFromCSVFile(String fileName) throws IOException {
		List<String[]> data = new ArrayList<String[]>();

		CSVReader reader = new CSVReader(new FileReader(fileName), ';');
		data = reader.readAll();
		reader.close();

		return data;
	}

	/**
	 * Method for checking if given value is in range
	 * 
	 * @param x
	 *            - value to check if it is range
	 * @param lower
	 *            - lower value of range
	 * @param upper
	 *            - upper value of range
	 * @return true if {@code x} is in range, otherwise false
	 */
	private static boolean isBetween(int x, int lower, int upper) {
		return lower <= x && x <= upper;
	}

	/**
	 * Method for setting daily demand for vitamins
	 * 
	 * @param inputData
	 *            - the set of input data to update
	 * @param row
	 *            - row of csv file which is actually processed
	 * @return set of input data updated by daily demand for vitamins (min and
	 *         max values)
	 */
	private static InputData setDailyDemandForVitamins(InputData inputData, String[] row) {
		inputData.setDailyDemandVitaminA(Double.parseDouble(row[3]));
		inputData.setMaxDailyDemandVitaminA(Double.parseDouble(row[4]));
		inputData.setDailyDemandVitaminB6(Double.parseDouble(row[5]));
		inputData.setMaxDailyDemandVitaminB6(Double.parseDouble(row[6]));
		inputData.setDailyDemandVitaminC(Double.parseDouble(row[7]));
		inputData.setMaxDailyDemandVitaminC(Double.parseDouble(row[8]));
		inputData.setDailyDemandVitaminE(Double.parseDouble(row[9]));
		inputData.setMaxDailyDemandVitaminE(Double.parseDouble(row[10]));
		inputData.setDailyDemandVitaminD(Double.parseDouble(row[11]));
		inputData.setMaxDailyDemandVitaminD(Double.parseDouble(row[12]));
		inputData.setDailyDemandVitaminK(Double.parseDouble(row[13]));
		inputData.setDailyDemandVitaminB1(Double.parseDouble(row[14]));
		inputData.setDailyDemandVitaminB2(Double.parseDouble(row[15]));
		inputData.setDailyDemandVitaminB12(Double.parseDouble(row[16]));
		inputData.setDailyDemandVitaminPP(Double.parseDouble(row[17]));
		inputData.setMaxDailyDemandVitaminPP(Double.parseDouble(row[18]));

		return inputData;
	}

	/**
	 * Method for calculating daily demand for vitamins based on value of
	 * physical activity of person.
	 * 
	 * @param inputData
	 *            - the set of input data
	 * @return map with daily demand for each vitamin
	 */
	public HashMap<Vitamin, Double> calculateDailyDemandForVitaminsBaseOnPhysicalActivity(InputData inputData) {
		HashMap<Vitamin, Double> dailyDemandForVitamins = new HashMap<Vitamin, Double>();

		switch (inputData.getPhysicalActivity()) {
		case NONE:
			dailyDemandForVitamins.put(Vitamin.A, inputData.getDailyDemandVitaminA());
			dailyDemandForVitamins.put(Vitamin.D, inputData.getDailyDemandVitaminD());
			dailyDemandForVitamins.put(Vitamin.E, inputData.getDailyDemandVitaminE());
			dailyDemandForVitamins.put(Vitamin.K, inputData.getDailyDemandVitaminK());
			dailyDemandForVitamins.put(Vitamin.B1, inputData.getDailyDemandVitaminB1());
			dailyDemandForVitamins.put(Vitamin.B2, inputData.getDailyDemandVitaminB2());
			dailyDemandForVitamins.put(Vitamin.B6, inputData.getDailyDemandVitaminB6());
			dailyDemandForVitamins.put(Vitamin.B12, inputData.getDailyDemandVitaminB12());
			dailyDemandForVitamins.put(Vitamin.PP, inputData.getDailyDemandVitaminPP());
			dailyDemandForVitamins.put(Vitamin.C, inputData.getDailyDemandVitaminC());
			break;
		case LOW:
			dailyDemandForVitamins.put(Vitamin.A, inputData.getDailyDemandVitaminA() * 1.125);
			dailyDemandForVitamins.put(Vitamin.D, inputData.getDailyDemandVitaminD() * 1.5);
			dailyDemandForVitamins.put(Vitamin.E, inputData.getDailyDemandVitaminE() * 1.5);
			dailyDemandForVitamins.put(Vitamin.K, inputData.getDailyDemandVitaminK() * 1.5);
			dailyDemandForVitamins.put(Vitamin.B1, inputData.getDailyDemandVitaminB1() * 1.5);
			dailyDemandForVitamins.put(Vitamin.B2, inputData.getDailyDemandVitaminB2() * 1.5);
			dailyDemandForVitamins.put(Vitamin.B6, inputData.getDailyDemandVitaminB6() * 1.5);
			dailyDemandForVitamins.put(Vitamin.B12, inputData.getDailyDemandVitaminB12() * 1.5);
			dailyDemandForVitamins.put(Vitamin.PP, inputData.getDailyDemandVitaminPP() * 1.125);
			dailyDemandForVitamins.put(Vitamin.C, inputData.getDailyDemandVitaminC() * 1.5);
			break;
		case AVERAGE:
			dailyDemandForVitamins.put(Vitamin.A, inputData.getDailyDemandVitaminA() * 1.25);
			dailyDemandForVitamins.put(Vitamin.D, inputData.getDailyDemandVitaminD() * 2);
			dailyDemandForVitamins.put(Vitamin.E, inputData.getDailyDemandVitaminE() * 3);
			dailyDemandForVitamins.put(Vitamin.K, inputData.getDailyDemandVitaminK() * 3);
			dailyDemandForVitamins.put(Vitamin.B1, inputData.getDailyDemandVitaminB1() * 3);
			dailyDemandForVitamins.put(Vitamin.B2, inputData.getDailyDemandVitaminB2() * 3);
			dailyDemandForVitamins.put(Vitamin.B6, inputData.getDailyDemandVitaminB6() * 3);
			dailyDemandForVitamins.put(Vitamin.B12, inputData.getDailyDemandVitaminB12() * 3);
			dailyDemandForVitamins.put(Vitamin.PP, inputData.getDailyDemandVitaminPP() * 1.25);
			dailyDemandForVitamins.put(Vitamin.C, inputData.getDailyDemandVitaminC() * 3);
			break;
		case HIGH:
			dailyDemandForVitamins.put(Vitamin.A, inputData.getDailyDemandVitaminA() * 1.5);
			dailyDemandForVitamins.put(Vitamin.D, inputData.getDailyDemandVitaminD() * 3);
			dailyDemandForVitamins.put(Vitamin.E, inputData.getDailyDemandVitaminE() * 4);
			dailyDemandForVitamins.put(Vitamin.K, inputData.getDailyDemandVitaminK() * 4);
			dailyDemandForVitamins.put(Vitamin.B1, inputData.getDailyDemandVitaminB1() * 4);
			dailyDemandForVitamins.put(Vitamin.B2, inputData.getDailyDemandVitaminB2() * 4);
			dailyDemandForVitamins.put(Vitamin.B6, inputData.getDailyDemandVitaminB6() * 4);
			dailyDemandForVitamins.put(Vitamin.B12, inputData.getDailyDemandVitaminB12() * 4);
			dailyDemandForVitamins.put(Vitamin.PP, inputData.getDailyDemandVitaminPP() * 1.5);
			dailyDemandForVitamins.put(Vitamin.C, inputData.getDailyDemandVitaminC() * 4);
			break;
		default:
			throw new IllegalArgumentException("Nieprawidlowy poziom aktywnosci fizycznej!");
		}

		return dailyDemandForVitamins;
	}

	/**
	 * Method for creating map with max daily demand for each vitamin
	 * 
	 * @param inputData
	 *            - the set of input data
	 * @return map with max daily demand for each vitamin
	 */
	public HashMap<Vitamin, Double> calculateMaxDailyDemandForVitaminsBaseOnPhysicalActivity(InputData inputData) {
		HashMap<Vitamin, Double> maxDailyDemandForVitamins = new HashMap<Vitamin, Double>();

		maxDailyDemandForVitamins.put(Vitamin.A, inputData.getMaxDailyDemandVitaminA());
		maxDailyDemandForVitamins.put(Vitamin.D, inputData.getMaxDailyDemandVitaminD());
		maxDailyDemandForVitamins.put(Vitamin.E, inputData.getMaxDailyDemandVitaminE());
		maxDailyDemandForVitamins.put(Vitamin.B6, inputData.getMaxDailyDemandVitaminB6());
		maxDailyDemandForVitamins.put(Vitamin.PP, inputData.getMaxDailyDemandVitaminPP());
		maxDailyDemandForVitamins.put(Vitamin.C, inputData.getMaxDailyDemandVitaminC());

		return maxDailyDemandForVitamins;
	}
}
