<#include "../bases/importbase.ftl"/>
<#include "../partials/navbar.ftl"/>


<div class="jumbotron bg-light">

	<div class="row d-flex justify-content-between">
		<h2>Mes livres :</h2>
		<button class="btn btn-warning" onclick="location.href='/books/add'">ajouter un livre</button>
	</div>

	<div class="row">
		<#if myBooksNotOnSale?size == 0>
			<h4 class="ml-5 mt-2">Aucun livre</h4>
		</#if>
		<#list myBooksNotOnSale as book>
	
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
	                <br/>
	                <form method="post">
	                	<input type="number" id="id" name="id" value="${book.id}" hidden>
	                	<label for="price">prix</label>
	                	<input class="form-control" type="number" id="price" name="price" min="0" value="${book.price}">  
	              		<br/>
	              		<button type="submit" class="btn btn-primary">sell</button>
	              	</form>
	              </p>
	            </div>
	          </div>
	        </div>
	
		</#list>
	</div>
</div>

<div class="jumbotron bg-light">

	<h2>Mes livres à vendre :</h2>

	<div class="row">
	<#if myBooksOnSale?size == 0>
			<h4 class="ml-5 mt-2">Aucun livre</h4>
		</#if>
		<#list myBooksOnSale as book>
	
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
	                <br/>
	                 ${book.price} €
	              </p>
	              <a class="btn btn-primary" onclick="location.href='/books/notsell/${book.id}'">ne plus vendre</a>
	            </div>
	          </div>
	        </div>
	
		</#list>
	</div>
</div>