import javax.swing.*;

public class Main {
    private static Gym gym = new Gym("Best Gym Ever", IOUtil.writeToListGymMemberDateOfLastPayment());

    public static void main(String[] args) {

        while (true) {
            String input = JOptionPane.showInputDialog(null,
                    "0: Avsluta programmet\n" +
                            "1: Namn sökning\n" +
                            "2: Personnummer sökning", gym.getName(), JOptionPane.INFORMATION_MESSAGE);

            if (input == null) {
                instructions(0);
            }
            try {
                instructions(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Only numbers allowed!");
            }
        }
    }

    public static void instructions(Integer number) {

        switch (number) {
            case 0:
                JOptionPane.showMessageDialog(null, "We hope to see you later! :)");
                System.exit(0);
            case 1:
                gym.searchForGymMemberByName();
                break;
            case 2:
                gym.searchForGymMemberByPersonalNumber();
                break;
        }
    }
}
