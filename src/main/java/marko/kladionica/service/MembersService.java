package marko.kladionica.service;

import marko.kladionica.entity.Member;
import marko.kladionica.entity.Reports;

import java.util.List;

public interface MembersService {

   public Member findById(String theId);

   public List<Member> getListAllMember();
}
