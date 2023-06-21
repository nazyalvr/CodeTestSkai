package skai.codetest.mvp.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import skai.codetest.mvp.FileFormatException;
import skai.codetest.mvp.HandballService;
import skai.codetest.mvp.model.HandballPlayer;

import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class HandballGame implements Game{
    private Set<HandballPlayer> players;
    @Override
    public void parse(String[] cont) throws FileFormatException {
        this.setPlayers(HandballService.parse(cont));
    }
    public void setPlayers(Set<HandballPlayer> players) {
        this.players = players;
    }

    @Override
    public Set<HandballPlayer> getPlayers() {
        return this.players;
    }

    @Override
    public Map<String, Long> getPlayersGamePoints() throws FileFormatException {
        return HandballService.getPlayersGamePoints(this);
    }
}
