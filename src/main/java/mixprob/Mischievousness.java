package mixprob;

public enum Mischievousness {
	LOW(0.1), AVERAGE(0.5), HIGH(1);

	double value;

	private Mischievousness(double value) {
		this.value = value;
	}
}
