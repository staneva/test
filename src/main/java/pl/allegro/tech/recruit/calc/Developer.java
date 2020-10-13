package pl.allegro.tech.recruit.calc;

import java.util.List;

public class Developer {
    private final String name;
    private final List<Developer> followers;

    public Developer(String name, List<Developer> followers) {
        this.name = name;
        this.followers = followers;
    }

    String getName() {
        return name;
    }

    public List<Developer> getFollowers() {
        return followers;
    }
}
