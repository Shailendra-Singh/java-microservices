package me.shail.order_service.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import me.shail.myboutique_commons.dto.AddressDto;
import me.shail.order_service.model.Address;

@CircuitBreaker(name = "address-service")
public class AddressService {
    public static AddressDto mapToDto(Address address) {
        if (address != null) {
            return new AddressDto(
                    address.getAddress1(),
                    address.getAddress2(),
                    address.getCity(),
                    address.getPostalCode(),
                    address.getCountry());
        }

        return null;
    }
}
