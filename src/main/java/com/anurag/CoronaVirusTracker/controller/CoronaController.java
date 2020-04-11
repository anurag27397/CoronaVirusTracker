package com.anurag.CoronaVirusTracker.controller;

import com.anurag.CoronaVirusTracker.model.CoronaModel;
import com.anurag.CoronaVirusTracker.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CoronaController {

    @Autowired
    DataService coronaDataService;


    @GetMapping("/")
    public String home(Model model)
    {
        List<CoronaModel> allStats = coronaDataService.getCoronaStats();
        int totalConfirmedCases= allStats.stream().mapToInt(stat -> stat.getConfirmedCase()).sum();

        model.addAttribute("locationStats", allStats);

      //  model.addAttribute("totalConfirmedCases", totalConfirmedCases);
        String numberAsString = String.format("%,d", totalConfirmedCases);

        model.addAttribute("totalConfirmedCases", numberAsString);

        return "home";
    }

}
