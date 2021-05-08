<#include "../bases/importbase.ftl"/>
<#include "../partials/navbar.ftl"/>

<div class="jumbotron bg-light">

	<h2>Mes livres :</h2>

	<div class="row">
		<#if myBooks?size == 0>
			<h4 class="ml-5 mt-2">Aucun livre</h4>
		</#if>
		<#list myBooks as book>
	
	        <div class="col-md-3">
	          <div class="card bg-light mb-3 text-center">
	            <div class="card-header row d-flex justify-content-between mx-0">
	              <h5 class="col">${book.name}</h5>
	            </div>
	            <div class="card-body">
	              <p class="card-title">
		            <img src="https://via.placeholder.com/200" alt="image"></img>
	              </p>
	              <p class="card-text">
	                ${book.nbPage} pages
	              </p>
	            </div>
	          </div>
	        </div>
	
		</#list>
	</div>
</div>

<div class="jumbotron bg-light">

	<div class="row d-flex justify-content-between">
		<h2>Livres à vendre :</h2>
		<form method="post">
	    	<input class="form-control" type="number" id="nbPage" name="nbPage" min="0" placeholder="nombre de page max">  
	    	<button type="submit" class="btn btn-primary">rechercher</button>
	    </form>
	</div>

	<div class="row">
	<#if books?size == 0>
			<h4 class="ml-5 mt-2">Aucun livre</h4>
		</#if>
		<#list books as book>
	
	        <div class="col-md-3">
	          <div class="card bg-light mb-3 text-center">
	            <div class="card-header row d-flex justify-content-between mx-0">
	              <h5 class="col">${book.name}</h5>
	            </div>
	            <div class="card-body">
	              <p class="card-title">
		            <img src="https://via.placeholder.com/200" alt="image"></img>
	              </p>
	              <p class="card-text">
	                ${book.user.firstname} ${book.user.lastname}  
	                <br/>
	                 ${book.price} €
	              </p>
	              <a class="btn btn-primary" onclick="location.href='/books/buy/${book.id}'">buy</a>
	            </div>
	          </div>
	        </div>
	
		</#list>
	</div>
</div>

