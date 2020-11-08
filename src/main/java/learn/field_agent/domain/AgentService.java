package learn.field_agent.domain;

import learn.field_agent.data.AgentRepository;
import learn.field_agent.models.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class AgentService {

    @Autowired
    private Validator validator;

    private enum ValidationMode {
        CREATE, UPDATE;
    }

    private final AgentRepository repository;

    public AgentService(AgentRepository repository) {
        this.repository = repository;
    }

    public List<Agent> findAll() {
        return repository.findAll();
    }

    public Agent findById(int agentId) {
        return repository.findById(agentId);
    }

    public Result<Agent> add(Agent agent) {
        Result<Agent> result = validate(agent, ValidationMode.CREATE);
//
//        if (agent.getAgentId() != 0) {
//            result.addMessage("agentId cannot be set for `add` operation", ResultType.INVALID);
//            return result;
//        }

        if(result.isSuccess()) {
            agent = repository.add(agent);
            result.setPayload(agent);
        }

        return result;
    }

    public Result<Agent> update(Agent agent) {
        Result<Agent> result = validate(agent, ValidationMode.UPDATE);
//        if (agent.getAgentId() <= 0) {
//            result.addMessage("agentId must be set for `update` operation", ResultType.INVALID);
//            return result;
//        }

//        if (result.isSuccess()) {
//            Set<ConstraintViolation<Agent>> violations = validator.validate(agent);
//            if(!violations.isEmpty()) {
//                for (ConstraintViolation<Agent> violation : violations) {
//                    result.addMessage(violation.getMessage(), ResultType.INVALID);
//                }
//            }
//        }

        if(result.isSuccess()) {
            if (repository.update(agent)) {
                result.setPayload(agent);
            } else {
                String msg = String.format("agentId: %s, not found", agent.getAgentId());
                result.addMessage(msg, ResultType.NOT_FOUND);
            }
        }

        return result;
    }

    public boolean deleteById(int agentId) {
        return repository.deleteById(agentId);
    }

    private Result<Agent> validate(Agent agent, ValidationMode validationMode) {
        Result<Agent> result = new Result<>();
        if (agent == null) {
            result.addMessage("agent cannot be null", ResultType.INVALID);
        } else if(validationMode == ValidationMode.CREATE && agent.getAgentId() > 0) {
            result.addMessage("Agent Id Should not be set.", ResultType.INVALID);
        } else if(validationMode == ValidationMode.UPDATE && agent.getAgentId() <= 0) {
            result.addMessage("Agent id is required", ResultType.INVALID);
        }

        if(result.isSuccess()) {
            Set<ConstraintViolation<Agent>> violations = validator.validate(agent);

            if(!violations.isEmpty()) {
                for(ConstraintViolation<Agent> violation : violations) {
                    result.addMessage(violation.getMessage(), ResultType.INVALID);
                }
            }
        }
        return result;
    }
}
