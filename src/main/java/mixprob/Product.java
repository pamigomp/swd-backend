package mixprob;

import java.util.HashMap;

public class Product {
	private String name;
	private double amountVitaminA;
	private double amountVitaminD;
	private double amountVitaminE;
	private double amountVitaminK;
	private double amountVitaminB1;
	private double amountVitaminB2;
	private double amountVitaminB6;
	private double amountVitaminB12;
	private double amountVitaminC;
	private double amountVitaminPP;
	private Mischievousness mischievousness;
	private double price;

	public Product(String name, double amountVitaminA, double amountVitaminD, double amountVitaminE,
			double amountVitaminK, double amountVitaminB1, double amountVitaminB2, double amountVitaminB6,
			double amountVitaminB12, double amountVitaminC, double amountVitaminPP, Mischievousness mischievousness,
			double price) {
		this.name = name;
		this.amountVitaminA = amountVitaminA;
		this.amountVitaminD = amountVitaminD;
		this.amountVitaminE = amountVitaminE;
		this.amountVitaminK = amountVitaminK;
		this.amountVitaminB1 = amountVitaminB1;
		this.amountVitaminB2 = amountVitaminB2;
		this.amountVitaminB6 = amountVitaminB6;
		this.amountVitaminB12 = amountVitaminB12;
		this.amountVitaminC = amountVitaminC;
		this.amountVitaminPP = amountVitaminPP;
		this.mischievousness = mischievousness;
		this.price = price;
	}

	public double getAmountVitaminA() {
		return amountVitaminA;
	}

	public void setAmountVitaminA(double amountVitaminA) {
		this.amountVitaminA = amountVitaminA;
	}

	public double getAmountVitaminD() {
		return amountVitaminD;
	}

	public void setAmountVitaminD(double amountVitaminD) {
		this.amountVitaminD = amountVitaminD;
	}

	public double getAmountVitaminE() {
		return amountVitaminE;
	}

	public void setAmountVitaminE(double amountVitaminE) {
		this.amountVitaminE = amountVitaminE;
	}

	public double getAmountVitaminK() {
		return amountVitaminK;
	}

	public void setAmountVitaminK(double amountVitaminK) {
		this.amountVitaminK = amountVitaminK;
	}

	public double getAmountVitaminB1() {
		return amountVitaminB1;
	}

	public void setAmountVitaminB1(double amountVitaminB1) {
		this.amountVitaminB1 = amountVitaminB1;
	}

	public double getAmountVitaminB2() {
		return amountVitaminB2;
	}

	public void setAmountVitaminB2(double amountVitaminB2) {
		this.amountVitaminB2 = amountVitaminB2;
	}

	public double getAmountVitaminB6() {
		return amountVitaminB6;
	}

	public void setAmountVitaminB6(double amountVitaminB6) {
		this.amountVitaminB6 = amountVitaminB6;
	}

	public double getAmountVitaminB12() {
		return amountVitaminB12;
	}

	public void setAmountVitaminB12(double amountVitaminB12) {
		this.amountVitaminB12 = amountVitaminB12;
	}

	public double getAmountVitaminC() {
		return amountVitaminC;
	}

	public void setAmountVitaminC(double amountVitaminC) {
		this.amountVitaminC = amountVitaminC;
	}

	public double getAmountVitaminPP() {
		return amountVitaminPP;
	}

	public void setAmountVitaminPP(double amountVitaminPP) {
		this.amountVitaminPP = amountVitaminPP;
	}

	public Mischievousness getMischievousness() {
		return mischievousness;
	}

	public void setMischievousness(Mischievousness mischievousness) {
		this.mischievousness = mischievousness;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "=[amountVitaminA=" + amountVitaminA + ", amountVitaminD=" + amountVitaminD + ", amountVitaminE="
				+ amountVitaminE + ", amountVitaminK=" + amountVitaminK + ", amountVitaminB1=" + amountVitaminB1
				+ ", amountVitaminB2=" + amountVitaminB2 + ", amountVitaminB6=" + amountVitaminB6
				+ ", amountVitaminB12=" + amountVitaminB12 + ", amountVitaminC=" + amountVitaminC + ", amountVitaminPP="
				+ amountVitaminPP + ", mischievousness=" + mischievousness + ", price=" + price + "]\n";
	}

