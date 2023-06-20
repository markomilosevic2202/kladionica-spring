package marko.kladionica.contoller;


import lombok.RequiredArgsConstructor;
import marko.kladionica.entity.Address;
import marko.kladionica.entity.Call;
import marko.kladionica.entity.Employee;
import marko.kladionica.entity.Member;
import marko.kladionica.example.test_data_bils.Match;
import marko.kladionica.service.AddressServiceImpl;
import marko.kladionica.service.MaxiService;
import marko.kladionica.service.MembersService;
import marko.kladionica.service.MembersServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class DemoController {
    private final MembersServiceImpl membersService;
    private final AddressServiceImpl addressService;
    private final MaxiService maxiService;
    @GetMapping("/")
    public String showHome( String theId, Model theModel){ //@RequestParam("employeeId")
//        Member theMember = membersService.findById("marko");
//        // set employee in the model to prepopulate the form
//        theModel.addAttribute("member", theMember);
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
        // save the employee
        System.out.println(theCall.toString());

        // use a redirect to prevent duplicate submissions
        return "home";
    }
}