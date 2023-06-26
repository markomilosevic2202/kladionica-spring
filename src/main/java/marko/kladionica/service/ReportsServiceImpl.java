package marko.kladionica.service;

import lombok.AllArgsConstructor;
import marko.kladionica.dao.AddressRepository;
import marko.kladionica.dao.MemberRepository;
import marko.kladionica.dao.ReportsRepository;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.Member;
import marko.kladionica.entity.Reports;
import org.springframework.stereotype.Service;
import weka.filters.unsupervised.attribute.Add;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportsServiceImpl implements ReportsService {

    private final ReportsRepository reportsRepository;
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    @Override
    public void save() {
        try {
            Optional<Member> optionalMember = memberRepository.findByEmail("sada@gmail.com");
            if (optionalMember.isEmpty()) {
                Member memeber = new Member();
                memeber.setEmail("sada@gmail.com");
                memeber.setPw("dsvsdv");
                optionalMember = Optional.of(memberRepository.save(memeber));
            }
            Optional<Address> optionalAddress = addressRepository.findByName("maxbet");
            if (optionalAddress.isEmpty()) {
                Address address = new Address();
                address.setName("maxbet");
                address.setAddress("www.maxbet.com");
                optionalAddress = Optional.of(addressRepository.save(address));
            }
            Reports reports = new Reports();
            reports.setMember(optionalMember.get());
            reports.setAddress(optionalAddress.get());
            reportsRepository.save(reports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
