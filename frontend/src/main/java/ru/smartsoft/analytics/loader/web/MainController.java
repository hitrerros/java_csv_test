package ru.smartsoft.analytics.loader.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.smartsoft.analytics.loader.web.db.UserHistoryDao;

@Controller
public class MainController {

    @Autowired
    private UserHistoryDao userHistoryDao;

    @RequestMapping(value = "/last_hour", method = RequestMethod.GET)
    public String getFormsOfLastHour(Model model) {
        model.addAttribute("data_bean",userHistoryDao.collectLastHourStatistics());
        return "lastHour.html";
    }


    @RequestMapping(value = "/incomplete_activities", method = RequestMethod.GET)
    public String getIncompleteActivities(Model model) {
        model.addAttribute("data_bean",userHistoryDao.collectIncompleteActions());
        return "incomplete";
    }

    @RequestMapping(value = "/top_forms", method = RequestMethod.GET)
    public String getTopForms(Model model) {
        model.addAttribute("data_bean",userHistoryDao.collectTopUsedForms());
        return "topForms";
    }
}
