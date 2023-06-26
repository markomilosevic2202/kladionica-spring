package marko.kladionica.dao;


import marko.kladionica.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("select m from Member m where m.pw = ?1")
    Optional<Member> findByPw(String pw);

    @Query("select m from Member m where m.email = ?1")
    Optional<Member> findByEmail(String email);



    // that's it ... no need to write any code LOL!

    // add a method to sort by last name
    //public List<Employee> findAllByOrderByLastNameASC();
	
}
