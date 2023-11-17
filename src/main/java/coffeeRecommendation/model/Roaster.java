package recommendation.model;

public class Roaster {
    protected String roasterName;
    protected String locationName;

    public Roaster(String roasterName, String locationName) {
        this.roasterName = roasterName;
        this.locationName = locationName;
    }

    public String getRoasterName() {
      return roasterName;
    }

    public void setRoasterName(String roasterName) {
      this.roasterName = roasterName;
    }

    public String getLocationName() {
      return locationName;
    }

    public void setLocationName(String locationName) {
      this.locationName = locationName;
    }
}