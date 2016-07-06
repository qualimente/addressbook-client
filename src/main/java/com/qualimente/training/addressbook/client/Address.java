package com.qualimente.training.addressbook.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

  private final String id;
    private final String line1;
    private final String line2;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;

    public Address(@JsonProperty("id") String id,
                   @JsonProperty("line1") String line1,
                   @JsonProperty("line2") String line2,
                   @JsonProperty("city") String city,
                   @JsonProperty("postalCode") String postalCode,
                   @JsonProperty("state") String state,
                   @JsonProperty("country") String country) {
      this.id = id;
      this.line1 = line1;
      this.line2 = line2;
      this.city = city;
      this.postalCode = postalCode;
      this.state = state;
      this.country = country;
    }

    public String getId() { return this.id; }
    public String getLine1() { return this.line1; }
    public String getLine2() { return this.line2; }
    public String getCity() { return this.city; }
    public String getState() { return this.state; }
    public String getPostalCode() { return this.postalCode; }
    public String getCountry() { return this.country; }
}
