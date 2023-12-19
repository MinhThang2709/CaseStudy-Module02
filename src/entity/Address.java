package entity;

import java.io.Serializable;

public class Address implements Serializable {
    private String city;
    private String district;
    private String houseNumber;

    public Address() {

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Address houseNumber(String houseNumber) {
        this.houseNumber= houseNumber;
        return this;
    }

    public Address district(String district) {
        this.district= district;
        return this;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
