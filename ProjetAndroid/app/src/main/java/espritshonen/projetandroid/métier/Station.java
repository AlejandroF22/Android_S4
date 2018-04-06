package espritshonen.projetandroid.métier;

public class Station {
    private String name;
    private String address;
    private String status; //OPEN or CLOSE
    private double lat, lng;
    private int bike_stands;
    private int available_bikes;
    private int available_bike_stands;

    public Station(String name, String address, String status, double lat, double lng, int bike_stands, int available_bikes, int available_bike_stands) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.lat = lat;
        this.lng = lng;
        this.bike_stands = bike_stands;
        this.available_bikes = available_bikes;
        this.available_bike_stands = available_bike_stands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getBike_stands() {
        return bike_stands;
    }

    public void setBike_stands(int bike_stands) {
        this.bike_stands = bike_stands;
    }

    public int getAvailable_bikes() {
        return available_bikes;
    }

    public void setAvailable_bikes(int available_bikes) {
        this.available_bikes = available_bikes;
    }

    public int getAvailable_bike_stands() {
        return available_bike_stands;
    }

    public void setAvailable_bike_stands(int available_bike_stands) {
        this.available_bike_stands = available_bike_stands;
    }

    public String toString()
    {
        return name +
                "" + address +
                "" + status +
                "" + lat +
                "" + lng +
                "" + bike_stands +
                "" + available_bikes +
                "" + available_bike_stands;
    }
}

