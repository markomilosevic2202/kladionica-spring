package marko.kladionica.service;

import lombok.AllArgsConstructor;
import marko.kladionica.dao.AddressRepository;
import marko.kladionica.dao.MemberRepository;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    @Override
    public Address findByName(String name) {

//        Optional<Address> optionalAddress = addressRepository.findByName(name);
//        Address theAddress = null;
//
//        if (optionalAddress.isPresent()) {
//            theAddress = optionalAddress.get();
//        }
//        else {
//            // we didn't find the employee
//            throw new RuntimeException("Did not find employee id - " + name);
//        }

        return  new Address(4,"hard", "hard-core");
    }



}
