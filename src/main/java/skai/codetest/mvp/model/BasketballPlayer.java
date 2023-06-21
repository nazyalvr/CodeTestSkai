package skai.codetest.mvp.model;

import java.util.Objects;

public class BasketballPlayer extends Player {
    private Long pointsScored;
    private Long rebounds;
    private Long assists;

    public BasketballPlayer(String[] info) {
        this.setPlayerName(info[0]);
        this.setNickName(info[1]);
        this.setPlayerNumber(Long.parseLong(info[2]));
        this.setTeamName(info[3]);
        this.setPointsScored(Long.parseLong(info[4]));
        this.setRebounds(Long.parseLong(info[5]));
        this.setAssists(Long.parseLong(info[6]));
    }
    public Long getPointsScored() {
        return pointsScored;
    }

    public void setPointsScored(Long pointsScored) {
        this.pointsScored = pointsScored;
    }

    public Long getRebounds() {
        return rebounds;
    }

    public void setRebounds(Long rebounds) {
        this.rebounds = rebounds;
    }

    public Long getAssists() {
        return assists;
    }

    public void setAssists(Long assists) {
        this.assists = assists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketballPlayer that = (BasketballPlayer) o;
        return Objects.equals(pointsScored, that.pointsScored) && Objects.equals(rebounds, that.rebounds) && Objects.equals(assists, that.assists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointsScored, rebounds, assists);
    }
}
