package divhacks2018;

public class Digit {

	public int value = 0;
	
	public int easiness = 0;
	public int hardness = 0;
	
	private String classification ="";
	
	public Digit(int value) {
		this.value = value;
	}
	
	public void setClassification(String s) {
		this.classification=s;
	}
	
	@Override
	public String toString() {
		return classification;
		
	}
}
