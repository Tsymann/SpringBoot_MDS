<#include "../bases/importbase.ftl"/>
<#include "../partials/navbar.ftl"/>

<div class="jumbotron bg-light">

	<h5 class="mb-5">
			prenom: ${user.firstname}<br/>
			nom: ${user.lastname}<br/>
			wallet: ${user.wallet} €<br/>
			nombre de livre: ${user.books?size}
	</h5>
	
<div>

	<h2>Livres possedés :</h2>

	<div class="row">
		<#if user.books?size == 0>
			<h4 class="ml-5 mt-2">Aucun livre</h4>
		</#if>
		<#list user.books as book>
	
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
</div>
	
</div>

