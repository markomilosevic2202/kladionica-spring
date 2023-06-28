package marko.kladionica.service;

import lombok.AllArgsConstructor;
import marko.kladionica.dao.AddressRepository;
import marko.kladionica.dao.MemberRepository;
import marko.kladionica.dao.ReportsRepository;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.Member;
import marko.kladionica.entity.Reports;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportsServiceImpl implements ReportsService {

    private final ReportsRepository reportsRepository;
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private static Integer  i = 0;

    @Override
    public void save(String address, String nameMember) {
        try {
            Optional<Member> optionalMember = memberRepository.findByName(nameMember);
            if (optionalMember.isEmpty()) {
                Member memeber = new Member();
                memeber.setEmail(nameMember);
                memeber.setPw("dsvsdv");
                optionalMember = Optional.of(memberRepository.save(memeber));
            }
            Optional<Address> optionalAddress = addressRepository.findByAddress(address);
            if (optionalAddress.isEmpty()) {
                i++;
                Address addressLocal = new Address();
                addressLocal.setName("maxbet" + i );
                addressLocal.setAddress(address);
                optionalAddress = Optional.of(addressRepository.save(addressLocal));
            }
            Reports reports = new Reports();
            reports.setMember(optionalMember.get());
            reports.setAddress(optionalAddress.get());
            reportsRepository.save(reports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reports> getList() {
        return reportsRepository.findAll();
    }

    @Override
    public List<Reports> getListByUser(String nameMember) {

        List<Reports> optionalAddress = reportsRepository.findByMemberId(nameMember);
        return optionalAddress;
    }
}
