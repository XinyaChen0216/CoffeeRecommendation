package recommendation.model;                                                                                                                                           
                                                                                                                                                                
                                                                                                                                                                
public class CoffeeOrigins {                                                                                                                                           
	protected int coffeeOriginId;                                                                                                                                
	protected Coffee coffee;                                                                                                                                      
	protected Origin origin;
	
	
	public CoffeeOrigins(int coffeeOriginId, Coffee coffee, Origin origin) {
		super();
		this.coffeeOriginId = coffeeOriginId;
		this.coffee = coffee;
		this.origin = origin;
	}


	public CoffeeOrigins(Coffee coffee, Origin origin) {
		super();
		this.coffee = coffee;
		this.origin = origin;
	}


	public CoffeeOrigins(int coffeeOriginId) {
		super();
		this.coffeeOriginId = coffeeOriginId;
	}


	public int getCoffeeOriginId() {
		return coffeeOriginId;
	}


	public void setCoffeeOriginId(int coffeeOriginId) {
		this.coffeeOriginId = coffeeOriginId;
	}


	public Coffee getCoffee() {
		return coffee;
	}


	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}


	public Origin getOrigin() {
		return origin;
	}


	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	
	                                                                                                                                                             
                                                                                                                                                                
                                                                                                                                                                
}                                                                                                                                                               