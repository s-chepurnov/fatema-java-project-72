package hexlet.code.dto;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BuildUrlPage extends BasePage {
    private String name;
    private Map<String, List<ValidationError<Object>>> errors;


}
