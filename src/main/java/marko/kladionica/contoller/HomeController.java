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

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MembersServiceImpl membersService;
    private final AddressServiceImpl addressService;
    private final MaxBetService maxBetService;
    private final MozzartService mozzartService;
    private final ReportsRepository reportsRepository;

    @GetMapping("/")
    public String showHome() {
        return "home";
    }










}