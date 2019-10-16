import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Gym {
    private String name;
    private List<GymMember> members;

    public Gym(String name, List<GymMember> members) {
        this.name = name;
        this.members = members;
    }


    public void searchForGymMemberByName() {
        gymMemberSearch(JOptionPane.showInputDialog("Vad heter kunden?"));
    }

    public void searchForGymMemberByPersonalNumber() {
        gymMemberSearch(JOptionPane.showInputDialog("Vad är kundens personnummer?"));
    }

    public void gymMemberSearch(String gymMemberInfo) {
        for (GymMember gymMember : getMembers()) {
            if ((gymMemberInfo.equalsIgnoreCase(gymMember.getName()) || gymMemberInfo.equalsIgnoreCase(gymMember.getPersonalNumber())) && isPayingGymMember(gymMember)) {
                JOptionPane.showMessageDialog(null, "Welcome " + gymMember.getName() + ", to the best gym ever!");
                IOUtil.writeToFileGymMembersVisits(gymMember);
                return;
            } else if ((gymMemberInfo.equalsIgnoreCase(gymMember.getName()) || gymMemberInfo.equalsIgnoreCase(gymMember.getPersonalNumber())) && !isPayingGymMember(gymMember)) {
                JOptionPane.showMessageDialog(null, "You havent paid the membership fee!");
                return;
            }
        }
            JOptionPane.showMessageDialog(null, "You are not a member at this gym!");
    }


    public static boolean isPayingGymMember(GymMember gymMember) {
        LocalDate dateOfLastPayment = gymMember.getDateOfLastPayment();
        LocalDate oneYearFromNow = LocalDate.now().minusYears(1);
        return oneYearFromNow.isBefore(dateOfLastPayment);
    }

    public List<GymMember> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }
}
