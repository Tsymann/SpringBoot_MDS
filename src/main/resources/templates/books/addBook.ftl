<#include "../bases/importbase.ftl"/>
<#include "../partials/navbar.ftl"/>


<div class="jumbotron bg-light">

	<form method="post">
	    <label for="name">Nom</label>
	    <input class="form-control" type="text" id="name" name="name" required>  
		<br/>
		<label for="nbPage">nombre de page</label>
	    <input class="form-control" type="number" id="nbPage" name="nbPage" min="0" required>  
	    <br/>
	    <label for="price">prix</label>
	    <input class="form-control" type="number" id="price" name="price" min="0" required>  
	    <br/>            	
		<button type="submit" class="btn btn-primary">ajouter</button>
	</form>
			
</div>