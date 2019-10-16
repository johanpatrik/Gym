import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IOUtilTest {

    private static Path tracking_gym_members_test = Paths.get("Test/TrackingGymMembersTest.txt");
    private static Gym gym;


    @BeforeEach
    public void setup() {
        List<GymMember> gymMembers = Arrays.asList(
                new GymMember("1231242151", "Kalle Karlson", LocalDate.now().minusYears(1))
        );
        gym = new Gym("Best Gym Ever!", gymMembers);
        IOUtil.setPath_of_gym_members(tracking_gym_members_test);
        IOUtil.setTracking_gym_members(tracking_gym_members_test);
    }

    @Test
    public void trackingGymMembersFileShouldHaveExpectedValues() {
        IOUtil.writeToFileGymMembersVisits(gym.getMembers().get(0));
        GymMember expected = gym.getMembers().get(0);
        GymMember actual = IOUtil.writeToListGymMemberVisits().get(0);

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPersonalNumber(), actual.getPersonalNumber());
        assertEquals(LocalDate.now(), actual.getDateOfLastVisit());
    }


    @Test
    public void fileNotFoundTest() {
        Path path = Paths.get("123124215214");
        assertThrows(RuntimeException.class, () -> IOUtil.fetchMembersFromFileToArrayList(true, path));
        assertThrows(RuntimeException.class, () -> IOUtil.fetchMembersFromFileToArrayList(false, path));

    }

}