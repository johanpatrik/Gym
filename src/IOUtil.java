import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOUtil {

    private static Path tracking_gym_members = Paths.get("src/TrackingGymMembers.txt");
    private static Path path_of_gym_members = Paths.get("src/GymMembers.txt");
    private static BufferedWriter bufferedWriter;

    public static List<GymMember> writeToListGymMemberDateOfLastPayment() {
        return fetchMembersFromFileToArrayList(false, path_of_gym_members);
    }

    public static List<GymMember> writeToListGymMemberVisits() {
        return fetchMembersFromFileToArrayList(true, tracking_gym_members);
    }

    public static List<GymMember> fetchMembersFromFileToArrayList(boolean isForPersonalTrainer, Path path) {
        String firstLine;
        String secondLine;
        List<GymMember> gymMembers = new ArrayList<>();

        try (
                BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            while ((firstLine = bufferedReader.readLine()) != null) {
                secondLine = bufferedReader.readLine();

                String[] firstLineArray = firstLine.split(",");

                if (isForPersonalTrainer) {
                    LocalDate dateOfLastVisit = LocalDate.parse(secondLine);
                    gymMembers.add(new GymMember(firstLineArray[0], dateOfLastVisit, firstLineArray[1].trim()));
                } else {
                    LocalDate dateOfLastPayment = LocalDate.parse(secondLine);
                    gymMembers.add(new GymMember(firstLineArray[0], firstLineArray[1].trim(), dateOfLastPayment));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("File " + path.getFileName() + " is not found.");
        }
        return gymMembers;
    }


    public static void writeToFileGymMembersVisits(GymMember gymMember) {
        if (bufferedWriter == null) {
            try {
                bufferedWriter = Files.newBufferedWriter(tracking_gym_members);
            } catch (IOException e) {
                throw new RuntimeException("Failed to instantiate BufferedWriter");
            }
        }

        try {
            bufferedWriter.write(gymMember.getPersonalNumber() + ", " + gymMember.getName() + "\n" + LocalDate.now() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file: " + tracking_gym_members.getFileName());
        } finally {
            try {
                bufferedWriter.flush();
            } catch (IOException e) {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    throw new RuntimeException("Failed to close file: " + tracking_gym_members.getFileName());
                }
            }
        }
    }

    public static void setTracking_gym_members(Path tracking_gym_members) {
        IOUtil.tracking_gym_members = tracking_gym_members;
    }

    public static void setPath_of_gym_members(Path path_of_gym_members) {
        IOUtil.path_of_gym_members = path_of_gym_members;
    }
}