package skai.codetest.mvp.player;

import org.springframework.stereotype.Component;

@Component
public class Player {
    private String playerName;
    private String nickName;
    private Long playerNumber;
    private String teamName;

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPlayerNumber(Long playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getPlayerNumber() {
        return playerNumber;
    }

    public String getPlayerName() {
        return playerName;
    }
}
