package skai.codetest.mvp.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import skai.codetest.mvp.BasketballService;
import skai.codetest.mvp.FileFormatException;
import skai.codetest.mvp.model.BasketballPlayer;

import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class BasketballGame implements Game{
    private Set<BasketballPlayer> players;

    @Override
    public void parse(String[] cont) throws FileFormatException {
        this.setPlayers(BasketballService.parse(cont));
    }
    private void setPlayers(Set<BasketballPlayer> players) {
        this.players = players;
    }

    @Override
    public Set<BasketballPlayer> getPlayers() {
        return players;
    }

    @Override
    public Map<String, Long> getPlayersGamePoints() throws FileFormatException {
        return BasketballService.getPlayersGamePoints(this);
    }
}
