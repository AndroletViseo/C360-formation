describe('Enregistrement Collaborateur', function () {
    var ctrl;
    var backend;
    var loc;

    beforeEach(module('App'));

    beforeEach(inject(function ($controller, $httpBackend, $location) {
        backend = $httpBackend;
        loc = $location;
        ctrl = $controller('controllerRegisterCollaborator');
    }));

    describe('Test EnregistrementCollaborateur', function () {

        beforeEach(function () {
            backend.expectGET('api/collaborateurs/regex').respond('{"PERSONNAL_ID_NUMBER":"^[A-Z0-9]+$","LAST_NAME":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$","FIRST_NAME":"^[a-zA-Z-\'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ]+$"}');
            ctrl.collaborator = {};
            ctrl.collaborator.lastName = "Darmet";
            ctrl.collaborator.firstName = "Henri";
            ctrl.collaborator.personnalIdnumber = "BB554";
        });

        afterEach(function () {
            backend.verifyNoOutstandingExpectation();
            backend.verifyNoOutstandingRequest();
        });

        it('Valide', function () {
            ctrl.saveAction();
            backend.expectPOST('api/collaborateurs').respond('true');
            backend.flush();
            expect(ctrl.isNewPersonalIdNumber).toBeTruthy();
        });

        it('Invalide', function () {
            ctrl.saveAction();
            backend.expectPOST('api/collaborateurs').respond('false');
            backend.flush();
            expect(ctrl.isNewPersonalIdNumber).toBeFalsy();
            //expect(loc.path()).toEqual('#/EnregistrementCollaborateur');
        });
    });
});