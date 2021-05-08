<#include "../bases/importbase.ftl"/>
<#include "../partials/navbar.ftl"/>

<div class="jumbotron bg-light">

	<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">prenom</th>
      <th scope="col">nom</th>
      <th scope="col">role</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
    
    <#if users?size == 0>
			<h4 class="ml-5 mt-2">Aucun utilisateurs</h4>
		</#if>
		<#list users as user>
    <tr>
      <th scope="row">${user.firstname}</th>
      <td>${user.lastname}</td>
      <td>${user.role.name}</td>
      <td><button class="btn btn-primary" onclick="location.href='/users/${user.id}'">detail</button></td>
    </tr>
    </#list>
    
  </tbody>
</table>

</div>

