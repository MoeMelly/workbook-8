import java.util.ArrayList;
import java.util.List;

public record Actor (int actorId, String firstName, String lastName) {
    public Actor(int actorId, String firstName, String lastName) {
        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
            throw new IllegalArgumentException("First and last name cannot be null nor Empty. Please type in the required fields.");
        }
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("First and last name fields cannot be blank.");
        }
        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

