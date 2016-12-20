package mixprob;

public class Results {
	private double amountBread;
	private double amountMilk;
	private double amountChicken;
	private double amountChocolate;
	private double amountOrange;
	private double amountCarrot;

	public Results() {
		super();
	}

	public Results(double amountBread, double amountMilk, double amountChicken, double amountChocolate,
			double amountOrange, double amountCarrot) {
		super();
		this.amountBread = amountBread;
		this.amountMilk = amountMilk;
		this.amountChicken = amountChicken;
		this.amountChocolate = amountChocolate;
		this.amountOrange = amountOrange;
		this.amountCarrot = amountCarrot;
	}

	public double getAmountBread() {
		return amountBread;
	}

	public void setAmountBread(double amountBread) {
		this.amountBread = amountBread;
	}

	public double getAmountMilk() {
		return amountMilk;
	}

	public void setAmountMilk(double amountMilk) {
		this.amountMilk = amountMilk;
	}

	public double getAmountChicken() {
		return amountChicken;
	}

	public void setAmountChicken(double amountChicken) {
		this.amountChicken = amountChicken;
	}

	public double getAmountChocolate() {
		return amountChocolate;
	}

	public void setAmountChocolate(double amountChocolate) {
		this.amountChocolate = amountChocolate;
	}

	public double getAmountOrange() {
		return amountOrange;
	}

	public void setAmountOrange(double amountOrange) {
		this.amountOrange = amountOrange;
	}

	public double getAmountCarrot() {
		return amountCarrot;
	}

	public void setAmountCarrot(double amountCarrot) {
		this.amountCarrot = amountCarrot;
	}

	@Override
	public String toString() {
		return "Results: [amountBread=" + amountBread + ", amountMilk=" + amountMilk + ", amountChicken=" + amountChicken
				+ ", amountChocolate=" + amountChocolate + ", amountOrange=" + amountOrange + ", amountCarrot="
				+ amountCarrot + "]";
	}
}
