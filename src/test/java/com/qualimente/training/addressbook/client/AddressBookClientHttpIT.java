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

  protected static final String API_CONTRACT_EXAMPLES_CLASSPATH = "examples.addressbook-contract.json";
  protected static final String BASE_SERVER_URI = "http://addressbook.qualimente.com";

  @ClassRule
  public static final HoverflyRule HOVERFLY_RULE = HoverflyRule.buildFromClassPathResource(API_CONTRACT_EXAMPLES_CLASSPATH)
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

/*
  @Test
  public void addAddress_should_add_new_address_for_customer_intentionally_unhandled() throws Exception {
    AddressBookClient client = new AddressBookClientHttp(BASE_SERVER_URI);
    Address expected = new Address(null, "address line 1", "line2", "city", "postal code", "state", "country");
    Address actual = client.addAddress("abcd-1234", expected);

    assertEquals(expected, actual);
  }
*/

}