package main.query;

import fileio.ActorInputData;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForActorsFilterDescription {
    /**
     * Gaseste un cuvant intr-un text:
     * Initial am luat cateva cazuri particulare foarte probabile
     * Dupa care, m-am folosit de Matcher si Pattern
     */
    public Boolean isMatch(final String p, final String description) {
        String p1 = p;
        Boolean found1 = Arrays.asList(description.split(" ")).contains(p1);
        if (found1) {
            return true;
        }

        String p2 = p + ",";
        Boolean found2 = Arrays.asList(description.split(" ")).contains(p2);
        if (found2) {
            return true;
        }

        String p3 = p + ".";
        Boolean found3 = Arrays.asList(description.split(" ")).contains(p3);
        if (found3) {
            return true;
        }

        String p4 = "(" + p + ")";
        Boolean found4 = Arrays.asList(description.split(" ")).contains(p4);
        if (found4) {
            return true;
        }

        Pattern pattern = Pattern.compile("-" + p, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(description);
        boolean matchFound = matcher.find();
        if (matchFound) {
            return true;
        }
        return false;
    }

    /**
     * @param sortType
     * @param filters
     * @param actors
     * @return
     */
    public String queryFilterDescription(final String sortType,
                                         final List<List<String>> filters,
                                         final List<ActorInputData> actors) {
        List<String> words = filters.get(2);
        List<ActorInputData> goodActors = new ArrayList<>();
        String mess = "Query result: [";
        for (ActorInputData actor : actors) {
            int contor = 0;
            String description = actor.getCareerDescription().toLowerCase();
            for (int i = 0; i < words.size(); i++) {
                if (isMatch(words.get(i), description)) {
                    contor++;
                }
            }
            if (contor == words.size()) {
                goodActors.add(actor);
            }
        }
        if (sortType.equals("asc")) {
            goodActors.sort(ActorInputData.getUpName());
        } else {
            goodActors.sort(ActorInputData.getDownName());
        }
        for (int i = 0; i < goodActors.size(); i++) {
            if (i == goodActors.size() - 1) {
                mess = mess + goodActors.get(i).getName() + "]";
                return mess;
            } else {
                mess = mess + goodActors.get(i).getName() + ", ";
            }
        }
        return mess + "]";
    }
}
