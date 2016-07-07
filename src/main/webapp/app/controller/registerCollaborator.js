angular.module('controllers')
    .controller('controllerRegisterCollaborator', ['$http', '$location', function ($http, $location) {
        var self = this;
        self.regex = {};
        self.isNewPersonalIdNumber = true;
        self.isFalseForm = false;
        self.isThereAnEmptyField = false;

        /*** Recupération des regex **/
        $http.get("api/collaborateurs/regex").then(function (data) {
            self.regex.personnalIdNumber = new RegExp(data.data.PERSONNAL_ID_NUMBER);
            self.regex.lastName = new RegExp(data.data.LAST_NAME);
            self.regex.firstName = new RegExp(data.data.FIRST_NAME);
        });

        self.isErrorInputMessageDisplayed = function (inputForm, focus) {
            return !inputForm.$error.required && inputForm.$invalid && !focus;
        };

        self.verifyForm = function (collaboratorForm) {
            if (collaboratorForm.$error.required) {
                self.isThereAnEmptyField = true;
                self.isFalseForm = false;
            }
            else if (collaboratorForm.$invalid) {
                self.isFalseForm = true;
                self.isThereAnEmptyField = false;
            }
            else {
                self.saveAction();
            }
        };

        self.saveAction = function () {
            //delete useless spaces between words 
            self.collaborator.lastName = self.collaborator.lastName.replace(/ +/g, " ");
            self.collaborator.firstName = self.collaborator.firstName.replace(/ +/g, " ");

            //post the form to the server
            $http.post("api/collaborateurs", self.collaborator).success(function (data) {
                console.log("self.collaborator: ",self.collaborator);
                if (data === "true" || data === true) {
                    self.isNewPersonalIdNumber = true;
                    $location.url('/pageblanche');
                }
                else self.isNewPersonalIdNumber = false;
            });
        }
    }])
    .directive('pwCheck', [function () {
        return {
            require: "ngModel",
            scope: {
                otherModelValue: "=pwCheck"
            },
            link: function (scope, element, attributes, ngModel) {
                ngModel.$validators.pwCheck = function (modelValue) {
                    return modelValue === scope.otherModelValue;
                };
                scope.$watch("otherModelValue", function () {
                    ngModel.$validate();
                });
            }
        }
    }])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/RegisterCollaborator', {
                templateUrl: 'templates/registerCollaborator.html',
                controller: 'controllerRegisterCollaborator',
                controllerAs: 'EC'
            })
    }
    ])
;
