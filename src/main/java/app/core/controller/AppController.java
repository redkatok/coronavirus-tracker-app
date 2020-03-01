package app.core.controller;

import app.core.model.LocationStats;
import app.core.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppController {


    @Autowired
    private CoronaVirusDataService service;

    @GetMapping("/")
    public String home(Model model) {

        List<LocationStats> allStats = service.getAllStats();
        int totalReportedCases=allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
        int totalNewCases=allStats.stream().mapToInt(stat->stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationStats",allStats);
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
