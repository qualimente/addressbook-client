package com.qualimente.training.addressbook.client;

import io.specto.hoverfly.junit.HoverflyRule;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * AddressBookClientHttpIT is responsible for verifying that AddressBookClientHttp works against
 * the documented AddressBook API contract.
 */
public class AddressBookClientHttpIT {


  @SuppressWarnings("unused") //API_CONTRACT_EXAMPLES_CLASSPATH can be used for local examples and debugging!
  protected static final String API_CONTRACT_EXAMPLES_CLASSPATH = "examples.addressbook-contract.json";
  protected static final String API_CONTRACT_EXAMPLES_URL = "https://raw.githubusercontent.com/qualimente/addressbook-api/master/src/main/resources/request_to_add_an_address_for_a_new_customer_should_succeed.json";
  protected static final String BASE_SERVER_URI = "http://addressbook.qualimente.com";

  @ClassRule
  public static final HoverflyRule HOVERFLY_RULE = HoverflyRule.buildFromUrl(API_CONTRACT_EXAMPLES_URL)
      .build();

  @Test
  public void addAddress_should_add_new_address_for_customer() throws Exception {
    AddressBookClient client = new AddressBookClientHttp(BASE_SERVER_URI); //returns 500; Host header mis-match because data says http://localhost:65360?

    String customerId = "c92294cb-c609-4af7-9761-538b19c9761e";
    Address newAddress = new Address(null,
        "42 Douglas Adams Way",
        null,
        "Phoenix", "85042", "AZ",
        "US");

    Address actual = client.addAddress(customerId, newAddress);

    assertNotNull(actual.getId());
    assertEquals(newAddress.getLine1(), actual.getLine1());
    assertNull(actual.getLine2());
    assertEquals(newAddress.getCity(), actual.getCity());
    assertEquals(newAddress.getPostalCode(), actual.getPostalCode());
    assertEquals(newAddress.getState(), actual.getState());
    assertEquals(newAddress.getCountry(), actual.getCountry());
  }

}