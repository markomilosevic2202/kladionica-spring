package marko.kladionica.dao;

import marko.kladionica.entity.Member;
import marko.kladionica.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportsRepository  extends JpaRepository<Reports, String> {




    @Query("SELECT r FROM Reports r WHERE r.member.name = ?1 OR ?1 IS NULL OR ?1 = ''")
    List<Reports> findByMemberId(String member_id);


}
