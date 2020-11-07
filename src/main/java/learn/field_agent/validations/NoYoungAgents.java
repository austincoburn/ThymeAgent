package learn.field_agent.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {DateOfBirthValidator.class})
@Documented
public @interface NoYoungAgents {
    String message() default "{Agent must be older than 12}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};

}
