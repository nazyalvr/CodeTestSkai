package skai.codetest.mvp.model;

import java.util.Objects;

public class HandballPlayer extends Player {
    private Long goalsMade;
    private Long goalsReceived;

    public HandballPlayer(String[] info) {
        this.setPlayerName(info[0]);
        this.setNickName(info[1]);
        this.setPlayerNumber(Long.parseLong(info[2]));
        this.setTeamName(info[3]);
        this.setGoalsMade(Long.parseLong(info[4]));
        this.setGoalsReceived(Long.parseLong(info[5]));
    }

    public Long getGoalsMade() {
        return goalsMade;
    }

    public void setGoalsMade(Long goalsMade) {
        this.goalsMade = goalsMade;
    }

    public Long getGoalsReceived() {
        return goalsReceived;
    }

    public void setGoalsReceived(Long goalsReceived) {
        this.goalsReceived = goalsReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandballPlayer that = (HandballPlayer) o;
        return Objects.equals(goalsMade, that.goalsMade) && Objects.equals(goalsReceived, that.goalsReceived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goalsMade, goalsReceived);
    }
}
