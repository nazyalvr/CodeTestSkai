package skai.codetest.mvp;

import org.junit.jupiter.api.Test;
import skai.codetest.mvp.game.BasketballGame;
import skai.codetest.mvp.game.HandballGame;
import skai.codetest.mvp.model.BasketballPlayer;
import skai.codetest.mvp.model.HandballPlayer;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandballServiceTest {
    @Test
    void findGameWinner() throws FileFormatException {
        Set<HandballPlayer> playerList = Stream.of(
                "Vasya;p1;1;team1;15;25",
                "Petya;p2;1;team1;5;25",
                "Jora;p3;1;team2;10;20",
                "Katya;p4;1;team2;15;20").map(x -> new HandballPlayer(x.split(";"))).collect(Collectors.toSet());

        Set<HandballPlayer> playerList2 = Stream.of(
                "player 1;nick1;4;Team A;0;20",
                "player 2;nick2;8;Team A;15;20",
                "player 3;nick3;15;Team A;10;20",
                "player 4;nick4;16;Team B;0;25",
                "player 5;nick5;23;Team B;12;25",
                "player 6;nick6;42;Team B;8;25").map(x -> new HandballPlayer(x.split(";"))).collect(Collectors.toSet());

        {
            HandballGame hg = new HandballGame(playerList);
            assertEquals(HandballService.findGameWinner(hg), "team2");
        }

        {
            HandballGame hg = new HandballGame(playerList2);
            assertEquals(HandballService.findGameWinner(hg), "Team A");
        }

    }

    @Test
    void getPlayersGamePoints() throws FileFormatException {
        Set<HandballPlayer> playerList = Stream.of(
                "Vasya;p1;1;team1;5;20",
                "Petya;p2;1;team1;10;20",
                "Jora;p3;1;team2;9;15",
                "Katya;p4;1;team2;11;15").map(x -> new HandballPlayer(x.split(";"))).collect(Collectors.toSet());
        HandballGame hg = new HandballGame(playerList);
        Map<String, Long> gameRes = Map.ofEntries(Map.entry("p1", -10L), Map.entry("p2", 0L), Map.entry("p3", 13L), Map.entry("p4", 17L));
        Set<HandballPlayer> playerList2 = Stream.of(
                "player 1;nick1;4;Team A;0;20",
                "player 2;nick2;8;Team A;15;20",
                "player 3;nick3;15;Team A;10;20",
                "player 4;nick4;16;Team B;0;25",
                "player 5;nick5;23;Team B;12;25",
                "player 6;nick6;42;Team B;8;25").map(x -> new HandballPlayer(x.split(";"))).collect(Collectors.toSet());


        assertEquals(gameRes, HandballService.getPlayersGamePoints(hg));

        HandballGame hg1 = new HandballGame(playerList2);
        Map<String, Long> gameRes1 = Map.ofEntries(
                Map.entry("nick1", -10L),
                Map.entry("nick2", 20L),
                Map.entry("nick3", 10L),
                Map.entry("nick4", -25L),
                Map.entry("nick5", -1L),
                Map.entry("nick6", -9L));

        assertEquals(gameRes1, HandballService.getPlayersGamePoints(hg1));
    }

    @Test
    void parse() throws FileFormatException {
        String[] cont = new String[]{"HANDBALL", "player 1;nick1;4;Team A;10;2", "player 2;nick2;8;Team B;2;10"};
        HandballGame hg = new HandballGame(new HashSet<>());
        hg.parse(cont);

        Set<HandballPlayer> players = Stream.of("player 1;nick1;4;Team A;10;2",
                "player 2;nick2;8;Team B;2;10").map(x -> new HandballPlayer(x.split(";"))).collect(Collectors.toSet());
        assertEquals(players, hg.getPlayers());
    }

}