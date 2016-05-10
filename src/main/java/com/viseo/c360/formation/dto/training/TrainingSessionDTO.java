package com.viseo.c360.formation.dto.training;

import com.viseo.c360.formation.domain.collaborator.Collaborator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TrainingSessionDTO {
    @NotNull
    @Min(value = 1)
    long training;

    @NotNull
    @Pattern(regexp= "(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))*")
    String beginning;

    @NotNull
    @Pattern(regexp= "(((0[1-9]|[12]\\d|3[01])\\/(0[13578]|1[02])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((1[6-9]|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((1[6-9]|[2-9]\\d)\\d{2}))|(29\\/02\\/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))*")
    String ending;

    @NotNull
    @Pattern(regexp= "([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]*")
    String beginningTime;

    @NotNull
    @Pattern(regexp= "([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]*")
    String endingTime;

    @NotNull
    String location;

    public TrainingSessionDTO() {
    }
    public String getBeginning() {
        return beginning;
    }
    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }
    public String getBeginningTime() {
        return beginningTime;
    }
    public void setBeginningTime(String beginningTime) {
        this.beginningTime = beginningTime;
    }
    public String getEnding() {
        return ending;
    }
    public void setEnding(String ending) {
        this.ending = ending;
    }
    public String getEndingTime() {
        return endingTime;
    }
    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public long getTraining() {
        return training;
    }
    public void setTraining(long training) {
        this.training = training;
    }
}