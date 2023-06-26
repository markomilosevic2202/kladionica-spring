package marko.kladionica.contoller;


import lombok.RequiredArgsConstructor;
import marko.kladionica.dao.ReportsRepository;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.Call;
import marko.kladionica.entity.Member;
import marko.kladionica.entity.Reports;
import marko.kladionica.service.AddressServiceImpl;
import marko.kladionica.service.MembersServiceImpl;
import marko.kladionica.service.selenium.MaxBetService;
import marko.kladionica.service.selenium.MozzartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
@RequiredArgsConstructor
public class DemoController {
    private final MembersServiceImpl membersService;
    private final AddressServiceImpl addressService;
    private final MaxBetService maxBetService;
    private final MozzartService mozzartService;
    private final ReportsRepository reportsRepository;

    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    // add a request mapping for /leaders

    @GetMapping("/leaders")
    public String showLeaders() {
        return "leaders";
    }

    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }

    @GetMapping("/maxbet")
    public String showMaxBet(Model theModel, Principal principal) {

        Member theMember = membersService.findById(principal.getName());
        Address theAddress = addressService.findByName("maxbet");

         theModel.addAttribute("member", theMember);
        theModel.addAttribute("address", theAddress);

        return "max-bet";
    }

    @GetMapping("/mozzart")
    public String showMozzart(Model theModel, Principal principal) {

        System.out.println(principal.toString());

        Member theMember = membersService.findById(principal.getName());
        Address theAddress = addressService.findByName("mozzartbet");

        theModel.addAttribute("member", theMember);
        theModel.addAttribute("address", theAddress);

//        Reports reports1 = new Reports();
//        Address address = new Address();
//       // reports1.setDate("154.545");
//      //  address.setId(50);
//        address.setAddress("www.sfds.sd");
//        address.setName("dsafsdfsd");
//        reports1.setAddress(address);
//        System.out.println(reports1.toString());
//        Address address2 = new Address();
//        address2.setAddress("www.sdsd");
//        address2.setName("sadasd");
////        reports1.setMember();
//        reports1.setDate(LocalDateTime.now().toString());
//        reportsRepository.save(reports1);


        return "mozzart";
    }

    @GetMapping("/saveMaxBet")
    public String saveMaxBet(@ModelAttribute("call") Call theCall) {

        try {
            maxBetService.getAllMatchesMaxBetBonus(theCall.getAddress(), theCall.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "home";
    }

    @GetMapping("/saveMozzart")
    public String saveMozzart(@ModelAttribute("call") Call theCall) {

        try {
            mozzartService.getAllMatchesMozzartOrdinary(theCall.getAddress(), theCall.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "home";
    }
}