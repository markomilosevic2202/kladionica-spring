package marko.kladionica.dao;

import marko.kladionica.entity.Address;
import marko.kladionica.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AddressRepository  extends JpaRepository<Address, Integer> {



    @Query("select a from Address a where a.name = ?1")
    Optional<Address> findByName(String name);

}
