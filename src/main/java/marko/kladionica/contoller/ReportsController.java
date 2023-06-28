package marko.kladionica.contoller;

import lombok.RequiredArgsConstructor;
import marko.kladionica.entity.Call;
import marko.kladionica.entity.Member;
import marko.kladionica.entity.Reports;
import marko.kladionica.service.MembersService;
import marko.kladionica.service.MembersServiceImpl;
import marko.kladionica.service.ReportsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ReportsService reportsService;
    private final MembersService membersService;

    @GetMapping()
    public String showReports(Model theModel) {
        List<Reports> listReports = reportsService.getList();
        theModel.addAttribute("reports", listReports);
        List<Member> listMember = membersService.getListAllMember();
        theModel.addAttribute("member", listMember);

        return "reports-list";

    }
    @GetMapping("/user")
    public String showReportsFilterMember( @RequestParam("name") String selectedOption,Model theModel) {
        List<Reports> listReports = reportsService.getListByUser(selectedOption);
        theModel.addAttribute("reports", listReports);
        List<Member> listMember = membersService.getListAllMember();
        theModel.addAttribute("member", listMember);

        return "reports-list";
    }
}
