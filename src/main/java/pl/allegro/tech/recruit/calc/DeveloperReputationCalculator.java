package pl.allegro.tech.recruit.calc;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class DeveloperReputationCalculator {
    private static final int FOLLOWERS_MULTIPLIER = 2;
    private static final int HACKTOBER_BONUS = 10;

    public int calculate(Developer dev, ZonedDateTime time) {
        int followersScore = getFollowersScore(dev);
        if (isDuringHacktober(time)) {
            return followersScore + HACKTOBER_BONUS;
        } else {
            return followersScore;
        }
    }

    public int getFollowersScore(Developer dev) {
        return Optional.ofNullable(dev.getFollowers())
                .map(List::size)
                .map(size -> size * FOLLOWERS_MULTIPLIER)
                .orElse(0);
    }

    public boolean isDuringHacktober(ZonedDateTime time) {
        return time.getMonth() == Month.OCTOBER;
    }
}
