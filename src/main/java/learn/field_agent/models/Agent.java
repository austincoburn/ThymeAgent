package learn.field_agent.models;

import learn.field_agent.validations.NoYoungAgents;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Agent {
    private int agentId;

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "Agent name cannot be greater than 50 characters.")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NoYoungAgents(message = "Agents must be older than 12 years old")
    private LocalDate dob;

    @Min(value = 36, message = "Height must be greater than {value}")
    @Max(value = 96, message = "Height must be less than {value}")
    private int heightInInches;
    private List<AgentAgency> agencies = new ArrayList<>();

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(int heightInInches) {
        this.heightInInches = heightInInches;
    }

    public List<AgentAgency> getAgencies() {
        return new ArrayList<>(agencies);
    }

    public void setAgencies(List<AgentAgency> agencies) {
        this.agencies = agencies;
    }
}
