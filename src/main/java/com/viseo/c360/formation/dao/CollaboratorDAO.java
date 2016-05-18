package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
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
    public void addCollaborator(Collaborator collaborator) {
        em.persist(collaborator);
    }

    public boolean isPersonnalIdNumberPersisted(String personnalIdNumber) {
        em.setFlushMode(FlushModeType.COMMIT);
        Collection<Collaborator> listCollaborator =
                (Collection<Collaborator>) em.createQuery(
                        "select c from Collaborator c where c.personnalIdNumber = :personnalIdNumber", Collaborator.class)
                        .setParameter("personnalIdNumber", personnalIdNumber).getResultList();
        return !listCollaborator.isEmpty();
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
    public void addRequestTraining(RequestTraining requestTraining) {
        em.persist(requestTraining);
    }

    @Transactional
    public void affectCollaboratorsTrainingSession(TrainingSession myTrainingSession, List<Collaborator> collaborators) {
        myTrainingSession = em.merge(myTrainingSession);
        for (Collaborator myCollaborator : collaborators) {
            myTrainingSession.addCollaborator(myCollaborator);
        }
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
}
