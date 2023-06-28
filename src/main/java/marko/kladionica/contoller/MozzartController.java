package marko.kladionica.contoller;


import lombok.RequiredArgsConstructor;
import marko.kladionica.dao.MemberRepository;
import marko.kladionica.dao.ReportsRepository;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.Call;
import marko.kladionica.entity.Member;
import marko.kladionica.service.AddressServiceImpl;
import marko.kladionica.service.MembersServiceImpl;
import marko.kladionica.service.ReportsService;
import marko.kladionica.service.selenium.MaxBetService;
import marko.kladionica.service.selenium.MozzartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/mozzart")
@RequiredArgsConstructor
public class MozzartController {


    private final AddressServiceImpl addressService;
    private final MemberRepository memberRepository;
    private final ReportsService reportsService;


    @GetMapping
    public String showMozzart(Model theModel, Principal principal) {

        Address theAddress = addressService.findByName("mozzart");
        theModel.addAttribute("address", theAddress);

        Member theMember = memberRepository.findByName(principal.getName()).get();
        theModel.addAttribute("member", theMember);

        return "mozzart";
    }
    @GetMapping("/save")
    public String saveMozzart(@ModelAttribute("call") Call theCall, Principal principal) {

//        try {
//            mozzartService.getAllMatchesMozzartOrdinary(theCall.getAddress(), theCall.getTime());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        reportsService.save(theCall.getAddress(), principal.getName());
        return "home";
    }
}
