package marko.kladionica.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import marko.kladionica.dao.MemberRepository;
import marko.kladionica.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MembersServiceImpl implements MembersService {


    private final MemberRepository memberRepository;

    @Override
    public Member findById(String id) {

        Optional<Member> optionalMember = memberRepository.findById(id);
        Member theMember = null;

        if (optionalMember.isPresent()) {
            theMember = optionalMember.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + id);
        }

        return theMember;
    }

}
