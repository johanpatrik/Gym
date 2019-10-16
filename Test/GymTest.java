import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GymTest {

    private static Gym gym;

    @BeforeAll
    public static void setup() {
        List<GymMember> gymMembers = Arrays.asList(
                new GymMember("1231242151", "Kalle Karlson", LocalDate.now().minusYears(1).plusDays(1)),
                new GymMember("1231241313", "Peter Svensson", LocalDate.now().minusYears(1))
        );
        gym = new Gym("Best Gym Ever!", gymMembers);


    }

    @Test
    void isPayingGymMember() {
        GymMember gymMember = gym.getMembers().get(0);
        boolean isPayingGymMember = Gym.isPayingGymMember(gymMember);
        assertTrue(isPayingGymMember);
    }

    @Test
    void isNotPayingGymMember() {
        GymMember gymMember = gym.getMembers().get(1);
        boolean isPayingGymMember = Gym.isPayingGymMember(gymMember);
        assertFalse(isPayingGymMember);
    }
}