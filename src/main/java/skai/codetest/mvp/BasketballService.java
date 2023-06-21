package skai.codetest.mvp;

import org.springframework.stereotype.Service;
import skai.codetest.mvp.game.BasketballGame;
import skai.codetest.mvp.model.BasketballPlayer;
import skai.codetest.mvp.repository.TournamentRepository;

import java.util.*;

@Service
public class BasketballService extends SportService{
    private TournamentRepository tournamentRepository;


    public BasketballService(TournamentRepository tournamentRepository) {

        super(tournamentRepository);
    }

    public static Set<BasketballPlayer> parse(String[] cont) throws FileFormatException {
        Set<BasketballPlayer> tempSet = new HashSet<>();
        for (int i = 1; i < cont.length; ++i){
            String[] playerInfo = cont[i].split(";");
            if (playerInfo.length != 7){
                throw new FileFormatException("Invalid Data!");
            }
            tempSet.add(new BasketballPlayer(playerInfo));
        }
        return tempSet;
    }

    public static String findGameWinner(BasketballGame game) throws FileFormatException {
        String firstTeam = "";
        String secondTeam = "";
        Long firstTeamGoals = 0L;
        Long secondTeamGoals = 0L;
        for (BasketballPlayer bp : game.getPlayers()){
            if (Objects.equals(firstTeam, "")){
                firstTeam = bp.getTeamName();
            }
            else {
                if (Objects.equals(secondTeam, "")){
                    if (!bp.getTeamName().equals(firstTeam)){
                        secondTeam = bp.getTeamName();
                    }
                } else{
                    if (!bp.getTeamName().equals(firstTeam) && !bp.getTeamName().equals(secondTeam)){
                        throw new FileFormatException("Can't be 3 or more different teams in one game!");
                    }
                }

            }
            if (bp.getTeamName().equals(firstTeam)){
                firstTeamGoals += bp.getPointsScored();
            } else{
                secondTeamGoals += bp.getPointsScored();
            }
        }
        if (firstTeamGoals.equals(secondTeamGoals)){
            throw new FileFormatException("Can't find game winner!");
        } else{
            if (firstTeamGoals > secondTeamGoals){
                return firstTeam;
            } else{
                return secondTeam;
            }
        }
    }

    public static Map<String, Long> getPlayersGamePoints(BasketballGame game) throws FileFormatException {
        String teamWinner = findGameWinner(game);
        Set<BasketballPlayer> players = game.getPlayers();
        if (!players.isEmpty()){
            Map<String, Long> gamePoints = new HashMap<>();
            for (BasketballPlayer bp: players){
                String nick = bp.getNickName();
                long points = 2L * bp.getPointsScored() + bp.getRebounds() + bp.getAssists();
                if (bp.getTeamName().equals(teamWinner)){
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
