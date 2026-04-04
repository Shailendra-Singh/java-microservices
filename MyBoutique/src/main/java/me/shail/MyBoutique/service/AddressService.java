package me.shail.MyBoutique.service;

import me.shail.MyBoutique.dto.AddressDto;
import me.shail.MyBoutique.model.Address;

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
