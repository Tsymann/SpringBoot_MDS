<#include "../bases/importbase.ftl"/>



	
	  <form method="post" class="container mt-3">
	
	    <h1 class="h3 mb-3 font-weight-normal d-flex justify-content-center">Log in</h1>
	    <label for="inputFirstname">firstname</label>
	    <input type="text" name="firstname" id="inputFirstname" class="form-control" value="${firstname}" required autofocus>
	    <br />
	    <label for="inputLastname">lastname</label>
	    <input type="text" name="lastname" id="inputLastname" class="form-control" value="${lastname}" required autofocus>
	    <br />
	    <label for="inputRole">role</label>
	   	<select class="form-control" name="roleStr" id="role-select">
   	    	<option value="buyer" <#if role == "buyer">selected</#if>>buyer</option>
    		<option value="seller" <#if role == "seller">selected</#if>>seller</option>
		</select>
	   
	   
	   
	    <br />
	    <div class="d-flex justify-content-center">
	      <button class="btn btn-lg btn-primary" type="submit">
	        Log in
	      </button>
	    </div>
	    
	  </form>
	  
