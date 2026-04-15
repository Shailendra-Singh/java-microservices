package me.shail.MyBoutique.order;

import me.shail.MyBoutique.common.dto.AddressDto;

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
