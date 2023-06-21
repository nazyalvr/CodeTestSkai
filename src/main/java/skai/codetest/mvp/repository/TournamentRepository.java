package skai.codetest.mvp.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class TournamentRepository {
    private final Map<String, Long> pointsForNicks = new HashMap<>();

    public Long getPointsForNick (String nickname){
        Long points = pointsForNicks.get(nickname);
        return Objects.requireNonNullElse(points, 0L);
    }

    public void updateResults(Map<String, Long> gameResults){
        for (String s : gameResults.keySet()){
            Long temp = getPointsForNick(s);
            pointsForNicks.put(s, gameResults.get(s) + temp);
        }
    }

    public Map.Entry<String, Long> getMvpPlayer(){
        Long maxPoints = 0L;
        String mvpPlayer="";
        for (String nick : this.pointsForNicks.keySet()) {
            if (pointsForNicks.get(nick).equals(maxPoints)) {
                mvpPlayer = "";
            } else {
                if (pointsForNicks.get(nick) > maxPoints) {
                    maxPoints = pointsForNicks.get(nick);
                    mvpPlayer = nick;
                }
            }
        }
        return Map.entry(mvpPlayer, maxPoints);
    }

}
