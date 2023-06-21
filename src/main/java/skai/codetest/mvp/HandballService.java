package skai.codetest.mvp;

import org.springframework.stereotype.Service;
import skai.codetest.mvp.game.HandballGame;
import skai.codetest.mvp.model.HandballPlayer;
import skai.codetest.mvp.repository.TournamentRepository;

import java.util.*;

@Service
public class HandballService extends SportService{
    private TournamentRepository tournamentRepository;

    public HandballService(TournamentRepository tournamentRepository) {
        super(tournamentRepository);
    }

    public static Set<HandballPlayer> parse(String[] cont) throws FileFormatException {
        Set<HandballPlayer> tempSet = new HashSet<>();
        for (int i = 1; i < cont.length; ++i){
            String[] playerInfo = cont[i].split(";");
            if (playerInfo.length != 6){
                throw new FileFormatException("Invalid Data!");
            }
            tempSet.add(new HandballPlayer(playerInfo));
        }
        return tempSet;
    }

    public static String findGameWinner(HandballGame game) throws FileFormatException {
        String team = "";
        Long firstTeamGoals = 0L;
        Long secondTeamGoals;
        String teamWinner = "";
        for (HandballPlayer hp : game.getPlayers()) {
            if (team.equals("")) {
                firstTeamGoals = hp.getGoalsReceived();
                team = hp.getTeamName();
            } else {
                if (!team.equals(hp.getTeamName())) {
                    secondTeamGoals = hp.getGoalsReceived();
                    if (firstTeamGoals > secondTeamGoals) {
                        teamWinner =  hp.getTeamName();
                    } else {
                        teamWinner = team;
                    }
                    break;
                }
            }
        }
        if (!Objects.equals(teamWinner, "")){
            return teamWinner;
        } else {
            throw new FileFormatException("Can't get a winner team!");
        }
    }
    public static Map<String, Long> getPlayersGamePoints(HandballGame game) throws FileFormatException {
        String teamWinner = findGameWinner(game);
        Set<HandballPlayer> players = game.getPlayers();
        if (!players.isEmpty()){
            Map<String, Long> gamePoints = new HashMap<>();
            for (HandballPlayer hp: players){
                String nick = hp.getNickName();
                long points = 2L * hp.getGoalsMade() - hp.getGoalsReceived();
                if (hp.getTeamName().equals(teamWinner)){
                    points += 10L;
                }
                if (gamePoints.containsKey(nick)){
                    throw new FileFormatException("Nicknames are not unique!");
                } else{
                    gamePoints.put(nick, points);
                }
            }
            return gamePoints;
        }
        else {
            throw new FileFormatException("Invalid Data!");
        }
    }

}
