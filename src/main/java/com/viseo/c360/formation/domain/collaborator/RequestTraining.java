package com.viseo.c360.formation.domain.collaborator;

import com.viseo.c360.formation.domain.BaseEntity;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class RequestTraining extends BaseEntity {

    @NotNull
    @Valid
    @ManyToOne
    Training training;

    @NotNull
    @Valid
    @ManyToOne
    Collaborator collaborator;

    @NotNull
    @Valid
    @ManyToMany
    List<TrainingSession> listSession;

    public RequestTraining() {
        super();
        this.listSession = new ArrayList<>();
    }

    public Collaborator getCollaborator() { return collaborator; }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<TrainingSession> getListSession() {
        return Collections.unmodifiableList(listSession);
    }

    public void addListSession(TrainingSession trainingSession) {
        this.listSession.add(trainingSession);
    }

    public void removeListSession(TrainingSession trainingSession) {
        this.listSession.remove(trainingSession);
    }
}
