package com.viseo.c360.formation.converters.collaborator;


import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;

import java.util.ArrayList;
import java.util.List;

public class DescriptionToCollaborator {

    public DescriptionToCollaborator() {
    }

    public Collaborator convert(CollaboratorDescription dto) {
        Collaborator domain = new Collaborator();
        if (dto.getId() > 0) domain.setId(dto.getId());
        domain.setFirstName(dto.getFirstName());
        domain.setLastName(dto.getLastName());
        domain.setPersonnalIdNumber(dto.getPersonnalIdNumber());
        domain.setEmail(dto.getEmail());
        domain.setPassword(dto.getPassword());
        domain.setIsAdmin(dto.getIsAdmin());
        return domain;
    }

    public List<Collaborator> convert(List<CollaboratorDescription> sourceList) {
        List<Collaborator> listCollaborator = new ArrayList<>();
        for (CollaboratorDescription collaboratorDescription : sourceList) {
            listCollaborator.add(convert(collaboratorDescription));
        }
        return listCollaborator;
    }
}