	public static HashMap<String, Product> createProducts() {
		HashMap<String, Product> products = new HashMap<String, Product>();

		products.put("Chleb", new Product("Chleb", 0.0021, 0, 0.33, 1.2, 0.434, 0.335, 0.075, 0, 0.4, 3.805,
				Mischievousness.LOW, 0.6));
		products.put("Mleko", new Product("Mleko", 0.057, 0.001225, 0.03, 0.2, 0.039, 0.185, 0.038, 0.00053, 0.2, 0.092,
				Mischievousness.LOW, 0.5));
		products.put("Kurczak", new Product("Kurczak", 0.0279, 0.000125, 0.27, 0.3, 0.066, 0.119, 0.56, 0.00032, 0,
				12.71, Mischievousness.AVERAGE, 1.2));
		products.put("Czekolada", new Product("Czekolada", 0.0525, 0, 3.14, 5.1, 0.06, 0.435, 0.045, 0.00067, 0.2,
				0.742, Mischievousness.HIGH, 3.5));
		products.put("Pomarancza", new Product("Pomarancza", 0.0675, 0, 0.18, 0, 0.087, 0.04, 0.06, 0, 53.2, 0.282,
				Mischievousness.LOW, 0.5));
		products.put("Marchew", new Product("Marchew", 5.0118, 0, 0.66, 13.2, 0.066, 0.058, 0.138, 0, 5.9, 0.983,
				Mischievousness.LOW, 0.3));

		return products;
	}

	public static double[] getSumOfVitamins(HashMap<String, Product> products) {
		double[] sums = new double[products.size()];
		int i = 0;

		for (String key : products.keySet()) {
			sums[i] = products.get(key).getAmountVitaminA() + products.get(key).getAmountVitaminD()
					+ products.get(key).getAmountVitaminE() + products.get(key).getAmountVitaminK()
					+ products.get(key).getAmountVitaminB1() + products.get(key).getAmountVitaminB2()
					+ products.get(key).getAmountVitaminB6() + products.get(key).getAmountVitaminB12()
					+ products.get(key).getAmountVitaminC() + products.get(key).getAmountVitaminPP();
			i++;
		}

		return sums;
	}

	public static double[] getPrices(HashMap<String, Product> products) {
		double[] prices = new double[products.size()];
		int i = 0;

		for (String key : products.keySet()) {
			prices[i] = products.get(key).getPrice();
			i++;
		}

		return prices;
	}

	public static double[] getMischievousness(HashMap<String, Product> products) {
		double[] mischievousness = new double[products.size()];
		int i = 0;

		for (String key : products.keySet()) {
			mischievousness[i] = products.get(key).getMischievousness().value;
			i++;
		}

		return mischievousness;
	}

	public static double[] getVitamins(HashMap<String, Product> products, String vitamin) {
		double[] vitamins = new double[products.size()];
		int i = 0;

		for (String key : products.keySet()) {
			switch (vitamin) {
			case "A":
				vitamins[i] = products.get(key).getAmountVitaminA();
				break;
			case "D":
				vitamins[i] = products.get(key).getAmountVitaminD();
				break;
			case "E":
				vitamins[i] = products.get(key).getAmountVitaminE();
				break;
			case "K":
				vitamins[i] = products.get(key).getAmountVitaminK();
				break;
			case "B1":
				vitamins[i] = products.get(key).getAmountVitaminB1();
				break;
			case "B2":
				vitamins[i] = products.get(key).getAmountVitaminB2();
				break;
			case "B6":
				vitamins[i] = products.get(key).getAmountVitaminB6();
				break;
			case "B12":
				vitamins[i] = products.get(key).getAmountVitaminB12();
				break;
			case "C":
				vitamins[i] = products.get(key).getAmountVitaminC();
				break;
			case "PP":
				vitamins[i] = products.get(key).getAmountVitaminPP();
				break;
			}

			i++;
		}

		return vitamins;
	}
}
