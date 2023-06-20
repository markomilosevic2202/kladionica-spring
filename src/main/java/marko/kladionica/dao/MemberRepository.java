package marko.kladionica.dao;


import marko.kladionica.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("select m from Member m where m.pw = ?1")
    Optional<Member> findByPw(String id);

    @Query("select m from Member m where m.email = ?1")
    Optional<Member> findByEmail(String id);

    // that's it ... no need to write any code LOL!

    // add a method to sort by last name
    //public List<Employee> findAllByOrderByLastNameASC();
	
}
