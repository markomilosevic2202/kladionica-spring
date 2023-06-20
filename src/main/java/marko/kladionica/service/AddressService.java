package marko.kladionica.service;

import marko.kladionica.entity.Address;
import marko.kladionica.entity.Member;

public interface AddressService {

    public Address findByName(String name);
}
