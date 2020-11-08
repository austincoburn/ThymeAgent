package learn.field_agent.controllers.ui;

import learn.field_agent.domain.AgentService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/agents")
public class AgentUIController {
    @Autowired
    AgentService service;

    @GetMapping
    public String get(Model model) throws DataAccessException {
        List<Agent> agents = service.findAll();
        model.addAttribute("agents", agents);
        return "agents/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("agent") Agent agent, Model model) {
        //set default values
        agent.setFirstName("");
        agent.setLastName("");
        agent.setHeightInInches(55);
        agent.setDob(LocalDate.now());

        return "agents/create";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("agent") @Valid Agent agent, BindingResult result, Model model) throws DataAccessException {
        if(result.hasErrors()) {
            return "agents/create";
        }
        service.add(agent);
        return "redirect:/agents";
    }
}
