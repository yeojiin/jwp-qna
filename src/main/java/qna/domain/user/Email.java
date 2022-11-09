package qna.domain.user;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static qna.constant.Message.NOT_VALID_EMAIL;
import static qna.constant.Message.NOT_VALID_EMPTY;

@Embeddable
public class Email {
    @Column(name = "email", length = 50)
    private String email;

    public Email (String email) {
        validateEmail(email);
        this.email = email;
    }

    public Email() {
    }

    public static Email of(String email) {
        return new Email(email);
    }

    private void validateEmail(String email) {
        if(email.isEmpty()) {
            throw new IllegalArgumentException(NOT_VALID_EMPTY + "[이메일]");
        }

        String emailRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";

        if(!Pattern.matches(emailRegex,email)) {
         throw new IllegalArgumentException(NOT_VALID_EMAIL);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
