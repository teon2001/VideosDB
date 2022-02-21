package main.query;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ForActorsAwards {
    /**
     * Verifica daca un actor are toate premiile cerurte in filtre
     */
    public int hasAll(final ActorInputData actor,
                      final List<String> filters) {
        Map<ActorsAwards, Integer> map = actor.getAwards();
        int contor = 0;
        int sum = 0;
        for (Map.Entry<ActorsAwards, Integer> entry : map.entrySet()) {
            ActorsAwards curr = entry.getKey();
            for (String filter : filters) {
                if (curr.toString().equals(filter)) {
                    contor++;
                    sum = sum + entry.getValue();
                    break;
                }
            }
        }
        if (contor == filters.size()) {
            return sum;
        }
        return 0;
    }

    /**
     * Calculeaza numarul de premii totale per actor
     */
    public int allAwards(final ActorInputData actor) {
        Map<ActorsAwards, Integer> map = actor.getAwards();
        int sum = 0;
        for (Map.Entry<ActorsAwards, Integer> entry : map.entrySet()) {
            sum = sum + entry.getValue();
        }
        return sum;
    }

    /**
     * @param actors o lista de cu toti actorii
     * @param filters
     * @param sortType
     * @return
     */
    public String queryActorsAwards(final List<ActorInputData> actors,
                                    final List<List<String>> filters,
                                    final String sortType) {
        String mess = "Query result: [";
        List<String> awards = filters.get(filters.size() - 1);

        ArrayList<ActorForAwards> list = new ArrayList<>();
        for (ActorInputData a : actors) {
            if (hasAll(a, awards) != 0) {
                int sum = allAwards(a);
                ActorForAwards aFA = new ActorForAwards();
                aFA.setName(a.getName());
                aFA.setNoAwards(sum);
                aFA.setHasAllAwards(1);
                list.add(aFA);
            }
        }

        if (sortType.equals("asc")) {
            list.sort(ActorForAwards.getUp().thenComparing(ActorForAwards.getUpName()));
        } else {
            list.sort(ActorForAwards.getDown().thenComparing(ActorForAwards.getDownName()));
        }

        for(ActorForAwards a: list) {
            //System.out.println("a: "+a.getName() + " premii: "+ a.getNoAwards());
        }

        int contor = 0;
        for (ActorForAwards iter : list) {
            contor++;
            if (contor == list.size()) {
                mess = mess + iter.getName();
                return mess + "]";
            } else {
                mess = mess + iter.getName() + ", ";
            }
        }
        return mess + "]";
    }
}
