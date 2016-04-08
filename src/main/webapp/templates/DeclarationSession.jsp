	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>

	 <%@ page import="com.viseo.c360.formation.domain.formation.SessionFormation" %>
	<%! 
		
		 String regexFormationSession = "\"/^"+SessionFormation.regextitreFormation+"+$/\""; 
		String regexDate = "\"/^"+SessionFormation.regexDate+"+$/\""; 
	%> 
	<!-- CSS -->
		
	<div class="row"> 
	  <div class="col-md-4">
	  </div>
	 
	 <div class="col-md-4">
		<div class="panel panel-default">
		
			  <div class="panel-heading">
			    <h3 class="panel-title">Déclaration d'une session</h3>
			  </div>
			  
			  <div class="panel-body">
				  <form name="sessionForm" ng-submit="DS.actionEnregistrer()" novalidate>
				
				 <!-- Nom de la Formation -->
				     <div class="form-group">
				    <h5> <span class="label label-default">Nom de la formation: </span></h5>
     					 <select class="selectpicker form-control"></select>
				    </div>
					
					      <!-- Heure de la session -->
				     <div class="form-group">
				        <h5> <span class="label label-default">Heure du Debut: (1er Jour)</span></h5>
				     <select class="selectpicker form-control">
				   	<option ng-repeat="horaire in DS.monTab track by $index"> {{horaire}}</option> 
				     </select>
				   	</div>
				   	
				     <div class="form-group">
 				    <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
				        <h5> <span class="label label-default">Heure de fin: (Dernier Jour)</span></h5>
				     
     					 <select class="selectpicker form-control">
 				   	<option ng-repeat="horaire in DS.monTab track by $index"> {{horaire}}</option> 
				     </select>
				    </div>
				    
				    <!-- Lieu de la session -->
				    <div class="form-group" ng-class="{ 'has-error' : sessionForm.titreformation.$invalid && sessionForm.titreformation.$dirty }">
				        <h5> <span class="label label-default">Lieu</span></h5>
     					 <select class="selectpicker form-control">
				     	<option>Salle Phuket</option>
				     	<option>Salle Bali</option>
				 	 <p ng-show="sessionForm.lieu.$invalid && sessionForm.lieu.$dirty" class="help-block">Veuillez entrer un nom valide</p>
				     </select>
				    </div>
				    
				    <div class="alert alert-danger" role="alert" ng-show="sessionForm.$invalid && sessionForm.$dirty">
			     	 Veuillez remplir tous les champs
			    	</div>
			    	
				    <button type="submit" class="btn btn-primary" ng-disabled="sessionForm.$invalid">Enregistrer</button>
				  </form>
				  </div>
			</div>
		</div>
	</div>