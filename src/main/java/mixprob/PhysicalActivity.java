package mixprob;

public enum PhysicalActivity {
	NONE("None"), LOW("Low"), AVERAGE("Average"), HIGH("High");

	public final String value;

	private PhysicalActivity(String value) {
		this.value = value;
	}
}
