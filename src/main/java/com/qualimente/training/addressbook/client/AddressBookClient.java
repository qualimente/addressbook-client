package com.qualimente.training.addressbook.client;

import java.util.List;

/**
 * AddressBookClient defines the client interface of the AddressBook service.
 */
public interface AddressBookClient {

  /**
   * Find the addresses for a given customer id
   * @param customerId the customer id, not null
   * @return return the customer's list of addresses, null when customer does not exist
   */
  List<Address> findAddressesForCustomerId(String customerId);

  /**
   * Add an address to a customer's list of addresses
   *  @param customerId the customer id, not null
   * @param address the address to add, not null
   */
  Address addAddress(String customerId, Address address);

}
