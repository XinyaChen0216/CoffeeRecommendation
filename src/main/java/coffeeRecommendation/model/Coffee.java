package coffee.model;


public class Coffee {
	protected String coffeeName;
	protected RoastType roastType;
	protected Double price;
	protected String roasterName;
	
	
	
	public enum RoastType{
		Light, MediumLight, Medium, MediumDark, Dark
	}

	
	// This constructor can be used for reading records from MySQL, where we have all fields,
	// including the coffeeName.

	public Coffee(String coffeeName, RoastType roastType, Double price, String roasterName) {
		super();
		this.coffeeName = coffeeName;
		this.roastType = roastType;
		this.price = price;
		this.roasterName = roasterName;
	}

	// This constructor can be used for reading records from MySQL, where we only have the coffeeName,
	// such as a foreign key reference to coffeeName.
	// Given coffeeName, we can fetch the full coffee record.


	public Coffee(String coffeeName) {
		super();
		this.coffeeName = coffeeName;
	}
	
	
   // getters and setters
	public String getCoffeeName() {
		return coffeeName;
	}

	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}
	
	public RoastType getRoastType() {
		return roastType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRoasterName() {
		return roasterName;
	}

	public void setRoasterName(String roasterName) {
		this.roasterName = roasterName;
	}






}