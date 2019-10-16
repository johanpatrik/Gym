import java.time.LocalDate;
import java.util.Objects;

public class GymMember {
    private String personalNumber;
    private String name;
    private LocalDate dateOfLastPayment;
    private LocalDate dateOfLastVisit;

    public GymMember(String personalNumber, String name, LocalDate dateOfLastPayment) {
        this.personalNumber = personalNumber;
        this.name = name;
        this.dateOfLastPayment = dateOfLastPayment;
    }

    public GymMember(String personalNumber, LocalDate dateOfLastVisit, String name) {
        this.personalNumber = personalNumber;
        this.name = name;
        this.dateOfLastVisit = dateOfLastVisit;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    public LocalDate getDateOfLastVisit() {
        return dateOfLastVisit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GymMember)) return false;
        GymMember gymMember = (GymMember) o;
        return getPersonalNumber().equals(gymMember.getPersonalNumber()) &&
                getName().equals(gymMember.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonalNumber(), getName());
    }
}
