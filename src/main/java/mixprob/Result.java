package mixprob;

public class Result {
	private double amountBread;
	private double amountMilk;
	private double amountChicken;
	private double amountChocolate;
	private double amountOrange;
	private double amountCarrot;

	public Result() {
	}

	public Result(double[] results) {
		this.amountBread = results[1];
		this.amountMilk = results[2];
		this.amountChicken = results[3];
		this.amountChocolate = results[4];
		this.amountOrange = results[0];
		this.amountCarrot = results[5];
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
		return "[amountBread=" + amountBread + ", amountMilk=" + amountMilk + ", amountChicken=" + amountChicken
				+ ", amountChocolate=" + amountChocolate + ", amountOrange=" + amountOrange + ", amountCarrot="
				+ amountCarrot + "]";
	}
}
