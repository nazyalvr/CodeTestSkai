package skai.codetest.mvp.game;

import org.springframework.stereotype.Component;
import skai.codetest.mvp.exception.FileFormatException;
import skai.codetest.mvp.player.Player;

import java.util.Map;
import java.util.Set;

@Component
public interface Game {
    void parse(String[] cont) throws FileFormatException;
    Set<? extends Player> getPlayers();
    Map<String, Long> getPlayersGamePoints() throws FileFormatException;


}
