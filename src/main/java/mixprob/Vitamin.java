package mixprob;

public enum Vitamin {
	A("A"), D("D"), E("E"), K("K"), B1("B1"), B2("B2"), B6("B6"), B12("B12"), C("C"), PP("PP");

	public final String vitaminName;

	private Vitamin(String vitaminName) {
		this.vitaminName = vitaminName;
	}

}
