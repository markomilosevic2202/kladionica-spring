package marko.kladionica.contoller;


import lombok.RequiredArgsConstructor;
import marko.kladionica.dao.ReportsRepository;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.Call;
import marko.kladionica.entity.Member;
import marko.kladionica.service.AddressServiceImpl;
import marko.kladionica.service.MembersServiceImpl;
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

    private final MembersServiceImpl membersService;
    private final AddressServiceImpl addressService;
    private final MaxBetService maxBetService;
    private final MozzartService mozzartService;
    private final ReportsRepository reportsRepository;

    @GetMapping
    public String showMozzart(Model theModel, Principal principal) {

        System.out.println(principal.toString());

        Member theMember = membersService.findById(principal.getName());
        Address theAddress = addressService.findByName("mozzartbet");

        theModel.addAttribute("member", theMember);
        theModel.addAttribute("address", theAddress);

        return "mozzart";
    }
    @GetMapping("/save")
    public String saveMozzart(@ModelAttribute("call") Call theCall) {

        try {
            mozzartService.getAllMatchesMozzartOrdinary(theCall.getAddress(), theCall.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "home";
    }
}
