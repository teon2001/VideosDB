package main.query;

import java.util.Comparator;

public class ActorForAwards {
    private String name;
    private int hasAllAwards;
    private int noAwards;
    /**
     * intoarce 1 sau 0 daca are toate premiile cerute
     */
    public int getHasAllAwards() {
        return hasAllAwards;
    }
    /**
     * seteaza 1 sau 0
     */
    public void setHasAllAwards(final int hasAllAwards) {
        this.hasAllAwards = hasAllAwards;
    }
    /**
     * intoarce numarul total de premii
     */
    public int getNoAwards() {
        return noAwards;
    }
    /**
     * seteaza numarul de premii
     */
    public void setNoAwards(final int noAwards) {
        this.noAwards = noAwards;
    }
    /**
     * intoarce numele actorului
     */
    public String getName() {
        return name;
    }
    /**
     * Seteaza numele actorului
     */
    public void setName(final String name) {
        this.name = name;
    }

    private static Comparator<ActorForAwards> up = new Comparator<ActorForAwards>() {
        @Override
        public int compare(final ActorForAwards o1, final ActorForAwards o2) {
            return Integer.compare(o1.getNoAwards(), o2.getNoAwards());
        }
    };

    private static Comparator<ActorForAwards> down = new Comparator<ActorForAwards>() {
        @Override
        public int compare(final ActorForAwards o1, final ActorForAwards o2) {
            return Double.compare(o2.getNoAwards(), o1.getNoAwards());
        }
    };

    private static Comparator<ActorForAwards> upName = new Comparator<ActorForAwards>() {
        @Override
        public int compare(final ActorForAwards o1, final ActorForAwards o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    private static Comparator<ActorForAwards> downName = new Comparator<ActorForAwards>() {
        @Override
        public int compare(final ActorForAwards o1, final ActorForAwards o2) {
            return o2.getName().compareTo(o1.getName());
        }
    };

    public static Comparator<ActorForAwards> getUp() {
        return up;
    }

    public static Comparator<ActorForAwards> getDown() {
        return down;
    }

    public static Comparator<ActorForAwards> getUpName() {
        return upName;
    }

    public static Comparator<ActorForAwards> getDownName() {
        return downName;
    }
}
