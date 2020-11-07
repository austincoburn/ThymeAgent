package learn.field_agent.validations;

import learn.field_agent.models.Agent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<NoYoungAgents, LocalDate> {
    @Override
    public void initialize(NoYoungAgents constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        return date.isAfter(LocalDate.now().minusYears(12));
    }
}
