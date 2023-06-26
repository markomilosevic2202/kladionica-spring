package marko.kladionica.dao;

import marko.kladionica.entity.Member;
import marko.kladionica.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportsRepository  extends JpaRepository<Reports, String> {
}
