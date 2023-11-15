//package coffeeRecommendation;

public class Roaster {
    protected Coffee coffee;
    protected Location location;

    public Roaster(Coffee coffee, Location location) {
        this.coffee = coffee;
        this.locationName = location;
    }

    // getters and setters
    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
