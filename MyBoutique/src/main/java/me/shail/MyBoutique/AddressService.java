package me.shail.MyBoutique;

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
