package com.qualimente.training.addressbook.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.List;

public class AddressBookClientHttp implements AddressBookClient {

  private final String baseServerUri;

  private final RestOperations restClient;

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public AddressBookClientHttp(String baseServerUri) {
    this.baseServerUri = baseServerUri;
    this.restClient = new RestTemplate();
  }

  @Override
  public List<Address> findAddressesForCustomerId(String customerId) {
    return null;
  }

  @Override
  public Address addAddress(String customerId, Address address) {
    URI uri = new UriTemplate(getCustomerAddressesUrl()).expand(customerId);
    try {
      final RequestEntity<String> addAddressRequest = RequestEntity.post(uri)
          .contentType(MediaType.APPLICATION_JSON)
          .body(MAPPER.writeValueAsString(address));

      ResponseEntity<Address> responseEntity = restClient.exchange(addAddressRequest, Address.class);

      if (responseEntity.getStatusCode().is2xxSuccessful()) {
        return responseEntity.getBody();
      } else {
        throw new CouldNotAddAddressException("Could not add address, server responded with status " + responseEntity.getStatusCode());
      }

    } catch (JsonProcessingException e) {
      throw new SerializationException("Could not serialize address " + address, e);
    }
  }

  private String getCustomerAddressesUrl() {
    return baseServerUri + "/customers/{customerId}/addressbook/addresses";
  }

  static class SerializationException extends RuntimeException {
    SerializationException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  static class CouldNotAddAddressException extends RuntimeException {
    CouldNotAddAddressException(String message) {
      super(message);
    }
  }
}
