package skai.codetest.mvp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import skai.codetest.mvp.game.BasketballGame;
import skai.codetest.mvp.model.BasketballPlayer;
import skai.codetest.mvp.repository.TournamentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BasketballServiceTest {
    @Test
    void findGameWinner() throws FileFormatException {
        Set<BasketballPlayer> playerList = Stream.of(
                "Vasya;p1;1;team1;5;2;1",
                "Petya;p2;1;team1;7;2;1",
                "Jora;p3;1;team2;10;2;1",
                "Katya;p4;1;team2;1;2;1").map(x -> new BasketballPlayer(x.split(";"))).collect(Collectors.toSet());
        BasketballGame bg = new BasketballGame(playerList);
        assertEquals(BasketballService.findGameWinner(bg), "team1");
    }

    @Test
    void getPlayersGamePoints() throws FileFormatException {
        Set<BasketballPlayer> playerList = Stream.of(
                "Vasya;p1;1;team1;5;2;1",
                "Petya;p2;1;team1;7;2;1",
                "Jora;p3;1;team2;10;2;1",
                "Katya;p4;1;team2;1;2;1").map(x -> new BasketballPlayer(x.split(";"))).collect(Collectors.toSet());
        BasketballGame bg = new BasketballGame(playerList);
        Map<String, Long> gameRes = Map.ofEntries(Map.entry("p1", 23L), Map.entry("p2", 27L), Map.entry("p3", 23L), Map.entry("p4", 5L));
        assertEquals(gameRes, BasketballService.getPlayersGamePoints(bg));
    }

    @Test
    void parse() throws FileFormatException {
        String[] cont = new String[]{"BASKETBALL", "player 1;nick1;4;Team A;10;2;0", "player 2;nick2;8;Team B;5;10;0"};
        BasketballGame bg = new BasketballGame(new HashSet<>());
        bg.parse(cont);

        Set<BasketballPlayer> players = Stream.of("player 1;nick1;4;Team A;10;2;0",
                "player 2;nick2;8;Team B;5;10;0").map(x -> new BasketballPlayer(x.split(";"))).collect(Collectors.toSet());
        assertEquals(players, bg.getPlayers());
    }

    @Test
    void testExpectedException() {

        FileFormatException thrown = Assertions.assertThrows(FileFormatException.class, () -> {
            Set<BasketballPlayer> playerList = Stream.of(
                    "Vasya;p1;1;team1;5;2;1",
                    "Petya;p2;1;team1;7;2;1",
                    "Jora;p3;1;team2;10;2;1",
                    "Katya;p4;1;team3;1;2;1").map(x -> new BasketballPlayer(x.split(";"))).collect(Collectors.toSet());
            BasketballGame bg = new BasketballGame(playerList);
            BasketballService.findGameWinner(bg);
        });

        Assertions.assertEquals("Can't be 3 or more different teams in one game!", thrown.getMessage());

        thrown = Assertions.assertThrows(FileFormatException.class, () -> {
            Set<BasketballPlayer> playerList = Stream.of(
                    "Vasya;p1;1;team1;5;2;1",
                    "Petya;p2;1;team1;5;2;1",
                    "Jora;p1;1;team2;10;2;1",
                    "Katya;p4;1;team2;0;2;1").map(x -> new BasketballPlayer(x.split(";"))).collect(Collectors.toSet());
            BasketballGame bg = new BasketballGame(playerList);
            BasketballService.getPlayersGamePoints(bg);
        });
        Assertions.assertEquals("Nicknames are not unique!", thrown.getMessage());
    }

}