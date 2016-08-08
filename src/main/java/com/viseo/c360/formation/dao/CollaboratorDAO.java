package com.viseo.c360.formation.dao;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.CollaboratorIdentity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;

@Repository
public class CollaboratorDAO {

    @PersistenceContext
    EntityManager em;

    //collaborateur
    @Transactional
    public Collaborator addCollaborator(Collaborator collaborator) throws PersistenceException {
            em.persist(collaborator);
            em.flush();
            return collaborator;
    }

    public Collaborator getCollaboratorByLoginPassword(String personnalEmail,String personnalPassword){
        em.setFlushMode(FlushModeType.COMMIT);
        Collaborator registredUser =
                (Collaborator) em.createQuery(
                        "select c from Collaborator c where c.email = :personnalEmail and c.password = :personnalPassword",Collaborator.class)
                    .setParameter("personnalEmail",personnalEmail).setParameter("personnalPassword",personnalPassword)
                    .getSingleResult();
        return registredUser;
    }

    public List<Collaborator> getAllCollaborators() {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.createQuery("select c from Collaborator c", Collaborator.class).getResultList();
    }

    public Collaborator getCollaborator(long id) {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.find(Collaborator.class, id);
    }

    //request training
    @Transactional
    public RequestTraining addRequestTraining(RequestTraining requestTraining) throws PersistenceException {
        em.persist(requestTraining);
        em.flush();
        return requestTraining;
    }


    @Transactional
    public TrainingSession affectCollaboratorsTrainingSession(TrainingSession trainingSession, List<CollaboratorIdentity> collaboratorIdentities)
            throws PersistenceException
    {
        trainingSession = em.merge(trainingSession);
        trainingSession.removeCollaborators();
        for (CollaboratorIdentity collaboratorIdentity : collaboratorIdentities) {
            trainingSession.addCollaborator(em.find(Collaborator.class, collaboratorIdentity.getId()));
        }
        em.flush();
        return trainingSession;
    }

    public List<Collaborator> getNotAffectedCollaborators(TrainingSession myTrainingSession) {
        em.setFlushMode(FlushModeType.COMMIT);
        if (!myTrainingSession.getCollaborators().isEmpty()) {
            List<Collaborator> listCollaborator = (List<Collaborator>) em.createQuery(
                    "select c from Collaborator c where c NOT IN :listCollaborators", Collaborator.class)
                    .setParameter("listCollaborators", myTrainingSession.getCollaborators()).getResultList();
            return listCollaborator;
        } else {
            List<Collaborator> listCollaborator = (List<Collaborator>) em.createQuery(
                    "select c from Collaborator c", Collaborator.class).getResultList();
            return listCollaborator;
        }
    }

    public List<Collaborator> getCollaboratorsRequestingBySession(TrainingSession myTrainnigSession) {
        em.setFlushMode(FlushModeType.COMMIT);
        Set<Collaborator> listCollaborator = new HashSet<Collaborator>(em.createQuery(
                "select c from RequestTraining r Inner Join r.  collaborator c Inner Join r.listSession s Where s = :session")
                .setParameter("session", myTrainnigSession).getResultList());
        listCollaborator.addAll(em.createQuery(
                "select c from RequestTraining r Inner Join r.collaborator c Where r.training = :training")
                .setParameter("training", myTrainnigSession.getTraining()).getResultList());
        listCollaborator.removeAll(em.createQuery(
                "select c from TrainingSession s Inner Join s.collaborators c Where s.training = :training")
                .setParameter("training", myTrainnigSession.getTraining()).getResultList());
        return new ArrayList<>(listCollaborator);
    }
}

