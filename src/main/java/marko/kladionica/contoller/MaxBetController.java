package marko.kladionica.contoller;


import lombok.RequiredArgsConstructor;
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
@RequestMapping("/maxbet")
@RequiredArgsConstructor
public class MaxBetController {
    private final MembersServiceImpl membersService;
    private final AddressServiceImpl addressService;
    private final MaxBetService maxBetService;
    private final ReportsService reportsService;


    @GetMapping()
    public String showMaxBet(Model theModel, Principal principal) {

//        Member theMember = membersService.findById(principal.getName());
//        Address theAddress = addressService.findByName("maxbet");
//
//        theModel.addAttribute("member", theMember);
//        theModel.addAttribute("address", theAddress);

        return "max-bet";
    }

    @GetMapping("/save")
    public String saveMaxBet(@ModelAttribute("call") Call theCall) {

//        try {
//            maxBetService.getAllMatchesMaxBetBonus(theCall.getAddress(), theCall.getTime());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        reportsService.save();

        return "home";
    }
}
