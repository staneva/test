package pl.allegro.tech.recruit.calc

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.ZonedDateTime

class DeveloperReputationCalculatorSpec extends Specification {

    @Subject
    DeveloperReputationCalculator calculator = new DeveloperReputationCalculator()

    @Unroll
    def "check calculation method"() {
        expect:
        calculator.calculate(developer, time) == result
        where:
        developer                                       | time                                             | result
        developer()                                     | ZonedDateTime.parse("2020-10-03T10:15:30+01:00") | 10
        new Developer(null, [developer()])              | ZonedDateTime.parse("2020-10-03T10:15:30+01:00") | 12
        new Developer(null, [developer(), developer()]) | ZonedDateTime.parse("2020-10-03T10:15:30+01:00") | 14
        developer()                                     | ZonedDateTime.parse("2020-11-03T10:15:30+01:00") | 0
        new Developer(null, [developer()])              | ZonedDateTime.parse("2020-11-03T10:15:30+01:00") | 2
        new Developer(null, [developer(), developer()]) | ZonedDateTime.parse("2020-11-03T10:15:30+01:00") | 4
    }

    @Unroll
    def "check getFollowersScore method"() {
        expect:
        calculator.getFollowersScore(developer) == result
        where:
        developer                                       | result
        new Developer(null, [developer(), developer()]) | 4
        new Developer(null, [developer()])              | 2
        developer()                                     | 0
    }

    @Unroll
    def "check isDuringHacktober method"() {
        expect:
        calculator.isDuringHacktober(time) == result
        where:
        time                                             | result
        ZonedDateTime.parse("2020-10-03T10:15:30+01:00") | true
        ZonedDateTime.parse("2020-12-03T10:15:30+01:00") | false
    }

    private static Developer developer() {
        return new Developer(null, null)
    }
}
