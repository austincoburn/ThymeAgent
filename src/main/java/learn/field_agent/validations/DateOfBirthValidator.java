package learn.field_agent.validations;

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

        if(date == null) {
            return true;
        }
        return date.isBefore(LocalDate.now().minusYears(12));
    }
}
