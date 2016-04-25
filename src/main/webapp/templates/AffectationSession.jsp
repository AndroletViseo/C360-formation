<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.viseo.c360.formation.domain.formation.Formation" %>
<%! 
    String regexnombredemijournee = "\"/^"+Formation.regexNombreDemiJournee+"+$/\""; 
    String regexTitredelaformation = "\"/^"+Formation.regexTitreFormation+"+$/\""; 
%>

<div class="row">
  
  <div class="col-md-4">
  </div>
  
  <div class="col-md-7">
    <div class="panel panel-default">
    
          <div class="panel-heading">
            <h3 class="panel-title">Affectation Session</h3>
          </div>
          
          <div class="panel-body"> 
	          <h5><span class="label label-default">Sessions:</span><h5>
	          	
	          	<input ng-model="SessionSelectionnee" title="Selectionner une session" class="filter form-control filter-unselected" list="SessionDataListFilter" placeholder="Sélectionner une session ..." style="margin-bottom: 5px;" autofocus>
	          	<datalist id="SessionDataListFilter" style="height: 200px; width: 100%;">  
	                <option data-ng-repeat="SessionHTML in listDesSessionsDisponibles" value="{{SessionHTML.version+'-'+SessionHTML.name+'-'+SessionHTML.debut}}"></option> 
	             </datalist>

	          <hr>
	          <h5><input type="checkbox" value="" checked>Afficher les demandes</h5>
		      <body class="form-group" id="wrapper" ng-controller="CtrlAffectationSession">  
		      		<div id="dual-list-box-employees" class="form-group row">
           				<div class="col-md-5">       
	           				<h5><span class="label label-default" id="gauche">Collaborateurs disponibles: {{listDesCollaborateursDisponibles.length}} affichés</span></h5>  	 
           					<input ng-model="searchCollaborateurField" class="filter form-control filter-unselected" type="text" placeholder="Écrire le nom ou prénom du collaborateur ..." style="margin-bottom: 5px;" autofocus> 
 				   				
           					<select ng-options="collaborateurHTML as (collaborateur.firstName +', '+collaborateur.name) for collaborateur in filterCollaborateur =(listDesCollaborateursDisponibles  | objectToString:searchCollaborateurField)" class="unselected form-control" style="height: 200px; width: 100%;" size="12" ng-model="disponibleCollaborateurs" ></select> 
           					
           					
           					<div class="row">
							  <div class="small-2 small-centered columns" ng-show="(filterCollaborateur.length == 0)&&(searchCollaborateurField.length != Null)">
							    <div data-alert class="alert-box info">
							     <h5><strong>Aucun résultat</strong></h5>
							    </div> 
							  </div>
							</div>
						</div>
						<div class="col-md-2 center-block" style="margin-top: 80px">       
							<button type="button" class="btn btn-default col-md-8 col-md-offset-2 atr" title="Ajouter tous à la session" data-type="atr" style="margin-bottom: 10px;" ng-click="moveAll(listDesCollaborateursDisponibles, listDesCollaborateursSelectionnes)"><span class="glyphicon glyphicon-list"></span><span class="glyphicon glyphicon-chevron-right"></span></button>       
							<button type="button" class="btn btn-default col-md-8 col-md-offset-2 str" title="Ajouter à la session" data-type="str" style="margin-bottom: 20px;" ng-click="moveItem(filterCollaborateur[0], listDesCollaborateursDisponibles,listDesCollaborateursSelectionnes)"><span class="glyphicon glyphicon-chevron-right"></span></button>       
							<button type="button" class="btn btn-default col-md-8 col-md-offset-2 stl" title="Retirer de la formation" data-type="stl" style="margin-bottom: 10px;" ng-click="moveItem(disponibleCollaborateurSelectionnee[0], listDesCollaborateursSelectionnes, listDesCollaborateursDisponibles)" ng-disabled="CtrlItemIsSelectedTOEnableOrDisableButton(disponibleCollaborateurSelectionnee"><span class="glyphicon glyphicon-chevron-left"></span></button>       
							<button type="button" class="btn btn-default col-md-8 col-md-offset-2 atl" title="Retirer tous de la formation" data-type="atl" style="margin-bottom: 10px;" ng-click="moveAll(listDesCollaborateursSelectionnes, listDesCollaborateursDisponibles)"><span class="glyphicon glyphicon-chevron-left"></span><span class="glyphicon glyphicon-list"></span></button>   
						</div>   
						<div class="col-md-5"><br>       
							<h5><span class="label label-default">Collaborateurs selectionnées: {{listDesCollaborateursSelectionnes.length}} affichés</span></h5>       
							<br>       
							<select class="selected form-control" style="height: 200px; width: 100%;" multiple="" name="" ng-model="disponibleCollaborateurSelectionnee" ng-options="collaborateur as (collaborateur.firstName +', '+collaborateur.name) for collaborateur in listDesCollaborateursSelectionnes"></select>   
						</div>
					</div>        
       		 </body>  
            <div><button type="submit" class="btn btn-primary">Enregistrer</button></div>                      
       </div>
  

    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.3/angular.min.js"></script>
    <script src="Controllers.js"></script>
    
  </div>
</div>