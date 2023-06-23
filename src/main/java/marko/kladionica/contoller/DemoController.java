package marko.kladionica.contoller;


import lombok.RequiredArgsConstructor;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.Call;
import marko.kladionica.entity.Member;
import marko.kladionica.service.AddressServiceImpl;
import marko.kladionica.service.MaxiService;
import marko.kladionica.service.MembersServiceImpl;
import marko.kladionica.service.selenium.MaxBetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class DemoController {
    private final MembersServiceImpl membersService;
    private final AddressServiceImpl addressService;
    private final MaxiService maxiService;
   private final MaxBetService maxBetService;
    @GetMapping("/")
    public String showHome(){

        return "home";
    }

    // add a request mapping for /leaders

    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders";
    }
    @GetMapping("/systems")
    public String showSystems(){
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
    @GetMapping("/save")
    public String saveEmployee(@ModelAttribute("call") Call theCall){

        try {
            maxBetService.getAllMatchesMaxBetBonus(theCall.getAddress(),theCall.getTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "home";
    }
}