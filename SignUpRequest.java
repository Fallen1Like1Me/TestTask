package registr;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @Size(min = 1, max = 20, message = "имя должно содержать от 1 до 20 символов")
    private String userName;

    @NotBlank(message = "адрес почты не может быть пустым")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @NotBlank(message = "пароль не может быть пустым")
    private String password;
}
